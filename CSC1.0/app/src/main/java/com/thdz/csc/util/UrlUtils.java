package com.thdz.csc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * url 拼接工具类
 */

public class UrlUtils {

    private static final String TAG = "CSC";

    /**
     * 根据获取到的图片路径计算图片完整url
     */
    public static String createUrlPath(String ip, String url){
        String value = Finals.Url_Http + ip + url;
        return value;
    }

    /**
     * 判断网络是否连接.
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ni : info) {
                    if (ni.getState() == NetworkInfo.State.CONNECTED) {
                        Log.d(TAG, "type = " + (ni.getType() == 0 ? "mobile" : ((ni.getType() == 1) ? "wifi" : "none")));
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
