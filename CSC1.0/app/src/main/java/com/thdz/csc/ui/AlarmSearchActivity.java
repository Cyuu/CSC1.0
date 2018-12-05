package com.thdz.csc.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.util.VUtils;

import butterknife.BindView;

/**
 * desc:    准备查询条件，
 * author:  Administrator
 * date:    2018/9/27  13:38
 */
public class AlarmSearchActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ed_station)
    EditText ed_station;

//    @BindView(R.id.ed_alarm_level)
//    EditText ed_alarm_level;

    @BindView(R.id.tv_begin_time)
    TextView tv_begin_time;

    @BindView(R.id.tv_end_time)
    TextView tv_end_time;

    @BindView(R.id.btn_search)
    TextView btn_search;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        toolbar.setTitle("历史告警查询");
        setToolbarEnable(toolbar);
        btn_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.btn_search:
                    Bundle bundle = new Bundle();
                    // 告警查询条件
                    bundle.putString("station", "");
                    bundle.putString("level", "1");
                    bundle.putString("begin_time", "2018-10-1 1:12:13");
                    bundle.putString("end_time", "2018-10-8 1:12:13");
                    goActivity(AlarmHisActivity.class, bundle);
                    break;
                default:
                    break;
            }
        }
    }
}
