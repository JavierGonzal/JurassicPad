package com.thedeveloperworldisyours.jurassicpad;

import android.app.Application;

/**
 * Created by javierg on 03/05/2017.
 */

public class JurassicPadApplication extends Application {

    AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
