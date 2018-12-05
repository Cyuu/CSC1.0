package com.thdz.csc.util;


import com.thdz.csc.bean.AlarmBean;
import com.thdz.csc.bean.DeviceBean;
import com.thdz.csc.bean.StationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:    测试数据
 * author:  Administrator
 * date:    2018/8/6  11:38
 */
public class TestUtil {


    public static List<AlarmBean> getAlarmList() {
        List<AlarmBean> list = new ArrayList<AlarmBean>();
        for (int i = 0; i < 4; i++) {
            AlarmBean bean = new AlarmBean(
                    "1" + i, "1" + i, "环境温度", "温度过低告警",
                    "1级", "2018-8-21 11:33:23", "2018-8-23 9:13:23", "领导",
                    "123", "机房空调", "1234" + i, "天河机房1号", "已开通");

            list.add(bean);
        }
        return list;
    }


    public static List<DeviceBean> getDeviceList() {
        List<DeviceBean> list = new ArrayList<DeviceBean>();
        for (int i = 0; i < 4; i++) {
            DeviceBean bean = new DeviceBean( 1 + i, "机房空调" + i, "保定市高新区恒滨路9"+i+"号", 21+ i,  "保定大机房01号", 1234 + i, "运行正常");
            list.add(bean);
        }
        return list;
    }


    public static List<StationBean> getStationList() {
        List<StationBean> list = new ArrayList<StationBean>();

        StationBean bean1 = new StationBean(1231, "天河站01", 31203, 1813, "已开通", "13", 38.927763, 115.469205);
        list.add(bean1);
        StationBean bean2 = new StationBean(1233, "天河站02", 23123, 20313, "已开通", "12", 38.957763, 115.459205);
        list.add(bean2);
        StationBean bean3 = new StationBean(3434, "天河站03", 63123, 62313, "已开通", "34", 38.787763, 115.669205);
        list.add(bean3);
        StationBean bean4 = new StationBean(5433, "天河站04", 23123, 72313, "已开通", "45", 38.447763, 115.559205);
        list.add(bean4);
        StationBean bean5 = new StationBean(7632, "天河站05", 34123, 92313, "已开通", "214", 38.887763, 115.339205);
        list.add(bean5);
        StationBean bean6 = new StationBean(9822, "天河站06", 73123, 52313, "已开通", "765", 38.327763, 115.823205);
        list.add(bean6);
        StationBean bean7 = new StationBean(5876, "天河站07", 93123, 42313, "已开通", "654", 38.117763, 115.229205);
        list.add(bean7);
        return list;
    }


}
