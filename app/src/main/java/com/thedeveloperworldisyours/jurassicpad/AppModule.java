package com.thedeveloperworldisyours.jurassicpad;

import com.thedeveloperworldisyours.jurassicpad.data.Repository;
import com.thedeveloperworldisyours.jurassicpad.schedulers.BaseSchedulerProvider;
import com.thedeveloperworldisyours.jurassicpad.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javierg on 03/05/2017.
 */
@Module
public class AppModule {

private JurassicPadApplication mJurassicPadApplication;

    public AppModule(JurassicPadApplication mJurassicPadApplication) {
        this.mJurassicPadApplication = mJurassicPadApplication;
    }

    @Singleton
    @Provides
    Repository provideRepository() {
        return new Repository();
    }

    @Singleton
    @Provides
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
