package com.thdz.csc.util;

import android.os.Environment;

/**
 * 常量类
 */
public class Finals {


    public static final String TAG = "CSC";

    public static final boolean IS_TEST;    // 是否测试
    public static final String FilePath;    // 文件缓存至磁盘路径
    public static final String PhonePath;   // 手机外部存储路径


    static { // TODO 打包前修改
        IS_TEST = true ; // false  true
        PhonePath = Environment.getExternalStorageDirectory().getPath();
        FilePath = Environment.getExternalStorageDirectory() + "/csc";
    }


    // ------SharedPreferences变量--start-----

    public static final String SP_NAME = "csc_sp";          // 名称
    public static final String SP_UID = "uid";              // 用户id
    public static final String SP_USERNAME = "username";    // 用户名称
    public static final String SP_PWD = "SP_PWD";
    public static final String SP_IP = "SP_IP";
    public static final String SP_AUTOLOGIN = "SP_AUTOLOGIN";
    public static final String SP_CLIENTID = "SP_CLIENTID"; // 推送唯一码

    // -----SharedPreferences变量--end-----


//    public static String logPath = "/log";        // 日志路径
//    private static String cachePath = "/cache";   // 缓存路径
    private static String imagePath = "/image";     // 图片存储路径


    /**
     * 图片存储路径
     */
    public static String ImageStoragePath = FilePath + imagePath;   // 用于存储下载图片
//    /**
//     * 图片缓存路径
//     */
//    public static String imageCachePath = FilePath + cachePath;     // 图片缓存路径
//    /**
//     * 日志存储路径
//     */
//    public static String LogStoragePath = FilePath + logPath;       // 日志存储路径

    // ---------------- 各种url接口----------------
    /**
     * 测试下载apk的url(无秘v5.3)
     */
    public static final String Url_test_update = "http://220.195.19.16/dd.myapp.com/16891/526E19240B32BDF54BE778AB9520A724.apk";

    //
    public static final String Url_Http = "http://";             // 登录

    //
//    public static final String Url_PORT = ":5546";          // 接口url的通用端口

    /**
     * TODO url头   ?sCmd=
     */
    public static final String Url_Head = "/WebService.asmx/RailwayForeignInvasionWebService";

    public static final String CMD_UserLogin = "20001";            // 登录
    public static final String CMD_GetStnList = "20002";           // 站点信息
    public static final String CMD_GetRecentAlarm = "20003";       // 最近告警列表
    public static final String CMD_GetRecentPic = "20004";         // 最近的几张图片 - app用不到，Pc在用
    public static final String CMD_GetIPCPicByAlarmNo = "20005";         // 告警图片
    public static final String CMD_GetRadarPicByAlarmNo = "20006";         // 雷达图片
    public static final String CMD_GetStnInfoByIds = "20007";        // 获取监控点信息
    public static final String CMD_GetStnStatusByIds = "20008";         // 获取监控点实时状态

    public static final String CMD_GetLastPic = "20009";     // 监控单元的最新一张IPC图片
    public static final String CMD_GetLastRadar = "20010";   // 监控单元的最新一张雷达图片
    public static final String CMD_GetUnitInfoByIds = "20011";       // 单元详情信息
    public static final String CMD_GetUnitStatusByIds = "20012";        // 单元详情状态

    public static final String CMD_GetPcdtStatusByIds = "20013";       // 获取Pcdt实时状态 -- app用不到，PC在用
    public static final String CMD_GetNvrList = "20014";  // 获取Nvr列表及实时状态 -- 录像设备 - app用不到，Pc在用
    public static final String CMD_GetIpcList = "20015";         // 获取Nvr列表及实时状态 -- 录像设备 - app用不到，Pc在用

    public static final String CMD_GetHisAlarm = "20016";     // 搜索历史告警列表
    public static final String CMD_GetCodeList = "20017";        // 监控点状态编码和名称的对应数据
    public static final String CMD_SystemControlCmd = "20018";    // 发送各种操作命令：控灯，抠图命令等
    public static final String CMD_UpdateHandleInfo = "20019";     // 用户提交告警处理意见
    public static final String CMD_AppLogout = "20020";          // 用户退出登录
    public static final String CMD_GetLargePic = "20021";       // 24  请求抓图、抠图 图片信息
    public static final String CMD_GetErrStatusStnList = "20022";   // 25. 请求四大故障有问题的点列表
    public static final String CMD_GetErrStatusListByStnIdAndErrGroupId = "20023";      // 26. 根据StnId和分组Id，获取未处理的异常状态列表（手机和PC端暂不使用）
    public static final String CMD_GetAlarmRecordByAlarmRcdId = "20024";  // 27. 根据AlarmId，查询告警信息，开始时间，结束时间。
    public static final String CMD_AlarmDeal = "20025";  // 28. 远程取消/确认告警

