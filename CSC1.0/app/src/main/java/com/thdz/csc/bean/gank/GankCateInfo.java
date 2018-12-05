package com.thdz.csc.bean.gank;

import java.io.Serializable;

/**
 * desc:
 * author:  Administrator
 * date:    2018/10/16  17:56
 */
public class GankCateInfo implements Serializable {

    private String _id;
    private String en_name;
    private String name;
    private int rank;

    public GankCateInfo() {

    }

    public GankCateInfo(String _id, String en_name, String name, int rank) {
        this._id = _id;
        this.en_name = en_name;
        this.name = name;
        this.rank = rank;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "GankCateInfo{" +
                "_id='" + _id + '\'' +
                ", en_name='" + en_name + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}
