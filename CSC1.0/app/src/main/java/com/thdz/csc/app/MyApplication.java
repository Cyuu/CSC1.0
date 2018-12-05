package com.thdz.csc.app;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.thdz.csc.util.SpUtil;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {

    /**
     * api的类型
     * 1 生产环境， 是一个纯ip或者：ip:port<br>
     * 2 gank.io<br>
     * 3 github<br>
     * 4 <br>
     */
    private int API_TYPE = 2;


    /**
     * 通知的index
     */
    public static int notyId = 1;
    private String TAG = "MyApplication";
    private static MyApplication application;
    /**
     * 当前登录用户是否有控制权限
     */
    public static boolean controlFlag = false;
    /**
     * 当前登录用户是否有告警处理权限
     */
    public static boolean alarmHandleFlag = false;


//    //定时器，定时检测服务器是否通讯正常
//    private final Timer timer = new Timer();
//    public static boolean isShowAlarmDialog = false;
//    private static String time = "";

    public static MyApplication getApp() {
        if (null == application) {
            synchronized (MyApplication.class) {
                if (null == application) {
                    application = new MyApplication();
                }
            }
        }
        return application;
    }

    public int screenWidth = 0;
    public int screenheigth = 0;


    private String ip = "";
    private String uid = "";

    public String getIP() {
        if (TextUtils.isEmpty(ip)) {
            ip = SpUtil.getIP(getApplicationContext());
        }
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }


    public String getUid() {
        if (TextUtils.isEmpty(uid)) {
            uid = SpUtil.getUid(getApplicationContext());
        }

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 全部activity集合
     */
    public static List<Activity> activityList; // 全部activity集合


    /**
     * 获取网络请求的BaseUrl
     */
    public String getBaseUrl() {
        String url = "";
        switch (API_TYPE) {
            case 1:
                url = "http://" + MyApplication.getApp().getIP() + ":8080/";
                break;
            case 2:
                url = "https://gank.io/";
                break;
            case 3:
                url = "https://api.github.com";
                break;
        }
        return url;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        activityList = new LinkedList<>();

        MultiDex.install(this);

        initBaiduMap();

//        // 初始化OkHttpClient
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
//                .readTimeout(20000L, TimeUnit.MILLISECONDS)
//                .writeTimeout(20000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//        OkHttpUtils.initClient(okHttpClient);

//        //初始化定时器
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                handler.sendMessage(message);
//            }
//        };
//        timer.schedule(task, 60000, 300000);
    }

    private void initBaiduMap() {
        // 初始化百度地图
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }


    /**
     * 添加Activity到容器中
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除Activity到容器中
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static Activity getCurrentActivity() {
        if (activityList.size() == 0) return null;
        Activity mAct = activityList.get(activityList.size() - 1);
        return mAct;
    }

    /**
     * 遍历所有Activity并finish
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
//        timer.cancel();
        System.exit(0);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        exit();
        activityList.clear();
        activityList = null;
    }

}