    public static final String CMD_GetStnStatusByUserId = "20026";  // 根据UserId，获取所管辖的所有监控点状态
    public static final String CMD_GetPcdtStatusByUserId = "20027";  // 根据UserId，获取所管辖的所有Pcdt状态
    public static final String CMD_GetUnitStatusByUserId = "20028";  // 根据UserId，获取所管辖的所有单元状态
    public static final String CMD_GetUserInfo = "20029";  // 根据UserId，获取用户权限
    public static final String CMD_GetRecentAlarm_PC = "20030";  // 获取用户权限范围内的最近告警
    public static final String CMD_UserCheck = "20031";             // 登录

    public static final String CMD_GetStnInfoByAlarmRcdId = "20032";       // 告警所属站点的基本信息
    public static final String CMD_GetStnStatusByAlarmRcdId = "20033";        // 告警所属站点的状态


    // 实时视频
    public static final String Url_Video_rt = // "rtsp://192.163.1.191:554/real/normal/1";
            "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp";
    public static final String Url_video_mp4 = "rtsp://192.163.1.191:554/record/hdda/alarm/20160511_164911_01.mp4"; // 视频录像

    // --------------- 发送控制命令的code --------------

    public static final int CODE_NEW_ALARM = 4204;          // 有新告警
    public static final int CODE_CANCEL_ALARM = 4203;       // 告警取消

//    public static final int CODE_PIC_DEVICE_OCCLU = 3000;       // 设备遮挡图片   目前未用
//    public static final int CODE_PIC_REGION = 3001;             // 区域图片到达   目前未用
    public static final int CODE_PIC_YUJING = 3002;             // 预警图片到达
    public static final int CODE_PIC_SMALL_YUJING = 3003;       // 小物体预警图片到达 --> 设备告警图片
    public static final int CODE_PIC_SMALL_GAOJING = 3004;      // 小物体告警图片到达

    public static final int CODE_IPC_RT_ARRIVE = 3005;          // Ipc巡视图片到达 - 监控单元 - 站点浏览时用,
    public static final int CODE_PIC_YIWU_ARRIVE = 3006;        // 异物告警图片
//    public static final int CODE_PIC_USER_FETCH = 3007;          // 用户获取图片   目前未用

//    public static final int CODE_MANUAL_PIC = 4564;             // 手动抓图         目前未用
//    public static final int CODE_MANUAL_PIC_BACK = 4565;        // 手动抓图 回复    目前未用

    public static final int CODE_REALITME_PIC = 4868;           // 用户请求实时图片
    public static final int CODE_REALITME_PIC_BACK = 4869;      // 用户请求实时图片 回复
    public static final int CODE_CORP_PIC = 4864;               // 抠图：用户请求局部高清图片
    public static final int CODE_CORP_PIC_BACK = 4865;          // 抠图：用户请求局部高清图片 回复

    // 告警详情，雷达数据，包含图片，从中获取图片
    public static final int CODE_RADAR_ALARM_BACK = 113;    //3036;     // 雷达告警数据 - 告警页面用

    // 监控单元 - 雷达数据，包含图片，从中获取图片
    public static final int CODE_RADAR_RT_BACK = 112;       //3030;    // 实时雷达数据 - 站点浏览时用,


    public static final int CODE_OPEN_ALERT = 4500;            // 手动打开警号， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_OPEN_ALERT_BACK = 4501;       // 手动打开警号回复， GTOOLS/GBC<-PCDT
    public static final int CODE_CLOSE_ALERT = 4502;           // 手动关闭警号， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_CLOSE_ALERT_BACK = 4503;      // 手动关闭警号回复， GTOOLS/GBC<-PCDT

    public static final int CODE_OPEN_ALARM_DEV = 4750;          // GBC打开现场报警设备
    public static final int CODE_OPEN_ALARM_DEV_BACK = 4751;          // GBC打开现场报警设备回复
    public static final int CODE_CLOSE_ALARM_DEV = 4752;     // GBC关闭现场报警设备
    public static final int CODE_CLOSE_ALARM_DEV_BACK = 4753;     // GBC关闭现场报警设备回复

