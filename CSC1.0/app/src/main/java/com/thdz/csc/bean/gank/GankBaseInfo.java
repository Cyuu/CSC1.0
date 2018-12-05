package com.thdz.csc.bean.gank;

import java.io.Serializable;

/**
 * desc:
 * author:  Administrator
 * date:    2018/10/16  17:54
 */
public class GankBaseInfo<T> implements Serializable {

    private boolean error;
    private T results;

    public GankBaseInfo() {
    }

    public GankBaseInfo(boolean error, T results) {
        this.error = error;
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankBaseInfo{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
