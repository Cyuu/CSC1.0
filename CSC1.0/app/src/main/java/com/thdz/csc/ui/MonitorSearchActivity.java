package com.thdz.csc.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.thdz.csc.R;
import com.thdz.csc.adapter.StationAdapter;
import com.thdz.csc.app.MyApplication;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.bean.BaseListBean;
import com.thdz.csc.bean.StationBean;
import com.thdz.csc.http.RetrofitUtils;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc:    监控站点查询页面
 * author:  Administrator
 * date:    2018/9/27  13:38
 */
public class MonitorSearchActivity extends BaseActivity {

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

    @Override
    public void setContentView() {
        setContentView(R.layout.fragment_monitor);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        toolbar.setTitle("站点查询");
        setToolbarEnable(toolbar);

        ed_station.clearFocus();

        tv_search.setOnClickListener(this);

        stnAdapter = new StationAdapter(context);
        listview.setAdapter(stnAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 进入站点，对应的设备列表页面
                // StationBean bean = stnList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", stnList.get(position));
                goActivity(DeviceListActivity.class, bundle);
            }
        });
    }

    /**
     * 获取设备数据，并展示
     */
    public void RequestDataByKey() {
        String keyword = ed_station.getText().toString().trim();
        if (TextUtils.isEmpty(keyword)) {
            toast("请输入站点的名称或拼音");
            return;
        }

        retrofit2.Call<BaseListBean<StationBean>> call = RetrofitUtils.getApi().getStationListGet(MyApplication.getApp().getUid(), keyword);
        call.enqueue(new Callback<BaseListBean<StationBean>>() {
            @Override
            public void onResponse(retrofit2.Call<BaseListBean<StationBean>> call, Response<BaseListBean<StationBean>> response) {
                stnList = response.body().getData();
                showStationList();
            }

            @Override
            public void onFailure(Call<BaseListBean<StationBean>> call, Throwable t) {
                toast("没有获取到监控点信息");
                Log.i(TAG, "没有获取到监控点列表信息");
                t.printStackTrace();
            }
        });
    }


    private void showStationList() {
        stnAdapter.setDataList(stnList);
        stnAdapter.notifyDataSetChanged();
        listview.smoothScrollToPosition(0);
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
                    RequestDataByKey();
                    break;
                default:
                    break;
            }
        }
    }


}
