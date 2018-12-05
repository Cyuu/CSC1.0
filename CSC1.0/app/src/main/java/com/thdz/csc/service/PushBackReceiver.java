package com.thdz.csc.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.thdz.csc.app.MyApplication;
import com.thdz.csc.bean.PushBeanBase;
import com.thdz.csc.event.AlarmCancelEvent;
import com.thdz.csc.event.AlarmListEvent;
import com.thdz.csc.event.ClientIdEvent;
import com.thdz.csc.ui.MainActivity;
import com.thdz.csc.util.BusUtil;
import com.thdz.csc.util.DataUtils;
import com.thdz.csc.util.Finals;
import com.thdz.csc.util.NotifyUtil;
import com.thdz.csc.util.SpUtil;

/**
 * 个推 推送需要的
 * com.thdz.xgj.service.PushBackReceiver
 */
public class PushBackReceiver extends BroadcastReceiver {

    /**
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView ==
     * null)
     */
    // public StringBuilder payloadData = new StringBuilder();
    private String TAG = "PushBackReceiver";
    // 通知id
//    private int notyId = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        int mAction = bundle.getInt(PushConsts.CMD_ACTION);
//        Log.i(TAG, "个推：action = " + mAction);

        switch (mAction) {
            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                // String appid = bundle.getString("appid");
                String messageid = bundle.getString("messageid");
                String taskid = bundle.getString("taskid");

                byte[] payload = bundle.getByteArray("payload");

                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(
                        context, taskid, messageid, 90001);
                Log.i(TAG, "csc 推送返回：" + (result ? "成功" : "失败"));

                if (payload != null) {
                    try {
                        String resultStr = new String(payload);
                        Log.i(TAG, "推送data = " + resultStr);
                        String data = DataUtils.getReturnData(resultStr);
                        PushBeanBase pushBean = JSON.parseObject(data, PushBeanBase.class);

                        // 处理推送
                        handlePush(context, pushBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case PushConsts.GET_CLIENTID: // 保存打本地，登录时上传到服务器
                // 获取ClientID(CID)
                // 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
                String clientid = bundle.getString("clientid");
                SpUtil.save(context, Finals.SP_CLIENTID, clientid); // 登陆页面保存
                ClientIdEvent clientEvent = new ClientIdEvent();
                clientEvent.setClientId(clientid);

                BusUtil.postSticky(clientEvent);
                Log.i(TAG, "clientid = " + clientid);

                break;

            case PushConsts.THIRDPART_FEEDBACK:
                /*
                 * String appid = bundle.getString("appid"); String taskid =
                 * bundle.getString("taskid"); String actionid =
                 * bundle.getString("actionid"); String result =
                 * bundle.getString("result"); long timestamp =
                 * bundle.getLong("timestamp");
                 *
                 * Log.d("GetuiSdkDemo", "appid = " + appid); Log.d("GetuiSdkDemo",
                 * "taskid = " + taskid); Log.d("GetuiSdkDemo", "actionid = " +
                 * actionid); Log.d("GetuiSdkDemo", "result = " + result);
                 * Log.d("GetuiSdkDemo", "timestamp = " + timestamp);
                 */
                break;

            default:
                break;
        }
    }


    /**
     * TODO 推送请求处理
     * 1 根据Code 分别处理，
     * 2 使用EventBus
     * 3 是否需要打开询问对话框
     * 4 StnNo, UnitNo, CodeId, CodeTm
     * 结论： </br>
     * 1 新告警：通知栏提示，点击调用MainActivity，刷新告警列表
     * 2 某条告警的图片，控制命令回复时，如果使用EventBus，如果是当前告警页，则Toast提示，展示查看按钮，刷新Status请求，刷新按钮样式。
     * 如果是其他页面，则不予以提示。
     * 3
     */
    private void handlePush(Context context, PushBeanBase pushBean) {
        if (pushBean == null) {
            return;
        }


        String codeId = pushBean.getCodeId();
        String stnNo = pushBean.getStnNo(); // 站点no -- 用来查询监控点名称
        String codeTm = pushBean.getCodeTm();
        String name = "";

//        if (Finals.IS_TEST) {
//            String name = DBManager.getInstance().getNameValueByCode(codeId);
//            Log.i(TAG, "推送codeId = " + codeId + ",名称 = " + name + ", StnNo = " + stnNo);
//        }

        int code = Integer.parseInt(codeId);

        switch (code) {
            case Finals.CODE_NEW_ALARM: // 来新告警
                // 1 告警页列表刷新
                AlarmListEvent listEvent = new AlarmListEvent(pushBean);
                BusUtil.postSticky(listEvent);

                // 2 弹出通知栏消息
                MyApplication.notyId++;
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // StnName = getStnName(stnNo);
                NotifyUtil.showNotification(context, name + "发生告警！", codeTm, intent, MyApplication.notyId);
                break;
            case Finals.CODE_CANCEL_ALARM: // 新告警取消了
                MyApplication.notyId++;
                intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // StnName = getStnName(stnNo);
                NotifyUtil.showNotification(context, name + "告警取消！", codeTm, intent, MyApplication.notyId);

                listEvent = new AlarmListEvent(pushBean);
                BusUtil.postSticky(listEvent);

                AlarmCancelEvent event = new AlarmCancelEvent(pushBean);
                BusUtil.postSticky(event);

                break;

            default:
                break;
        }
    }

}