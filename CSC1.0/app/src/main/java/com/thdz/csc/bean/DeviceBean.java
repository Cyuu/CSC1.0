package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * zzx返回的最新告警列表的item类
 */
public class DeviceBean implements Serializable {

    private int deviceId;       // 设备id
    private String deviceName;  // 设备名称
    private String deviceAddr;  // 设备地址

    private int stationId;  // 站点id
    private String stationName;  // 站点名称

    private int stateId;      // 状态
    private String stateName; // 状态

    public DeviceBean() {

    }

    public DeviceBean(int deviceId, String deviceName, String deviceAddr, int stationId, String stationName, int stateId, String stateName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceAddr = deviceAddr;
        this.stationId = stationId;
        this.stationName = stationName;
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}