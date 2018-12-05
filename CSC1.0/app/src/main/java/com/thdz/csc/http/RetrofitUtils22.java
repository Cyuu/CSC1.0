package com.thdz.csc.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc:    Retrofit封装22
 * author:  Administrator
 * date:    2018/10/16  11:44
 */
public class RetrofitUtils22 {

    public static final String BASE_URL = "";
    public static final int IIMEOUT = 60;
    private static Retrofit mRetrofit;

    private static volatile RetrofitUtils22 mInstance;

    private static RetrofitUtils22 getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils22();
                }
            }
        }
        return mInstance;
    }

    private RetrofitUtils22() {
        initRetrofit();
    }

    private void initRetrofit() {
        // 自定义OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(IIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(IIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(IIMEOUT, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    /**
     * 创建API
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    private static ApiService api;

    public static ApiService getApi() {
        if (api == null) {
            api = mRetrofit.create(ApiService.class);
        }
        return api;
    }

}
