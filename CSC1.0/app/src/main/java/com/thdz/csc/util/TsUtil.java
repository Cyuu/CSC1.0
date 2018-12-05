package com.thdz.csc.util;

import android.content.Context;
import android.widget.Toast;


public class TsUtil {
    /**
     * toast提示消息
     */
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//        showToast(MyApplication.getApp(), info);
    }

//	public static void WarnNoNet(Context context) {
//		Toast.makeText(context.getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();
//	}


//    /**
//     * 自定义Toast展示
//     */
//    public static void showToast(Context context, String info) {
//        LayoutInflater inflater = (LayoutInflater)
//                context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View toastView = inflater.inflate(R.layout.layout_toast, null, false);
//
//        Toast toast = new Toast(context.getApplicationContext());
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
//        toast.setDuration(Toast.LENGTH_SHORT);
//
//        TextView txt = (TextView) toastView.findViewById(R.id.txt_tips);
//        txt.setText(info);
//
//        toast.setView(toastView);
//        toast.show();
//    }


}
