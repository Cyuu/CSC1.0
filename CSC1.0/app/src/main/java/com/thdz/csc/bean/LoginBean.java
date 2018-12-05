package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * desc:    登录
 * author:  Administrator
 * date:    2018/10/9  16:58
 */
public class LoginBean implements Serializable {

    private String username;
    private String pwd;
    private String cid; // 个推ClientId
    private String uid; // uid
    private String token; // 令牌
    private String access; // 权限
    private String contact; // 联系方式

    public LoginBean() {

    }

    public LoginBean(String username, String pwd, String cid, String uid, String token, String access, String contact) {
        this.username = username;
        this.pwd = pwd;
        this.cid = cid;
        this.uid = uid;
        this.token = token;
        this.access = access;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
