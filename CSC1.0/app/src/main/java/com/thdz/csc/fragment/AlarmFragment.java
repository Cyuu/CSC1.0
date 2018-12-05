package com.thdz.csc.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ScrollView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thdz.csc.R;
import com.thdz.csc.adapter.AlarmCommonAdapter;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.base.BaseFragment;
import com.thdz.csc.bean.AlarmBean;
import com.thdz.csc.bean.BaseListBean;
import com.thdz.csc.bean.PushBeanBase;
import com.thdz.csc.event.AlarmListEvent;
import com.thdz.csc.ui.AlarmLevelListActivity;
import com.thdz.csc.ui.AlarmSearchActivity;
import com.thdz.csc.ui.DeviceDetailActivity;
import com.thdz.csc.util.BusUtil;
import com.thdz.csc.util.TestUtil;
import com.thdz.csc.util.VUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 最新告警<br/>
 * 1 增加缓存<br/>
 * 2 增加默认选择条件<br/>
 * 3 增加跳转至搜搜页面的入口<br>
 * ---------------------
 * 接口：
 * 1 请求三种告警的统计数量
 * 2 获取最新告警列表(?)
 * 3 MQ接收实时状态
 */
public class AlarmFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sv_alarm)
    ScrollView sv_alarm;

    @BindView(R.id.swipy_layout)
    SwipyRefreshLayout swipy_layout;// 上下刷新

    @BindView(R.id.card_alarm1)
    CardView card_alarm1;
    @BindView(R.id.card_alarm2)
    CardView card_alarm2;
    @BindView(R.id.card_alarm3)
    CardView card_alarm3;

    @BindView(R.id.listview)
    ListView listview;

    private AlarmCommonAdapter alarmAdapter;

    // 数据Datalist
    private List<AlarmBean> alarmList;

    public static AlarmFragment newInstance() {
        Bundle args = new Bundle();
        AlarmFragment fragment = new AlarmFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup arg1, Bundle arg2) {
        return inflater.inflate(R.layout.fragment_alarm, arg1, false);
    }


    @Override
    public void initView(Bundle savedInstanceState, View view, LayoutInflater inflater) {
        EventBus.getDefault().register(this);

        setToolbarBackGone(toolbar);
        setHasOptionsMenu(true);

        VUtils.setSwipyColor(swipy_layout);
        swipy_layout.setDirection(SwipyRefreshLayoutDirection.TOP); // z只能从上面刷新
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

        card_alarm1.setOnClickListener(this);
        card_alarm2.setOnClickListener(this);
        card_alarm3.setOnClickListener(this);

        swipy_layout.setRefreshing(false);

        alarmAdapter = new AlarmCommonAdapter(context);
        listview.setAdapter(alarmAdapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (VUtils.isFastDoubleClick()) {
                    return;
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", alarmList.get(position));
                    ((BaseActivity) getActivity()).goActivity(DeviceDetailActivity.class, bundle);
                }
            }
        });

        getTestData();

    }

    private void getTestData() {
        alarmList = TestUtil.getAlarmList();
        alarmAdapter.setDataList(alarmList);
        alarmAdapter.notifyDataSetChanged();
        sv_alarm.smoothScrollTo(0, 0);
    }


    /**
     * 请求数据<br/>
     */
    public void RequestData() {
        Call<BaseListBean<AlarmBean>> newAlarm = api.getNewAlarmGet(application.getUid());
        newAlarm.enqueue(new Callback<BaseListBean<AlarmBean>>() {
            @Override
            public void onResponse(Call<BaseListBean<AlarmBean>> call, Response<BaseListBean<AlarmBean>> response) {
                swipy_layout.setRefreshing(false);
                if (response.body().getCode() != 0) {
                    toast(response.body().getMsg());
                    return;
                }
                // todo 要不要append数据
                alarmList = response.body().getData();
                showView(alarmList);
            }

            @Override
            public void onFailure(Call<BaseListBean<AlarmBean>> call, Throwable t) {
                swipy_layout.setRefreshing(false);
                toast("未获取到最新告警数据");
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        BusUtil.reg(this);
    }


    /**
     * 获取数据并展示在
     */
    private void showView(List<AlarmBean> alarmList) {
        if (alarmList.size() <= 0) {
            toast("未获取到最新告警数据");
            return;
        }
        alarmAdapter.setDataList(alarmList);
        alarmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.card_alarm1:
                    Bundle bundle = new Bundle();
                    bundle.putString("level", "1");
                    ((BaseActivity) getActivity()).goActivity(AlarmLevelListActivity.class, bundle);
                    break;
                case R.id.card_alarm2:
                    bundle = new Bundle();
                    bundle.putString("level", "2");
                    ((BaseActivity) getActivity()).goActivity(AlarmLevelListActivity.class, bundle);
                    break;
                case R.id.card_alarm3:
                    bundle = new Bundle();
                    bundle.putString("level", "3");
                    ((BaseActivity) getActivity()).goActivity(AlarmLevelListActivity.class, bundle);
                    break;
                default:
                    break;
            }
        }
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.alarm, menu);
        // menu.findItem(R.id.menu_connect).setVisible(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.alarm_refresh) {
            RequestData();
            getTestData();
        } else if (item.getItemId() == R.id.alarm_history) {
            ((BaseActivity) getActivity()).goActivity(AlarmSearchActivity.class, null);

            // 后期：Activity --> Fragment
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 推送处理：有最新告警，则刷新列表
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void dealListEvent(AlarmListEvent event) {
        PushBeanBase pushBean = event.getPushBean();
        EventBus.getDefault().removeStickyEvent(event); // FIXME 解决收到多条推送告警的bug
        RequestData();
    }

}
