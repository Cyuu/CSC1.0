package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * dejia -- zzx -- 推送返回的bean
 */

public class PushBeanBase implements Serializable {

    private String StnNo;       // 站点
    private String UnitNo;      // 单元
    private String CodeId;      // code, 区分是什么业务
    private String CodeTm;      // time

    private String UserName;    // 
    private String ClientId ;   //
    private String Platform;    //

    private String Param1;
    private String Param2;
    private String Param3;
    private String Param4;


    public PushBeanBase() {
    }

    public PushBeanBase(String stnNo, String unitNo, String codeId, String codeTm, String userName, String clientId, String platform, String param1, String param2, String param3, String param4) {
        StnNo = stnNo;
        UnitNo = unitNo;
        CodeId = codeId;
        CodeTm = codeTm;
        UserName = userName;
        ClientId = clientId;
        Platform = platform;
        Param1 = param1;
        Param2 = param2;
        Param3 = param3;
        Param4 = param4;
    }

    public String getStnNo() {
        return StnNo;
    }

    public void setStnNo(String stnNo) {
        StnNo = stnNo;
    }

    public String getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(String unitNo) {
        UnitNo = unitNo;
    }

    public String getCodeId() {
        return CodeId;
    }

    public void setCodeId(String codeId) {
        CodeId = codeId;
    }

    public String getCodeTm() {
        return CodeTm;
    }

    public void setCodeTm(String codeTm) {
        CodeTm = codeTm;
    }

    public String getParam1() {
        return Param1;
    }

    public void setParam1(String param1) {
        Param1 = param1;
    }

    public String getParam2() {
        return Param2;
    }

    public void setParam2(String param2) {
        Param2 = param2;
    }

    public String getParam3() {
        return Param3;
    }

    public void setParam3(String param3) {
        Param3 = param3;
    }

    public String getParam4() {
        return Param4;
    }

    public void setParam4(String param4) {
        Param4 = param4;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    @Override
    public String toString() {
        return "PushBeanBase{" +
                "StnNo='" + StnNo + '\'' +
                ", UnitNo='" + UnitNo + '\'' +
                ", CodeId='" + CodeId + '\'' +
                ", CodeTm='" + CodeTm + '\'' +
                ", UserName='" + UserName + '\'' +
                ", ClientId='" + ClientId + '\'' +
                ", Platform='" + Platform + '\'' +
                ", Param1='" + Param1 + '\'' +
                ", Param2='" + Param2 + '\'' +
                ", Param3='" + Param3 + '\'' +
                ", Param4='" + Param4 + '\'' +
                '}';
    }

}
