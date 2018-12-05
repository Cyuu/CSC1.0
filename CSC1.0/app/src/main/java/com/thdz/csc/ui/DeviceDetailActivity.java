package com.thdz.csc.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.util.VUtils;

import butterknife.BindView;

/**
 * desc:    设备详情(告警详情页)---- 点击告警列表的item，将进入产生告警的设备详情页<br>
 * author:  Administrator<br>
 * date:    2018/10/8  13:56<br>
 * ----------------<br>
 * 接口：
 * 1 设备id-->查询设备详情，基础信息+状态信息
 * 2 各种遥控命令
 * 3 MQ接收实时状态
 */
public class DeviceDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_cmd1)
    TextView btn_cmd1;

    @BindView(R.id.btn_cmd2)
    TextView btn_cmd2;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_alarm_detail);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        toolbar.setTitle("设备详情");
        setToolbarEnable(toolbar);

        btn_cmd1.setOnClickListener(this);
        btn_cmd2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.btn_cmd1:
                    showSureDialog("确定要执行遥控命令吗？", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toast("已发送遥控命令");
                        }
                    });
                    break;
                case R.id.btn_cmd2:
                    showSureDialog("确定要执行遥调命令吗？", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toast("已发送遥调命令");
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }
}
