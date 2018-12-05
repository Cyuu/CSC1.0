package com.thdz.csc.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class DataUtils {

    private static final String TAG = "DataUtils";

//    /**
//     * 解析xxx.json为String-->json-->db: 解析本地的Status.json，保存到数据库
//     */
//    public static void SaveLocalStatusList(Context context) {
//        try {
//            // txt ---> String
//            InputStream inputStream = context.getApplicationContext().getResources().getAssets().open("status.json");
////            byte[] arrayOfByte = new byte[inputStream.available()];
////            inputStream.read(arrayOfByte);
////            String address = EncodingUtils.getString(arrayOfByte, "utf-8");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            String statusStr = new String(buffer, "utf-8");
//            inputStream.close();
//
//            // String --> json
//            List<StateCodeBean> codeBeans = JSON.parseArray(statusStr, StateCodeBean.class);
//            Log.i(TAG, "codeBeans.size = " + codeBeans.size());
//            // json --> db
//            DBManager.getInstance().insertStateCodeList(codeBeans);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 获取get请求的url<br/>
     * 方法调用： createReqUrl4Get(ip,uid,cmdStr,dataStr)<br/>
     * "DATA"节点的value是字符串类型，from 2016-08-31 <br/>
     * TODO 193 对应80端口，225对应8088，其他待定<br/>
     * 2016.9.30 确认 端口都改为：8088<br/>
     */
    public static String createReqUrl4Get(String ip, String uid, String cmdStr, String dataStr) {
        if (TextUtils.isEmpty(uid)) {
            uid = "0";
        }
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("CMD", Integer.parseInt(cmdStr));
            jsonObj.put("UID", uid);
            int pfValue = 3; // 1-服务器，2-PC客户端，3-手机客户端, 4 web
            jsonObj.put("PF", pfValue); // 1-服务器，2-PC客户端，3-手机客户端, 4 web， String --> int
            try {
                jsonObj.put("DATA", new JSONObject(dataStr));
            } catch(JSONException e) {
                jsonObj.put("DATA", dataStr);
            }
            // jsonObj.put("DATA", dataStr);

//            String result = "http://" + ip + ":8088" + Finals.Url_Head + "?sCmd=" + jsonObj.toString();
            String result = Finals.Url_Http + ip + Finals.Url_Head + "?sCmd=" + jsonObj.toString();
//            if (ip.contains("193")) {
//                result = "http://" + ip + Finals.Url_Head + "?sCmd=" + jsonObj.toString();
//            } else if (ip.contains("225")) {
//                result = "http://" + ip + ":8088" +Finals.Url_Head + "?sCmd=" + jsonObj.toString();
//            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }


    public static String createImageUrl(String ip, String picPath) {
        String picUrl = Finals.Url_Http + ip +  "/" + picPath.replace('\\', '/');
        return picUrl;
    }


    /**
     * 获取get请求的url<br/>
     * 方法调用： createReqUrl4Get(ip,uid,cmdStr,dataStr)<br/>
     * "DATA"节点的value是字符串类型，from 2016-08-31 <br/>
     * TODO 193 对应80端口，225对应8088，其他待定<br/>
     * 2016.9.30 确认 端口都改为：8088<br/>
     */
    public static String createWSCmdUrl(Context context, String ip, String uid, int cmdCode, String dataStr) {
        if (TextUtils.isEmpty(uid)) {
            uid = "0";
        }
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("JSCode", cmdCode);
            jsonObj.put("UserId", uid);
            jsonObj.put("ClientId", SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID));
            int pfValue = 3; // 1-服务器，2-PC客户端，3-手机客户端, 4 web
            jsonObj.put("PF", pfValue); // 0-ADS服务程序, 1-服务器，2-PC客户端，3-手机客户端, 4 web， String --> int

            try {
                jsonObj.put("DATA", new JSONObject(dataStr));
            } catch(JSONException e) {
                jsonObj.put("DATA", dataStr);
            }
            // jsonObj.put("DATA", dataStr);

//            String result = "http://" + ip + ":8088" + Finals.Url_Head + "?sCmd=" + jsonObj.toString();
            String result = Finals.Url_Http + ip + Finals.Url_Head + "?sJson=" + jsonObj.toString();
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

     /**
     * 获取版本号
     */
    public static String getVersion(Context context) {
        String value = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            value = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 所有发送控制命令的入参 - 参数
     */
    public static String CreateCommandParams(Context context, String stnNo, String unitNo, int codeId) {
        try {
            JSONObject jsonObj = new JSONObject();

            String userName = SpUtil.getData(context.getApplicationContext(), Finals.SP_USERNAME);
            String ClientId = SpUtil.getData(context.getApplicationContext(), Finals.SP_CLIENTID);

            jsonObj.put("StnNo", Integer.parseInt(stnNo)); // 监控点编号(tid)
            jsonObj.put("UnitNo", Integer.parseInt(unitNo));  // 单元编号(sid)
            jsonObj.put("CodeId", codeId);

            jsonObj.put("UserName", userName);
            jsonObj.put("ClientId", ClientId);
            jsonObj.put("CodeTm", DataUtils.getCurrentTm());
            jsonObj.put("No", DataUtils.getCMD_No());
            jsonObj.put("Platform", 3);  // 平台
            jsonObj.put("Param1", "0");
            jsonObj.put("Param2", unitNo);
            jsonObj.put("Param3", "0");
            jsonObj.put("Param4", "0");
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


//    /**
//     * 获取post请求的url
//     */
//    public static String getReqUrlHead4Post(Context context) {
//        return "http://" + SpUtil.getIP(context) + Finals.Url_Head;
//    }


    /**
     * 解析出完整的json结构
     */
    public static String getRespString(String response) {
        if (response.contains("{") && response.contains("}")) {
            int firstIndex = response.indexOf("{");
            int lastIndex = response.lastIndexOf("}");
            String value = response.substring(firstIndex, lastIndex + 1);
            return value;
        }
        return null;
    }

    public static boolean isReturnOK(String value) throws Exception {
        JSONObject jsonObj = new JSONObject(value);
        String returnCode = jsonObj.getString("Result");
        if (returnCode.equalsIgnoreCase("true")) {
            return true;
        }
        return false;
    }


    public static String getReturnMsg(String value) throws Exception {
        JSONObject jsonObj = new JSONObject(value);
        String msg = jsonObj.getString("Msg");
        return msg;
    }


    /**
     * 新的信息返回：{"CMD":22,"UID":-1,"PF":1,"DATA":{"bResult":true,"sMsg":null}}
     */
    public static String getReturnResult(String value) throws Exception {
        JSONObject jsonObj = new JSONObject(value);
        String DataStr = jsonObj.getString("DATA");
        JSONObject jsonObj2 = new JSONObject(DataStr);
        String msg = jsonObj2.getString("bResult");
        return msg;
    }


    public static String getReturnUserId(String value) throws Exception {
        JSONObject jsonObj = new JSONObject(value);
        String uid = jsonObj.getString("UserId");
        return uid;
    }

    /**
     * 从json结构中解析中完整DATA结构
     */
    public static String getReturnData(String value) throws Exception {
        JSONObject jsonObj = new JSONObject(value);
        String msg = jsonObj.getString("DATA");
        return msg;
    }


    /**
     * 生成随即请求No，Scope:(1 - 65535)
     */
    public static String getCMD_No() {
        int temp = (int) (Math.random() * 65534 + 1);
        return temp + "";
    }


    /**
     * 生成随即请求No，Scope:(1 - 65535)
     */
    public static String getCurrentTm() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

}
