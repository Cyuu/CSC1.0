package com.thdz.csc.map;

import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.thdz.csc.R;
import com.thdz.csc.bean.StationBean;

/**
 * desc:    机房站点的点聚合类
 * author:  Administrator
 * date:    2018/10/2  13:52
 */
public class StationClusterBean implements ClusterItem {

    private StationBean stationBean; // 用于展示信息
    private LatLng pos; // 用于展示坐标(百度坐标系)

    public StationClusterBean() {

    }

    public StationClusterBean(StationBean stationBean, LatLng pos) {
        this.stationBean = stationBean;
        this.pos = pos;
    }

    public StationBean getStationBean() {
        return stationBean;
    }

    public LatLng getPos() {
        return pos;
    }

    public void setPos(LatLng pos) {
        this.pos = pos;
    }

    public void setStationBean(StationBean stationBean) {
        this.stationBean = stationBean;
    }

    @Override
    public LatLng getPosition() {
        return stationBean == null ? null : new LatLng(stationBean.getLat(), stationBean.getLng());
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_jifang_name); // ic_jifang --> ic_jifang_name
    }
}
