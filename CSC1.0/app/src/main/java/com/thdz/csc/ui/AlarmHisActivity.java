package com.thdz.csc.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thdz.csc.R;
import com.thdz.csc.adapter.AlarmCommonAdapter;
import com.thdz.csc.app.MyApplication;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.bean.AlarmBean;
import com.thdz.csc.bean.BaseListBean;
import com.thdz.csc.bean.gank.GankBaseInfo;
import com.thdz.csc.bean.gank.GankCateInfo;
import com.thdz.csc.http.RetrofitUtils;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 告警历史<br/>
 * ---------<br>
 * 接口：
 * 1 根据条件 查询：告警历史列表
 */
public class AlarmHisActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipy_layout)
    SwipyRefreshLayout swipy_layout;// 上下刷新

    @BindView(R.id.listview)
    ListView listview;

    private AlarmCommonAdapter alarmAdapter;

    // 数据Datalist
    private List<AlarmBean> alarmList;

    private String tip = "没有未确认的告警信息！";
    private String msgFail = "连接服务器失败";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_history_list);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        toolbar.setTitle("历史告警");
        setToolbarEnable(toolbar);

        VUtils.setSwipyColor(swipy_layout);
        swipy_layout.setDirection(SwipyRefreshLayoutDirection.TOP); // 只能从上面刷新
        swipy_layout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                switch (direction) {
                    case TOP:
                        // RequestData();
                        getCate();
                        break;
                    case BOTTOM:
                        // RequestData();
                        getCate();
                        break;
                }
            }
        });

        swipy_layout.setRefreshing(false);

        alarmAdapter = new AlarmCommonAdapter(context);
        listview.setAdapter(alarmAdapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (VUtils.isFastDoubleClick()) {
                    return;
                } else {
                    Intent intent = new Intent(context, DeviceDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", alarmList.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

//        RequestDataByKey();
        getTestData();
        getCate();
    }


    /**
     * 测试用的 请求gank.io的api数据
     */
    private void getCate() {
        Call<String> call = RetrofitUtils.getApi().getCategories();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                toast(response.body());
                Log.i(TAG, "请求成功，返回数据：" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG, "请求失败");
                swipy_layout.setRefreshing(false);
                toast(msgFail);
                t.printStackTrace();
            }
        });
    }

    /**
     * 测试用的 请求gank.io的api数据
     */
    private void getCate222() {
        Call<GankBaseInfo<GankCateInfo>> call = RetrofitUtils.getApi().getCates();

        call.enqueue(new Callback<GankBaseInfo<GankCateInfo>>() {
            @Override
            public void onResponse(Call<GankBaseInfo<GankCateInfo>> call, Response<GankBaseInfo<GankCateInfo>> response) {
                // toast(response.body());
                boolean error = response.body().isError();
                GankCateInfo bean = response.body().getResults();
                Log.i(TAG, "请求成功，返回数据：是否有误：" + error + "数据体 =" + bean.toString());
            }

            @Override
            public void onFailure(Call<GankBaseInfo<GankCateInfo>> call, Throwable t) {
                Log.i(TAG, "请求失败");
                swipy_layout.setRefreshing(false);
                toast(msgFail);
                t.printStackTrace();
            }
        });
    }



    private void getTestData() {
        alarmList = TestUtil.getAlarmList();
        alarmAdapter.setDataList(alarmList);
        alarmAdapter.notifyDataSetChanged();
    }


    /**
     * 获取当前时间字符串，格式：2016-08-11 08：08:08
     *
     * @param index 向前推迟的小时数
     */
    public String getStartDateStr(int index) {
        Calendar calendar = Calendar.getInstance();

        // calendar.add(Calendar.DAY_OF_MONTH, 0 - index);
        calendar.add(Calendar.HOUR_OF_DAY, 0 - index);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String value = sdf.format(date);
        return value;

    }


    /**
     * 请求数据<br/>
     */
    public void RequestData() {
//        String keyword = ed_station.getText().toString().trim();
//        if (TextUtils.isEmpty(keyword)) {
//            toast("请输入站点的名称或拼音");
//            return;
//        }

        String uid = MyApplication.getApp().getUid();
        String stnId = "";
        String level = "";
        String beginTime = "";
        String endTime = "";


        Call<BaseListBean<AlarmBean>> call = RetrofitUtils.getApi().getHisAlarmGet(uid, stnId, level, beginTime, endTime);

        call.enqueue(new Callback<BaseListBean<AlarmBean>>() {
            @Override
            public void onResponse(retrofit2.Call<BaseListBean<AlarmBean>> call, Response<BaseListBean<AlarmBean>> response) {
                alarmList = response.body().getData();


//                swipy_layout.setRefreshing(false);
//                String value = DataUtils.getRespString(response);
//                showView(value); // 处理方式3

            }

            @Override
            public void onFailure(retrofit2.Call<BaseListBean<AlarmBean>> call, Throwable t) {
                swipy_layout.setRefreshing(false);
                toast(msgFail);
                t.printStackTrace();
            }
        });
    }


    /**
     * 1 nUserId 用户uid<br/>
     * 2 sDTBegin, 开始时间，格式：2016-08-11 08：08:08<br/>
     * 3 isConfirmed 是否确认，需要填false
     */
    private String createParams() {
        try {
            org.json.JSONObject jsonObj = new org.json.JSONObject();
            jsonObj.put("DtBegin", getStartDateStr(12));
            jsonObj.put("BConfirmed", "false");
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取数据并展示在
     */
    private void showView(final String response) {
        Log.i(TAG, "解析出json，返回参数是：" + response);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String dataStr = DataUtils.getReturnData(response);
                    alarmList = com.alibaba.fastjson.JSONArray.parseArray(dataStr, AlarmBean.class);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (alarmList.size() <= 0) {
                                toast(tip);
                                return;
                            }
                            alarmAdapter.setDataList(alarmList);
                            alarmAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toast(tip);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {

                default:
                    break;
            }
        }
    }

}