    public static final int CODE_OPEN_UPALARM = 4504;          // 手动打开上行告警灯， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_OPEN_UPALARM_BACK = 4505;     // 手动打开上行告警灯回复， GTOOLS/GBC<-PCDT
    public static final int CODE_CLOSE_UPALARM = 4506;         // 手动关闭上行告警灯， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_CLOSE_UPALARM_BACK = 4507;    // 手动关闭上行告警灯回复， GTOOLS/GBC<-PCDT
    public static final int CODE_OPEN_DOWNALARM = 4508;        // 手动打开下行告警灯， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_OPEN_DOWNALARM_BACK = 4509;   // 手动打开下行告警灯回复， GTOOLS/GBC<-PCDT
    public static final int CODE_CLOSE_DOWNALARM = 4510;       // 手动关闭下行告警灯， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_CLOSE_DOWNALARM_BACK = 4511;  // 手动关闭下行告警灯回复， GTOOLS/GBC<-PCDT

    public static final int CODE_OPEN_UPTCG = 4512;            // 手动打开上行列调， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_OPEN_UPTCG_BACK = 4513;       // 手动打开上行列调回复， GTOOLS/GBC<-PCDT
    public static final int CODE_CLOSE_UPTCG = 4514;           // 手动关闭上行列调， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_CLOSE_UPTCG_BACK = 4515;      // 手动关闭上行列调回复， GTOOLS/GBC<-PCDT
    public static final int CODE_OPEN_DOWNTCG = 4516;          // 手动打开下行列调， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_OPEN_DOWNTCG_BACK = 4517;     // 手动打开下行列调回复， GTOOLS/GBC<-PCDT
    public static final int CODE_CLOSE_DOWNTCG = 4518;         // 手动关闭下行列调， GTOOLS/GBC->PCDT,PCDT收到此命令， 系统进入手动模式
    public static final int CODE_CLOSE_DOWNTCG_BACK = 4519;    // 手动关闭下行列调回复， GTOOLS/GBC<-PCDT


    public static final int CODE_OPEN_GSTR = 4534;             // 启用地面监测单元主控(GSTR)
    public static final int CODE_OPEN_GSTR_BACK = 4535;        // 启用地面监测单元主控(GSTR)--回复
    public static final int CODE_CLOSE_GSTR = 4532;            // 屏蔽地面监测单元主控(GSTR)
    public static final int CODE_CLOSE_GSTR_BACK = 4533;       // 屏蔽地面监测单元主控(GSTR)--回复
    public static final int CODE_SET_GSTR_NORMAL = 4538;       // 设置地面监测单元主控(GSTR)警号正常模式
    public static final int CODE_SET_GSTR_NORMAL_BACK = 4539;  // 设置地面监测单元主控(GSTR)警号正常模式--回复

    public static final int CODE_RESET_GMDT = 4530;            // 监控点--GMDT重启 --- 系统重启
    public static final int CODE_RESET_GMDT_BACK = 4531;       // 监控点--GMDT重启 --- 系统重启

    public static final int CODE_OPEN_NORMAL_MODE = 4536;      // 监控点--开启正常模式
    public static final int CODE_OPEN_NORMAL_MODE_BACK = 4537; // 监控点--开启正常模式

    public static final int CODE_GET_UPDATE_VERSION = 4566;       // 手动获取升级包版本号
    public static final int CODE_GET_UPDATE_VERSION_BACK = 4567;  // 手动获取升级包版本号-回复


    // ---------监控点详情页面 -- 状态发生变化的code --------begin----------

    public static final int CODE_STN_STATE_UP_LIGHT_OK = 3304;
    public static final int CODE_STN_STATE_UP_LIGHT_FAIL = 3305;
    public static final int CODE_STN_STATE_Down_LIGHT_OK = 3306;
    public static final int CODE_STN_STATE_Down_LIGHT_FAIL = 3307;

    public static final int CODE_STN_STATE_Alc_OK = 3308;
    public static final int CODE_STN_STATE_Alc_FAIL = 3309;

    public static final int CODE_STN_STATE_UP_LIGHT_Real_OK = 3940;
    public static final int CODE_STN_STATE_UP_LIGHT_Real_FAIL = 3941;
    public static final int CODE_STN_STATE_Down_LIGHT_Real_OK = 3942;
    public static final int CODE_STN_STATE_Down_LIGHT_Real_FAIL = 3943;

