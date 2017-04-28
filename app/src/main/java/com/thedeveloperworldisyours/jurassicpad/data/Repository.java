package com.thedeveloperworldisyours.jurassicpad.data;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by javierg on 28/04/2017.
 */

public class Repository implements DataSource {

    public static final int TIMER_HTTP_OK = 5;

    @Override
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//                .client(getOkHttpClient())

    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .readTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .writeTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .build();
    }
}
