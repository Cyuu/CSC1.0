package com.thdz.csc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.thdz.csc.R;
import com.thdz.csc.app.MyApplication;
import com.thdz.csc.base.BaseActivity;
import com.thdz.csc.event.ClientIdEvent;
import com.thdz.csc.service.CSCPushService;
import com.thdz.csc.util.BusUtil;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.SpUtil;
import com.thdz.csc.util.StatusBarCompat;
import com.thdz.csc.util.VUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 登录页面<br/>
 * ip地址是否用自动输入完成框<br/>
 * 如果不是自动登录，则不填充控件<br/>
 * 这里默认自动登录，而只有退出登录，才取消自动登录<br/>
 * 登录--->请求app使用的状态码-->成功后goMain(),失败后，goMain()<br/>
 * 如果上次已经登录，尚未注销，那将不再登录，直接：--->请求app使用的状态码 <br/>
 * --------------------------------<br>
 * 页面接口：
 * 1 登录，          params: 用户名，密码，cid
 * 2 检查用户权限，   params: 用户名，密码，(cid)
 * 3 退出登录，       params: 用户名，密码，cid
 * <br/>
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.ip_addr_tv)
    EditText ip_addr_tv;

    @BindView(R.id.username_tv)
    EditText username_tv;

    @BindView(R.id.password_tv)
    EditText password_tv;

    @BindView(R.id.cb_pwd_show)
    CheckBox cb_pwd_show;

    @BindView(R.id.login_btn)
    TextView login_btn;

    private String username = "";
    private String password = "";
    private String ipStr = "";

    // DemoPushService.class 自定义服务名称, 核心服务
    private Class cscPushService = CSCPushService.class;

    private String msgFail = "连接服务器失败";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BusUtil.reg(this);
        PushManager.getInstance().initialize(context.getApplicationContext(), cscPushService);
        login_btn.setOnClickListener(this);

        cb_pwd_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password_tv.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    password_tv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        cb_pwd_show.setChecked(true);

        // 设置沉浸式状态栏
        int staBarColor = ContextCompat.getColor(this, R.color.login_top_color);
        int navBarColor = ContextCompat.getColor(this, R.color.login_top_color);
        StatusBarCompat.setStatusBraColor(this, staBarColor, navBarColor);

        if (!Finals.IS_TEST) {
            // 获取到ClientId之前，将登陆按钮置灰，不可用
            if (TextUtils.isEmpty(SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID))) {
                login_btn.setBackgroundResource(R.drawable.bg_gray_login_corner);
                login_btn.setEnabled(false);
            }
        }

        if (Finals.IS_TEST) {
            findViewById(R.id.login_logo_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goActivity(HomeActivity.class, null);
                }
            });


        }

        showLoginEditView();

        hideInputMethod();

        if (Finals.IS_TEST) {
            ip_addr_tv.setText("192.168.1.21");
            username_tv.setText("admin");
            password_tv.setText("admin");

            ip_addr_tv.setSelection(ip_addr_tv.getText().toString().length());
            username_tv.setSelection(username_tv.getText().toString().length());
            password_tv.setSelection(password_tv.getText().toString().length());
        }

    }

    private void initPermission() {

    }


    @Override
    public void onClick(View v) {
        if (VUtils.isFastDoubleClick()) {
            return;
        } else {
            switch (v.getId()) {
                case R.id.login_btn:
                    doLogin();
                    break;
                default:
                    break;
            }
        }
    }


    private void RequestUserInfo() {
        MyApplication.controlFlag = false;
        MyApplication.alarmHandleFlag = false;
        String url = DataUtils.createReqUrl4Get(application.getIP(), application.getUid(),
                Finals.CMD_GetUserInfo, "");
        doRequestGet(url, new StringCallback() {
            @Override
            public void onError(Call call, final Exception e, int id) {
                e.printStackTrace();
                hideProgressDialog();
                gotoMain();
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    String value = DataUtils.getRespString(response);
                    Log.i(TAG, "Return userinfo = " + value);
                    String dataStr = DataUtils.getReturnData(value);

                    JSONObject jsonObj = new JSONObject(dataStr);
                    String controlFlag = jsonObj.getString("ClientControl");
                    String alarmHandleFlag = jsonObj.getString("ClientAlarmHandle");

                    if ("true".equalsIgnoreCase(controlFlag)) {
                        MyApplication.controlFlag = true;
                    } else {
                        MyApplication.controlFlag = false;
                    }

                    if ("true".equalsIgnoreCase(alarmHandleFlag)) {
                        MyApplication.alarmHandleFlag = true;
                    } else {
                        MyApplication.alarmHandleFlag = false;
                    }
                    Log.i(TAG, "controlFlag = " + MyApplication.controlFlag + " alarmHandleFlag = " + MyApplication.alarmHandleFlag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideProgressDialog();
                gotoMain();
            }
        });
    }

    /**
     * 登录请求
     */
    private void doLogin() {
        if (!doCheck()) {// 输入验证
            return;
        }
        showProgressDialog();
        String url = DataUtils.createReqUrl4Get(ipStr, "", Finals.CMD_UserLogin, createParams());
        doRequestGet(url, new StringCallback() {
            @Override
            public void onError(Call call, final Exception e, int id) {
                hideProgressDialog();
                toast(msgFail);
                e.printStackTrace();

            }

            @Override
            public void onResponse(String response, int id) {
                hideProgressDialog();
                String value = DataUtils.getRespString(response);
                Log.i(TAG, "解析出json，返回参数是：" + value);
                String dataStr = "";
                try {
                    dataStr = DataUtils.getReturnData(value);
                    if (DataUtils.isReturnOK(dataStr)) {
                        String uid = DataUtils.getReturnUserId(dataStr);
                        application.setUid(uid);
                        SpUtil.save(context, Finals.SP_UID, uid);
                        saveLoginInfo();

                        RequestUserInfo();

                    } else {
                        toast(DataUtils.getReturnMsg(dataStr));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!TextUtils.isEmpty(dataStr)) {
                        try {
                            JSONObject failObj = new JSONObject(dataStr);
                            String msgStr = failObj.getString("sMsg");
                            toast(msgStr);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            toast(msgFail);
                        }
                    } else {
                        toast(msgFail);
                    }
                }
            }
        });
    }

    private String createUserCheckParams() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("UserName", SpUtil.getData(context.getApplicationContext(), Finals.SP_USERNAME));
            jsonObj.put("UserPwd", SpUtil.getData(context.getApplicationContext(), Finals.SP_PWD));
            jsonObj.put("ClientId", SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID));
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    private String createParams() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("UserName", username);
            jsonObj.put("UserPwd", password);
            jsonObj.put("ClientId", SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID));
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 设置检测规则，检查输入是否正确, true 输入正确 // false 输入错误
     */
    private boolean doCheck() {
        ipStr = ip_addr_tv.getText().toString().trim();
        username = username_tv.getText().toString().trim();
        password = password_tv.getText().toString().trim();

        if (TextUtils.isEmpty(ipStr)) {
            toast("请输入合法的ip地址");
            return false;
        }

        if (TextUtils.isEmpty(username)) {
            toast("请输入用户名");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            toast("请输入密码");
            return false;
        }
        return true;
    }


    /**
     * 跳转至首页
     */
    private void gotoMain() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void doAutoLogin() {
        username_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_USERNAME));
        password_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_PWD));
        ip_addr_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_IP));

        ip_addr_tv.setSelection(ip_addr_tv.getText().toString().length());
        username_tv.setSelection(username_tv.getText().toString().length());
        password_tv.setSelection(password_tv.getText().toString().length());

        application.setIP(SpUtil.getData(context.getApplicationContext(), Finals.SP_IP));
        application.setUid(SpUtil.getUid(context.getApplicationContext()));

        // 如果已经登录，则直接请求用户权限接口
        RequestUserInfo();
    }

    private void requestLogout() {
        String url = DataUtils.createReqUrl4Get(
                SpUtil.getData(context.getApplicationContext(), Finals.SP_IP),
                SpUtil.getUid(context.getApplicationContext()),
                Finals.CMD_AppLogout,
                createLogoutParams());

        SpUtil.save(context, Finals.SP_AUTOLOGIN, "0");
        SpUtil.save(context, Finals.SP_PWD, "");
        SpUtil.save(context, Finals.SP_UID, "");
        doRequestGet(url, null);
    }

    private String createLogoutParams() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("UserName", "0");
            jsonObj.put("UserPwd", "0");
            jsonObj.put("ClientId", SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID));
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void checkAutoLogin() {
        String url = DataUtils.createReqUrl4Get(SpUtil.getData(context.getApplicationContext(), Finals.SP_IP), "", Finals.CMD_UserCheck, createUserCheckParams());
        doRequestGet(url, new StringCallback() {
            @Override
            public void onError(Call call, final Exception e, int id) {
                doAutoLogin();
            }

            @Override
            public void onResponse(String response, int id) {
                String value = DataUtils.getRespString(response);
                Log.i(TAG, "解析出json，返回参数是：" + value);
                String dataStr = "";
                try {
                    dataStr = DataUtils.getReturnData(value);
                    if (DataUtils.isReturnOK(dataStr)) {
                        doAutoLogin();
                    } else {
                        requestLogout();
                        forceShowLoginView();
                    }
                } catch (Exception e) {
                    requestLogout();
                    forceShowLoginView();
                }
            }
        });
    }

    private void forceShowLoginView() {
        hideProgressDialog();
        ip_addr_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_IP));
        username_tv.setText(SpUtil.getData(context.getApplicationContext(), Finals.SP_USERNAME));

        ip_addr_tv.setSelection(ip_addr_tv.getText().toString().length());
        username_tv.setSelection(username_tv.getText().toString().length());
    }

    /**
     * 判断是否是退出登录而来,这里默认自动登录，而只有退出登录，才取消自动登录<br/>
     * 根据sp里的值，填充到控件中，如果没有则，不动<br/>
     */
    private void showLoginEditView() {
        showProgressDialog();
        if (SpUtil.isAutoLogin(context.getApplicationContext())) { // 自动登录
            checkAutoLogin();
        } else { // 正常登录或者退出登录
            forceShowLoginView();
        }

    }


    /**
     * 登录成功后保存登录信息
     */
    private void saveLoginInfo() {
        SpUtil.save(context.getApplicationContext(), Finals.SP_IP, ipStr);//登录ip
        SpUtil.save(context.getApplicationContext(), Finals.SP_USERNAME, username);
        SpUtil.save(context.getApplicationContext(), Finals.SP_PWD, password);
        SpUtil.save(context.getApplicationContext(), Finals.SP_AUTOLOGIN, "1");//自动登录

        application.setIP(ipStr);
    }

    /**
     * 响应 获取个推ClientId的 请求
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetClientIdEvent(ClientIdEvent event) {
        Log.i(TAG, "已获取到ClientId: " + event.getClientId());
        // toast("获取到ClientId："+ event.getClientId());
        // 如果本地未保存，则调整登陆按钮颜色，并保存（第一次登录使用），否则，不做任何处理(以后使用)
        if (!TextUtils.isEmpty(SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID))) {
            // 将登陆框置为绿色，并可用
            login_btn.setBackgroundResource(R.drawable.bg_blue_login_selector);
            login_btn.setEnabled(true);
            SpUtil.save(context, Finals.SP_CLIENTID, event.getClientId());
        }
    }

}