    public static final int CODE_STN_STATE_UP_Alc_OK = 3944;
    public static final int CODE_STN_STATE_UP_Alc_FAIL = 3945;
    public static final int CODE_STN_STATE_Down_Alc_OK = 3946;
    public static final int CODE_STN_STATE_Down_Alc_FAIL = 3947;

    public static final int CODE_STN_STATE_Conn_Gmdt_Adr_Conn_OK = 3554;
    public static final int CODE_STN_STATE_Conn_Gmdt_Adr_Conn_FAIL = 3555;

    public static final int CODE_STN_STATE_Conn_OK = 3556;
    public static final int CODE_STN_STATE_Conn_FAIL = 3557;

    public static final int CODE_STN_GmdtRunMode_Ok = 3991;
    public static final int CODE_STN_GmdtRunMode_SystemTest = 3992;
    public static final int CODE_STN_GmdtRunMode_UnitTest = 3993;
    public static final int CODE_STN_GmdtRunMode_Manual = 3994;

    public static final int CODE_STN_GmdtRunState_RT = 3995;
    public static final int CODE_STN_GmdtRunState_Sure_Alarm = 3996;
    public static final int CODE_STN_GmdtRunState_Cancel_Alarm = 3997;

    public static final int CODE_STN_Gmdt_Monitor_Ok = 3998;
    public static final int CODE_STN_Gmdt_Monitor_Fail = 3999;

    public static final int CODE_STN_Gmdt_Work_Ok = 4000;
    public static final int CODE_STN_Gmdt_Work_Fail = 4001;

    // 10：巡视，20：预警，21：小物体告警，22：工务告警，30：异物告警
    public static final int CODE_STN_Alarm_Xunshi = 10;
    public static final int CODE_STN_Alarm_Yujing = 20;
    public static final int CODE_STN_Alarm_SmallObject = 21;
    public static final int CODE_STN_Alarm_Gongwu = 22;
    public static final int CODE_STN_Alarm_Yiwu = 30;

    // 新增：
    public static final int CODE_STN_Up_Down_LightExistState_OK = 3344;   // 上、下行告警灯存在
    public static final int CODE_STN_Up_Down_LightExistState_FAIL = 3345; // 上、下行告警灯丢失

    public static final int CODE_STN_GPSState_OK = 3346;            // gps模块工作状态   正常
    public static final int CODE_STN_GPSState_FAIL = 3347;          // gps模块工作状态   异常
    public static final int CODE_STN_AlarmDevState_OK = 3348;       // 其他告警设备工作状态，3348-正常
    public static final int CODE_STN_AlarmDevState_FAIL = 3349;     // 其他告警设备工作状态，3349-异常

    public static final int CODE_STN_TrainPassing_ING = 3360;       // 火车通行状态   正在通过
    public static final int CODE_STN_TrainPassing_DONE = 3361;      // 火车通行状态   已通过
    public static final int CODE_STN_AlarmDevRealState_OPEN = 3952;  // 其他告警设备运行状态  打开
    public static final int CODE_STN_AlarmDevRealState_CLOSE = 3953; // 其他告警设备运行状态  关闭

    public static final int CODE_STN_SystemAlarm_OK = 4007;         // 系统告警状态  4007 系统正常
    public static final int CODE_STN_SystemAlarm_FAIL = 4006;       // 系统告警状态  4006 系统告警

    // ---------监控点详情页面 -- 状态发生变化的code --------end----------


    // --------单元详情页面 -- 状态发生变化的code --------begin----------

    public static final int CODE_Unit_State_ACB_OK = 3320;          // 音频衰减
    public static final int CODE_Unit_State_ACB_FAIL = 3321;        // 音频不衰减
    public static final int CODE_Unit_State_Alert_OK = 3322;        // 警号状态正常
    public static final int CODE_Unit_State_Alert_FAIL = 3323;      // 警号状态不正常

    public static final int CODE_Unit_State_Alert_Real_OK = 3948;   // 警号实际工作状态正常
    public static final int CODE_Unit_State_Alert_Real_FAIL = 3949; // 警号实际工作状态不正常
    public static final int CODE_Unit_State_Alf_OK = 3316;          // 区域内无异物
    public static final int CODE_Unit_State_Alf_FAIL = 3317;        // 区域内有异物

