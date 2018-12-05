package com.thdz.csc.gank.api;

import com.thdz.csc.app.MyApplication;
import com.thdz.csc.http.RetrofitUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc:
 * author:  Administrator
 * date:    2018/10/17  8:18
 */
public class GankApiUtil {

    private static Retrofit mRetrofit;

    private static GankApiService api;

    public static GankApiService getApi() {
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
                                // Rxjava2适配器
                                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
                    }
                }
            }
            api = mRetrofit.create(GankApiService.class);
        }
        return api;
    }

}
