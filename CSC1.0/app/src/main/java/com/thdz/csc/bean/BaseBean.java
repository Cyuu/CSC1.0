package com.thdz.csc.bean;

import java.io.Serializable;

/**
 * desc:    json返回的通信基础类
 * author:  Administrator
 * date:    2018/10/9  16:50
 */
public class BaseBean<T> implements Serializable {

    private String msg ;
    private int code;
    private T data;

    public BaseBean() {

    }

    public BaseBean(String msg, int code, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
