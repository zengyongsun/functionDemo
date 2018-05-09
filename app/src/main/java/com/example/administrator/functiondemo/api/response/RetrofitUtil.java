package com.example.administrator.functiondemo.api.response;

import com.example.administrator.functiondemo.api.WanApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *    @author : Zeyo
 *     e-mail : zengyongsun@163.com
 *     time   : 2018/05/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RetrofitUtil {

    private static final int DEFAULT_TIME_OUT = 10;
    private static OkHttpClient.Builder mOkHttpClient;
    private static Retrofit mRetrofit;

    public void setOkHttpClient() {
        //创建 OkHttpClient
        mOkHttpClient = new OkHttpClient.Builder();
        mOkHttpClient.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        mOkHttpClient.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        mOkHttpClient.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
    }

    public static Retrofit getRetrofit() {
        // 创建Retrofit
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient.build())
                .baseUrl(WanApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        }
        return mRetrofit;
    }

}
