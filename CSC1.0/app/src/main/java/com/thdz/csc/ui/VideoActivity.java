package com.thdz.csc.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thdz.csc.base.BaseActivity;

/**
 * desc:    视频播放页面
 * author:  Administrator
 * date:    2018/9/29  8:04
 */
public class VideoActivity extends BaseActivity {
    @Override
    public void setContentView() {
        View view = new View(context);
        view.setBackgroundColor(Color.DKGRAY);
        setContentView(view);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }
}
