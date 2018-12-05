package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * 站点bean
 */
public class StationBean implements Serializable {

    private int StnId; // 1
    private String StnName; // 泗亭1号
    private int StnNo; // 131001

    private int deviceId;
    private String stnState;
    private String deviceCount;
    private double lat;
    private double lng;

    public StationBean() {
        super();
    }

    public StationBean(int stnId, String stnName, int stnNo, int deviceId, String stnState, String deviceCount, double lat, double lng) {
        StnId = stnId;
        StnName = stnName;
        StnNo = stnNo;
        this.deviceId = deviceId;
        this.stnState = stnState;
        this.deviceCount = deviceCount;
        this.lat = lat;
        this.lng = lng;
    }

    public int getStnId() {
        return StnId;
    }

    public void setStnId(int stnId) {
        StnId = stnId;
    }

    public String getStnName() {
        return StnName;
    }

    public void setStnName(String stnName) {
        StnName = stnName;
    }

    public int getStnNo() {
        return StnNo;
    }

    public void setStnNo(int stnNo) {
        StnNo = stnNo;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getStnState() {
        return stnState;
    }

    public void setStnState(String stnState) {
        this.stnState = stnState;
    }

    public String getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(String deviceCount) {
        this.deviceCount = deviceCount;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof StationBean)) {
            return false;
        } else {
            StationBean item = (StationBean) arg0;
            return item.getStnId() == this.StnId;
        }
    }

}
