package com.thedeveloperworldisyours.jurassicpad.data;

import okhttp3.OkHttpClient;
import retrofit.Retrofit;

/**
 * Created by javierg on 28/04/2017.
 */

public interface DataSource {

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

}
