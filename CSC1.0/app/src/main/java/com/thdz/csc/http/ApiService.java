package com.thdz.csc.http;

import com.thdz.csc.bean.AlarmBean;
import com.thdz.csc.bean.BaseBean;
import com.thdz.csc.bean.BaseListBean;
import com.thdz.csc.bean.DeviceBean;
import com.thdz.csc.bean.LoginBean;
import com.thdz.csc.bean.StationBean;
import com.thdz.csc.bean.gank.GankBaseInfo;
import com.thdz.csc.bean.gank.GankCateInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * desc:    Http接口
 * author:  Administrator
 * date:    2018/10/9  16:44
 * ----------------------<br>
 * 如果定义的属性里和json返回的字段不同，则必须要用这个注解 @SerializedName
 */
public interface ApiService {

    /**
     * 登录
     */
    @GET("user/login")
    Call<BaseBean<LoginBean>> loginGet(
            @Query("username") String username,
            @Query("pwd") String pwd,
            @Query("cid") String cid);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    Call<BaseBean<LoginBean>> loginPost(
            @Field("username") String username,
            @Field("pwd") String pwd,
            @Field("cid") String cid);

    /**
     * 登出
     */
    @GET("user/logout")
    Call<BaseBean<LoginBean>> logoutGet(
            @Query("username") String username,
            @Query("pwd") String pwd,
            @Query("cid") String cid);

    /**
     * 登出
     */
    @FormUrlEncoded
    @POST("user/logout")
    Call<BaseBean<LoginBean>> logoutPost(
            @Field("username") String username,
            @Field("pwd") String pwd,
            @Field("cid") String cid);

    /**
     * 最新告警
     */
    @GET("alarm/new")
    Call<BaseListBean<AlarmBean>> getNewAlarmGet(@Query("uid") String uid);

    /**
     * 最新告警
     */
    @FormUrlEncoded
    @POST("alarm/new")
    Call<BaseListBean<AlarmBean>> getNewAlarmPost(@Field("uid") String uid);

    /**
     * 历史告警列表
     */
    @GET("alarm/hisAlarm")
    Call<BaseListBean<AlarmBean>> getHisAlarmGet(
            @Query("uid") String uid,
            @Query("stnId") String stnId,
            @Query("level") String level,
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime);

    /**
     * 历史告警列表
     */
    @FormUrlEncoded
    @POST("alarm/hisAlarm")
    Call<BaseListBean<AlarmBean>> getHisAlarmPost(
            @Field("uid") String uid,
            @Field("stnId") String stnId,
            @Field("level") String level,
            @Field("beginTime") String beginTime,
            @Field("endTime") String endTime);

    /**
     * 站点列表
     */
    @GET("station/stations")
    Call<BaseListBean<StationBean>> getStationListGet(
            @Query("uid") String uid,
            @Query("keyword") String keyword);

    /**
     * 站点列表
     */
    @FormUrlEncoded
    @POST("station/stations")
    Call<BaseListBean<StationBean>> getStationListPost(
            @Field("uid") String uid,
            @Field("keyword") String keyword);

    /**
     * 设备列表
     */
    @GET("station/devices")
    Call<BaseListBean<DeviceBean>> getDevicenListGet(
            @Query("uid") String uid,
            @Query("stnId") String stnId);

    /**
     * 设备列表
     */
    @FormUrlEncoded
    @POST("station/devices")
    Call<BaseListBean<DeviceBean>> getDevicenListPost(
            @Field("uid") String uid,
            @Field("stnId") String stnId);

    /**
     * 设备 - 控制命令1
     */
    @GET("device/cmd1")
    Call<BaseListBean<String>> commitCmd1Get(
            @Query("uid") String uid,
            @Query("code") String code,
            @Query("value") String value);

    /**
     * 设备 - 控制命令1
     */
    @FormUrlEncoded
    @POST("device/cmd1")
    Call<BaseListBean<String>> commitCmd1(
            @Field("uid") String uid,
            @Field("code") String code,
            @Field("value") String value);

    /**
     * 设备 - 控制命令2
     */
    @GET("device/cmd2")
    Call<BaseListBean<String>> commitCmd2Get(
            @Query("uid") String uid,
            @Query("code") String code,
            @Query("value") String value);

    /**
     * 设备 - 控制命令2
     */
    @FormUrlEncoded
    @POST("device/cmd2")
    Call<BaseListBean<String>> commitCmd2(
            @Field("uid") String uid,
            @Field("code") String code,
            @Field("value") String value);


    ////////////// GANK.IO /////////////
    // 字符串解析的转换器
    @GET("api/xiandu/categories")
    Call<String> getCategories();

    // 字符串解析的转换器
    @GET("api/xiandu/categories")
    Call<GankBaseInfo<GankCateInfo>> getCates();


}
