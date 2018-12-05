package com.thdz.csc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.igexin.sdk.GTServiceManager;

/**
 * desc:       配置自定义推送服务<br>
 * 为了让推送服务在部分主流机型上更稳定运行，从2.9.5.0版本开始，个推支持第三方应用配置使用自定义Service来作为推送服务运行的载体。<br>
 * 核心服务, 继承 android.app.Service, 必须实现以下几个接口, 并在 AndroidManifest 声明该服务并配置成
 * android:process=":pushservice", 具体参考 {@link }
 * PushManager.getInstance().initialize(this.getApplicationContext(), userPushService), 其中
 * userPushService 为 用户自定义服务 即 CSCPushService.<br>
 * author:  Administrator
 * date:    2018/9/21  17:27
 */
public class CSCPushService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        GTServiceManager.getInstance().onCreate(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return GTServiceManager.getInstance().onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GTServiceManager.getInstance().onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GTServiceManager.getInstance().onLowMemory();
    }

}
