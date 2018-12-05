package com.thdz.csc.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.thdz.csc.R;
import com.thdz.csc.ui.ImageActivity;

import java.lang.reflect.Method;

public class VUtils {

    /**
     * 上次点击时间
     */
    private static long lastClickTime;
    private static long interval_time = 450;

    /**
     * 防止重复点击，需要加：click select itemClick
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < interval_time) {
            // toast("按太快了");
            return true;
        }
        lastClickTime = time;
        return false;
    }


    public static void showSureDialog(Context context, String tip, DialogInterface.OnClickListener sureListener) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View mView = layoutInflater.inflate(R.layout.d_sure, null);
        TextView dialog_sure_tv = (TextView) mView.findViewById(R.id.dialog_sure_tv);
        dialog_sure_tv.setText(tip);
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("确认", sureListener);

        mBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog sureDialog = mBuilder.create();
        sureDialog.show();
    }


    public static void setSwipeColor(SwipeRefreshLayout sLayout) {
        sLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    /**
     * 设置SwipyLayout的颜色
     */
    public static void setSwipyColor(SwipyRefreshLayout sLayout) {
        sLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }


//    /**
//     * TODO 查看已下载的视频
//     */
//    public static void gotoVideo(Context context, String url, boolean isLocal) {
//        TsUtil.showToast(context, "rtsp视频");
//        Intent intent = new Intent(context, VideoActivity.class);
//        intent.putExtra("url", "file://" + path); // TODO url
//        intent.putExtra("isLocal", isLocal);
//        context.startActivity(intent);
//
//    }

    /**
     * TODO 查看已下载的大图
     */
    public static void gotoImage(Context context, String image_name) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("path", image_name);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }

    /**
     * 将dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将像素转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


//    /**
//     * 提供ViewPager的ViewList，注意些Adapter的时候必须用view_pager_item
//     */
//    public static List<View> getViewPagerViewList(Context context) {
//        List<View> views = new ArrayList<View>();
//        for (int i = 0; i < 4; i++) {
//            View view = LayoutInflater.from(context.getApplicationContext()).inflate(
//                    R.layout.view_pager_item, null);
//            views.add(view);
//        }
//        return views;
//    }


//
//    private static int screenWidth = 0;
//    private static int screenHeight = 0;
//
//    /**
//     * 获取屏幕宽度
//     */
//    public static int getScreenWidth(Context context) {
//        if (screenWidth == 0) {
//            DisplayMetrics metrics = new DisplayMetrics();
//            ((Activity) context).getWindowManager().getDefaultDisplay()
//                    .getMetrics(metrics);
//            screenWidth = metrics.widthPixels;
//        }
//
//        return screenWidth; // 屏幕的宽
//    }
//
//    /**
//     * 获取屏幕高度
//     */
//    public static int getScreenHeight(Context context) {
//        if (screenHeight == 0) {
//            DisplayMetrics metrics = new DisplayMetrics();
//            ((Activity) context).getWindowManager().getDefaultDisplay()
//                    .getMetrics(metrics);
//            screenHeight = metrics.heightPixels;
//        }
//        return screenHeight;// 屏幕的高
    //    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }


//    /**
//     * 显示键盘
//     */
//    public static void showInput(Context context, @NotNull View view) {
//        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        im.showSoftInput(view, 0);
//    }

    /**
     * 隐藏虚拟键盘
     */
    public static void hideInput(Activity act) {
        act.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * [华为手机]检测是否与偶刘海屏
     */
    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * [华为手机]获取刘海屏的参数
     */
    public static int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "getNotchSize Exception");
        } finally {
            return ret;
        }
    }


}
