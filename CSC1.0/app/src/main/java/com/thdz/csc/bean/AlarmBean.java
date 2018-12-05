package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * zzx返回的最新告警列表的item类
 */

public class AlarmBean implements Serializable {
    private String alarmRecordId; // 告警id
    private String alarmTypeCode; // 告警类型编码
    private String alarmTypeName; // 告警类型名称
    private String alarmName; // 告警名称
    private String alarmLevel; // 告警级别
    private String beginTime; // 开始时间
    private String endTime; // 结束时间
    private String userName; // 用户名称
    private String deviceId; // 设备id
    private String deviceName; // 设备名称
    private String stationId; // 站点id
    private String stationName; // 站点名称
    private String openState; // 开通状态


    public AlarmBean() {
    }
 

    public AlarmBean(String alarmRecordId, String alarmTypeCode, String alarmTypeName, String alarmName, String alarmLevel, String beginTime, String endTime, String userName, String deviceId, String deviceName, String stationId, String stationName, String openState) {
        this.alarmRecordId = alarmRecordId;
        this.alarmTypeCode = alarmTypeCode;
        this.alarmTypeName = alarmTypeName;
        this.alarmName = alarmName;
        this.alarmLevel = alarmLevel;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.userName = userName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.stationId = stationId;
        this.stationName = stationName;
        this.openState = openState;
    }

    public String getOpenState() {
        return openState;
    }

    public void setOpenState(String openState) {
        this.openState = openState;
    }

    public String getAlarmRecordId() {
        return alarmRecordId;
    }

    public void setAlarmRecordId(String alarmRecordId) {
        this.alarmRecordId = alarmRecordId;
    }

    public String getAlarmTypeCode() {
        return alarmTypeCode;
    }

    public void setAlarmTypeCode(String alarmTypeCode) {
        this.alarmTypeCode = alarmTypeCode;
    }

    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
