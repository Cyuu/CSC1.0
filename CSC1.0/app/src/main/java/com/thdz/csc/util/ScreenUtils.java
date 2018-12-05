package com.thdz.csc.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import com.thdz.csc.base.BaseActivity;


public class ScreenUtils {

    private static int Phone_width = 0;
    private static int Phone_height = 0;

//	/**
//	 * 获得屏幕宽度
//	 */
//	public static int getScreenWidth(Context context) {
//		if (Phone_width ==0) {
//			DisplayMetrics dm = new DisplayMetrics();
//			((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//			Phone_width = dm.widthPixels;
//		}
//		return Phone_width;
//	}
//
//	/**
//	 * 获得屏幕高度
//	 */
//	public static int getScreenHeight(Context context) {
//		if (Phone_height ==0) {
//			DisplayMetrics dm = new DisplayMetrics();
//			((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//			Phone_height = dm.heightPixels;
//		}
//		return Phone_height;
//	}

    /**
     * dp ---> px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp ---> px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px ---> dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(BaseActivity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = activity.getScreenWidth();
        int height = activity.getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(BaseActivity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = activity.getScreenWidth();
        int height = activity.getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

}