    public static final int CODE_Unit_State_2Iac_OK = 3518;         // Gstr和Iac连接状态    3518：正常
    public static final int CODE_Unit_State_2Iac_FAIL = 3519;       // Gstr和Iac连接状态   3519：异常
    public static final int CODE_Unit_State_2IO_OK = 3520;          // Gstr和IO连接状态     3520：正常
    public static final int CODE_Unit_State_2IO_FAIL = 3521;        // Gstr和IO连接状态	   3521：异常

    public static final int CODE_Unit_State_2Nspch_OK = 3524;       // Gstr和内部备用通道连接状态     3524：正常
    public static final int CODE_Unit_State_2Nspch_FAIL = 3525;     // Gstr和内部备用通道连接状态	   3525：异常
    public static final int CODE_Unit_State_2Radar_OK = 3510;       // Gstr和雷达连接状态      3510：正常
    public static final int CODE_Unit_State_2Radar_FAIL = 3511;     // Gstr和雷达连接状态     3511：异常


    public static final int CODE_Unit_State_Monitor_OK = 3970;      // Gstr监控状态     3970：正常
    public static final int CODE_Unit_State_Monitor_FAIL = 3971;    // Gstr监控状态	   3971：异常
    public static final int CODE_Unit_State_Work_OK = 4004;         // Gstr工作状态   4004：正常
    public static final int CODE_Unit_State_Work_FAIL = 4005;       // Gstr工作状态   4005：异常

    public static final int Gstr_State_Run_OK = 3964;           // Gstr工作模式   3964：正常
    public static final int Gstr_State_Run_FAIL = 3965;         // Gstr工作模式   3965：手动
    public static final int Gstr_State_Real_OK = 3950;          // Gstr工作模式   3950：工作
    public static final int Gstr_State_Real_FAIL = 3951;        // Gstr工作模式   3951：停止工作

    public static final int CODE_Unit_State_Iac_OK = 3302;      // Iac运行状态   3302：正常
    public static final int CODE_Unit_State_Iac_FAIL = 3303;    // Iac运行状态   3303：异常
    public static final int CODE_Unit_State_IO_OK = 3326;       // IO运行状态   3326：正常
    public static final int CODE_Unit_State_IO_FAIL = 3327;     // IO运行状态   3327：异常

    public static final int CODE_Unit_State_Nspch_OK = 3324;    // 内部备用通道状态   3324：正常
    public static final int CODE_Unit_State_Nspch_FAIL = 3325;  // 内部备用通道状态   3325：异常
    public static final int CODE_Pcdt2Gstr_OK = 3514;           // Pcdt和gstr连接状态 3514：正常
    public static final int CODE_Pcdt2Gstr_FAIL = 3515;         // Pcdt和gstr连接状态  3515：异常

    public static final int CODE_Unit_State_Occlude_OK = 3330;    // 雷达遮挡状态	 3330：未遮挡
    public static final int CODE_Unit_State_Occlude_FAIL = 3331;  // 雷达遮挡状态	 3331：遮挡

    public static final int CODE_Unit_State_Radar_OK = 3300;    // 雷达状态   3300：正常
    public static final int CODE_Unit_State_Radar_FAIL = 3301;  // 雷达状态   3301：异常

    public static final int CODE_Unit_State_VSB_OK = 3318;      // 内部备用通道状态   3318：正常
    public static final int CODE_Unit_State_VSB_FAIL = 3319;    // 内部备用通道状态   3319：异常

    public static final int CODE_Unit_GstrSysState_OK = 3966;    // Gstr系统运行模式：正常  新增
    public static final int CODE_Unit_GstrSysState_FAIL = 3967;  // Gstr系统运行模式：异常  新增

    public static final int CODE_Unit_Nspch485State_OK = 3338;    // 内部备用通道状态485  正常  新增
    public static final int CODE_Unit_Nspch485State_FAIL = 3339;  // 内部备用通道状态485  异常  新增


    // --------单元详情页面 -- 状态发生变化的code --------end----------

    // --------------确认告警 - - 取消告警=---------
    public static final int CODE_SURE_ALARM = 4900;          // 确认告警
    public static final int CODE_SURE_ALARM_BACK = 4901;     // 确认告警回复

    // -------------- 取消告警 ---------
    public static final int CODE_CLEAR_ALARM = 4528;           // 监控点--清除报警
    public static final int CODE_CLEAR_ALARM_BACK = 4529;      // 监控点--清除报警  --远程取消告警回复



    /// <summary>
    /// 根据ID综合查询已绑定FLV的防区列表
    /// </summary>
    public final static int GetMonitorAreaFlvList_ById = 139;




}
