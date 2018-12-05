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
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.bean.AlarmBean;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 告警列表（1级， 2级， 3级）<br/>
 * ---------<br>
 * 接口：
 * 1 根据条件 查询：告警历史列表
 */
public class AlarmLevelListActivity extends BaseActivity {

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

        toolbar.setTitle("当前告警");

        try {
            String level = getIntent().getExtras().getString("level");
            toolbar.setTitle(level + "级告警");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setToolbarEnable(toolbar);

        VUtils.setSwipyColor(swipy_layout);
        swipy_layout.setDirection(SwipyRefreshLayoutDirection.TOP);
        swipy_layout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                switch (direction) {
                    case TOP:
                        RequestData();
                        break;
                    case BOTTOM:
                        RequestData();
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
        String url = DataUtils.createReqUrl4Get(application.getIP(), application.getUid(),
                Finals.CMD_GetRecentAlarm, createParams());
        doRequestGet(url, new StringCallback() {
            @Override
            public void onError(Call call, final Exception e, int id) {
                swipy_layout.setRefreshing(false);
                toast(msgFail);
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {
                swipy_layout.setRefreshing(false);
                String value = DataUtils.getRespString(response);
                showView(value); // 处理方式3
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
