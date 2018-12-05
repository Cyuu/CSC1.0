package com.thdz.csc.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.app.MyApplication;
import com.thdz.csc.util.BusUtil;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.SpUtil;
import com.thdz.csc.util.TsUtil;
import com.thdz.csc.util.VUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

/**
 * Activity框架<br/>
 * (请求接口步骤)<br/>
 * 1 initQueue() <br/>
 * 2 拼接参数 先调用buildCommonHttpParams()，然后构建自己的私有参数buildParams4XX()，返回map <br/>
 * 3 发送请求 volleyPostRequest() <br/>
 * ------------------------- <br/>
 * 公共的loading 需要在每个页面的initView方法里，加上common_null_layout.setOnClickListener(this);
 * 然后再pressEvent()里，添加该控件的点击事件
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

//    public final int CODE_RELOAD = 0; // 重新加载
//    public final int CODE_LOAD_NEW = 1; // 加载新内容
//    public final int CODE_LOAD_MORE = 2;// 加载更多

    public final String failTip = "连接服务器失败";
    public final String errorTip = "无相关数据";


    public MyApplication application;

    /**
     * 需要输入密码校验的对话框，里面的密码输入框
     */
    public EditText pEdit;

    protected Context context;
    public ProgressDialog progressDialog = null;
    /**
     * 通过 android:theme 属性应用这个样式到你的 ProgressBar 。
     * <style name="CircularProgress" parent="Theme.AppCompat.Light">
     * <item name="colorAccent">@color/indigo</item>
     * </style>
     * android:theme="@style/CircularProgress"
     */
    public String TAG = this.getClass().getSimpleName();

//    public ApiService api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        application = (MyApplication) getApplication();// 获取应用程序全局的实例引用
//        MyApplication application = (MyApplication) MyApplication.getInstance();
//        application.activityList.add(this); // 把当前Activity放入集合中
        application.addActivity(this);

        if (application.screenWidth == 0) {
            application.screenWidth = getScreenWidth();
        }

        if (application.screenWidth == 0) {
            application.screenheigth = getScreenHeight();
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView();



        ButterKnife.bind(this);
        initView(savedInstanceState);
//        hideInputMethod();

    }


    /**
     * 获得屏幕宽度
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * setContentView
     */
    public abstract void setContentView();


    /**
     * get方式
     *
     * @param url      url
     * @param callback 回调方法
     */
    public void doRequestGet(String url, StringCallback callback) {
        Log.i(TAG, "请求地址：" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * post方式
     *
     * @param url      url
     * @param params   post参数
     * @param callback 回调方法
     */
    public void doRequestPost(String url, String params, StringCallback callback) {
        Log.i(TAG, "请求地址：" + url);
        OkHttpUtils
                .get()
                .url(url)
                .addParams("sCmd", params)
                .build()
                .execute(callback);
    }


    /**
     * 发送控制命令的请求<br/>
     * 1 code， no的确认<br/>
     * 2 没有StringCallback
     */
    public void RequestControlCMD(String stnNo, String unitNo, int code) {
        String dataStr = DataUtils.CreateCommandParams(context, stnNo, unitNo, code);
        String url = DataUtils.createReqUrl4Get(
                application.getIP(), application.getUid(), Finals.CMD_SystemControlCmd, dataStr);
        doRequestGet(url, null);
//        toast("命令已发送");
    }


    public void showProgressDialog() {
        showProgressDialog("正在加载...");
    }

    public void showProgressDialog(String txt) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.setMessage(txt);
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * init view after setcontentView()
     */
    public abstract void initView(Bundle savedInstanceState);

    // @Subscribe
    @Override
    public abstract void onClick(View v);

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.push_right_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        hideInputMethod();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusUtil.unreg(this);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

//        application.activityList.remove(this); // 把当前Activity从集合中移除
        application.removeActivity(this);

    }

    public void hideInputMethod() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void onBackPressed() {
        if (progressDialog != null && progressDialog.isShowing()) {
            hideProgressDialog();
            return;
        }
        super.onBackPressed();
    }


    /**
     * 打开输入密码验证对话框
     */
    public void showPwdDialog(DialogInterface.OnClickListener sureListener) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View pwdView = layoutInflater.inflate(R.layout.dialog_pwd, null);
        TextView title_tv = (TextView) pwdView.findViewById(R.id.dialog_title_tv); // title
        title_tv.setText("请输入密码进行校验");
        pEdit = (EditText) pwdView.findViewById(R.id.pwd_check_ed);
        if (Finals.IS_TEST) {
            pEdit.setText(SpUtil.getData(context, Finals.SP_PWD));
            pEdit.setSelection(pEdit.getText().toString().length());
        }
        mBuilder.setView(pwdView);
        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton("确认", sureListener);
        mBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideInputMethod();
                    }
                });
            }
        });
        AlertDialog pwdDialog = mBuilder.create();
        pwdDialog.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // 打开输入法：
                if (pEdit != null) {
                    //设置可获得焦点
                    pEdit.setFocusable(true);
                    pEdit.setFocusableInTouchMode(true);
                    //请求获得焦点
                    pEdit.requestFocus();
                    //调用系统输入法
                    InputMethodManager inputManager = (InputMethodManager) pEdit
                            .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInput(pEdit, 0);
                }
            }
        }, 200);

    }


    /**
     * 设置title for Activity
     */
    public void setTitle(String title) {
        TextView titletv = (TextView) findViewById(R.id.title_tv);
        titletv.setText(title);
    }

    /**
     * 隐藏返回箭头 for Activity
     */
    public void setBackGone() {
        ImageView back = (ImageView) findViewById(R.id.left_img);
        back.setVisibility(View.GONE);
    }


    /**
     * 设置返回箭头动作
     */
    public void setBackActive() {
        ImageView back = (ImageView) findViewById(R.id.left_img);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 隐藏title栏右侧按钮 for Activity
     */
    public void setRightTopGone() {
        ImageView back = (ImageView) findViewById(R.id.right_img);
        back.setVisibility(View.INVISIBLE);
    }


    public void setToolbarUnable(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(false); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(false); // 显示左上角返回按钮
        }
    }

    public void setToolbarEnable(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 显示左上角返回按钮
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();//返回
                }
            });
        }
    }


    public void toast(String info) {
        TsUtil.toast(context, info);
    }

    public void goActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void showSureDialog(String tip, DialogInterface.OnClickListener sureListener) {
        VUtils.showSureDialog(context, tip, sureListener);
    }


}
