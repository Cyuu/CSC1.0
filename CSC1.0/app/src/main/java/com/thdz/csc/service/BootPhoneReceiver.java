package com.thdz.csc.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 启动手机的广播
 */

public class BootPhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //在广播中声明
        String action = intent.getAction();
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {
            //Intent service = new Intent(context, PushBackReceiver.class);
            //service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startActivity(service);
        }
    }
}
