package com.thdz.csc.bean;

import java.io.Serializable;
import java.util.List;

/**
 * desc:    json返回的通信基础类
 * author:  Administrator
 * date:    2018/10/9  16:50
 */
public class BaseListBean<T> implements Serializable {

    private String msg ;
    private int code;
    private List<T> data;

    public BaseListBean() {

    }

    public BaseListBean(String msg, int code, List<T> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
