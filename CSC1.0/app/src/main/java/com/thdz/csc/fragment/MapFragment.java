package com.thdz.csc.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.mapapi.clusterutil.clustering.Cluster;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.base.BaseFragment;
import com.thdz.csc.bean.PushBeanBase;
import com.thdz.csc.bean.StationBean;
import com.thdz.csc.event.AlarmListEvent;
import com.thdz.csc.map.PositionUtil;
import com.thdz.csc.map.StationClusterBean;
import com.thdz.csc.ui.DeviceListActivity;
import com.thdz.csc.ui.MonitorSearchActivity;
import com.thdz.csc.util.BusUtil;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.MapUtils;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 地图上展示告警站点<br>
 * --------------<br>
 * 接口：<br>
 * 1 获取站点列表
 * 2 MQ接收实时状态 - 用来更新站点的图标状态
 */
public class MapFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mapview)
    MapView mapview;

    @BindView(R.id.fab_location)
    FloatingActionButton fab_location;

    private List<StationBean> stnList;

    private String tip = "没有可查看的监控点";

    /**
     * 用户当前所在位置，或者默认一个位置, 天河电子门口
     */
    private LatLng myLatlng = new LatLng(38.927763, 115.469205); // 百度坐标

    private float zoomDefault = MapUtils.zoomDefault;

    private BaiduMap mBaiduMap;

    private ClusterManager<StationClusterBean> clusterManager;

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) {
        return inflater.inflate(R.layout.fragment_map, arg1, false);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view, LayoutInflater inflater) {

        setToolbarBackGone(toolbar);
        setHasOptionsMenu(true);
        fab_location.setOnClickListener(this);
        getDevicesByStation();

        initMap();

        showStation4Map();
    }

    private void RequestData() {

    }


    private void showStation4Map() {
        List<StationBean> stations = TestUtil.getStationList();
    }

    private void resetLocation() {
        MapStatus.Builder builder = new MapStatus.Builder();
        LatLng center = new LatLng(39.915071, 116.403907); // 默认当前位置
        float zoom = 11.0f; // 默认 11级
        builder.target(center).zoom(zoom);

        if (myLatlng.longitude != 0 && myLatlng.latitude != 0) {
//            MapUtils.setStatusAtLatLng(mBaiduMap, myLatlng);
            MapUtils.setStatusAtLatLngAndDefaultZoom(mBaiduMap, myLatlng);
        }

    }

    private void initMap() {
        mBaiduMap = mapview.getMap();

        resetLocation();

        // 隐藏logo
        View child = mapview.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

        /*
        //地图上比例尺
        mMapView.showScaleControl(false);
        // 隐藏缩放控件
        mMapView.showZoomControls(false);
        */

        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                MapStatus ms = new MapStatus.Builder().target(myLatlng).zoom(zoomDefault).build();
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));

                // 测试数据
                getTestData();

                // getDevicesByStation();

            }
        });

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 定义点聚合管理类ClusterManager
        clusterManager = new ClusterManager<StationClusterBean>(getActivity(), mBaiduMap);

        // 设置marker状态时的响应
        mBaiduMap.setOnMapStatusChangeListener(clusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(clusterManager);

        // 点聚合图标点击事件
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<StationClusterBean>() {
            @Override
            public boolean onClusterClick(Cluster<StationClusterBean> cluster) {
                toast("有" + cluster.getSize() + "个站点");
                return false;
            }
        });

        // 点聚合的item点击事件
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<StationClusterBean>() {
            @Override
            public boolean onClusterItemClick(StationClusterBean item) {
                StationBean bean = item.getStationBean();
                // toast(bean.getStnName() + "|" + bean.getStnState() + "|" + bean.getLat() + "," + bean.getLng());
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean);
                ((BaseActivity)getActivity()).goActivity(DeviceListActivity.class, bundle);
                return false;
            }
        });

    }

    private void getTestData() {
        List<StationBean> list = TestUtil.getStationList();
        List<StationClusterBean> items = new ArrayList<StationClusterBean>();
        for (StationBean stnBean : list) {
            LatLng latlng = PositionUtil.GpsToBd09(new LatLng(stnBean.getLat(), stnBean.getLng()));
            items.add(new StationClusterBean(stnBean, latlng));
        }
        clusterManager.addItems(items);

    }


    /**
     * 获取所有站点数据并展示
     */
    public void getDevicesByStation() {
        String url = DataUtils.createReqUrl4Get(application.getIP(), application.getUid(),
                Finals.CMD_GetStnList, createParams());
        doRequestGet(url, new StringCallback() {
            @Override
            public void onError(Call call, final Exception e, int id) {
                if (e.getMessage().contains("failed to connect")) {
                    toast("连接服务器失败");
                    Log.i(TAG, "连接服务器失败");
                } else {
                    toast("没有获取到监控点信息");
                    Log.i(TAG, "没有获取到监控点列表信息");
                }
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {
                String value = DataUtils.getRespString(response);
                showMapView(value);
            }
        });
    }


    private String createParams() {
        try {
            org.json.JSONObject jsonObj = new org.json.JSONObject();
            jsonObj.put("nUserId", application.getUid());
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            toast(errorTip);
        }
        return "";
    }

    private void showMapView(String value) {

    }

    @Override
    public void onStart() {
        super.onStart();
        BusUtil.reg(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.monitor, menu);
        // menu.findItem(R.id.menu_connect).setVisible(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_refresh) {
            RequestData();
            getTestData();
        } else if (item.getItemId() == R.id.item_query) {
            ((BaseActivity) getActivity()).goActivity(MonitorSearchActivity.class, null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.fab_location:
                    toast("定位");
                    resetLocation();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 推送处理：有最新告警，则刷新列表
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void dealListEvent(AlarmListEvent event) {
        PushBeanBase pushBean = event.getPushBean();
        EventBus.getDefault().removeStickyEvent(event); // FIXME 解决收到多条推送告警的bug

    }


}
