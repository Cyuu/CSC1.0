package com.thdz.csc.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.thdz.csc.R;
import com.thdz.csc.adapter.DeviceAdapter;
import com.thdz.csc.adapter.StationAdapter;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.base.BaseFragment;
import com.thdz.csc.bean.DeviceBean;
import com.thdz.csc.bean.StationBean;
import com.thdz.csc.ui.ImageActivity;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 监控站点
 * --------<br>
 * 接口：
 * 根据拼音  查询监控站点
 */
public class MonitorFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /* 检索条件 */
    @BindView(R.id.ed_station)
    EditText ed_station;

    @BindView(R.id.tv_search)
    ImageView tv_search;

    @BindView(R.id.listview)
    ListView listview;

    private StationAdapter stnAdapter;
    private List<StationBean> stnList;

    private DeviceAdapter deviceAdapter;
    private List<DeviceBean> deviceList;

    private String tip = "没有可查看的监控点";

    public static MonitorFragment newInstance() {
        Bundle args = new Bundle();
        MonitorFragment fragment = new MonitorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) {
        return inflater.inflate(R.layout.fragment_monitor, arg1, false);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view, LayoutInflater inflater) {

        setToolbarBackGone(toolbar);

        ed_station.clearFocus();

        tv_search.setOnClickListener(this);

        stnAdapter = new StationAdapter(context);
        listview.setAdapter(stnAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 更新id
                StationBean bean = stnList.get(position);
                ((BaseActivity) getActivity()).goActivity(ImageActivity.class, null);

            }
        });

        RequestData();
    }


    /**
     * 获取设备数据，并展示
     */
    public void RequestData() {
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
                showDeviceList(value);
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


    private void showDeviceList(String value) {

    }

    private void doSearch() {
        getTestData();
    }

    private void getTestData() {
        stnList = TestUtil.getStationList();
        stnAdapter.setDataList(stnList);
        stnAdapter.notifyDataSetChanged();
        listview.smoothScrollToPosition(0);

    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.tv_search:
                    doSearch();
                    break;
                default:
                    break;
            }
        }
    }


}
