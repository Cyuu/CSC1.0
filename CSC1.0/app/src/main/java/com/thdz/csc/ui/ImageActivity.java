package com.thdz.csc.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.thdz.csc.R;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.util.VUtils;
import com.thdz.csc.view.TouchImageView;

import butterknife.BindView;

/**
 * 大图展示页面
 */
public class ImageActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image_zoom)
    TouchImageView image_zoom; // 可放大拉伸图片控件

    private String imgPath;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_image);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        BusUtil.reg(this);
//        setTitle("查看大图");
//        setBackActive();
//        setRightTopGone();

        toolbar.setTitle("大图");
        setToolbarEnable(toolbar);


        imgPath = getIntent().getStringExtra("path");
        if (TextUtils.isEmpty(imgPath)) {
            toast("图片不存在");
            finish();
            return;
        }

        showBmpByUrl(imgPath);

    }


    private void showBmpByUrl(String imgPath) {
//        OkHttpUtils
//                .get()
//                .url(imgPath)
//                .build()
//                .execute(new BitmapCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        e.printStackTrace();
//                        toast("图片不存在");
//                    }
//
//                    @Override
//                    public void onResponse(final Bitmap response, int id) {
//                        image_zoom.setImageBitmap(response);
//                        image_zoom.setZoom(1f);
//                    }
//                });
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
