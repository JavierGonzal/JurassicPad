package com.thedeveloperworldisyours.jurassicpad;

import com.thedeveloperworldisyours.jurassicpad.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javierg on 03/05/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
