package com.thdz.csc.http;

import com.thdz.csc.app.MyApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc:    Retrofit封装
 * author:  Administrator
 * date:    2018/10/16  9:44
 */
public class RetrofitUtils {

    private static Retrofit mRetrofit;

    private static ApiService api;

    public static ApiService getApi() {
        if (api == null) {
            if (mRetrofit == null) {
                synchronized (RetrofitUtils.class) {
                    if (mRetrofit == null) {
                        mRetrofit = new Retrofit.Builder()
                                .baseUrl(MyApplication.getApp().getBaseUrl())
                                // 字符串解析
                                // .addConverterFactory(StringConverterFactory.create())
                                // Gson解析
                                .addConverterFactory(GsonConverterFactory.create())
                                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                    }
                }
            }
            api = mRetrofit.create(ApiService.class);
        }
        return api;
    }

}
