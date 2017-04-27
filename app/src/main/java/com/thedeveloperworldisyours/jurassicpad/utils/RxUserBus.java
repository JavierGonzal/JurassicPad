package com.thedeveloperworldisyours.jurassicpad.utils;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by javierg on 27/04/2017.
 */

public class RxUserBus {
    private static PublishSubject<String> bus = PublishSubject.create();

    private RxUserBus() {}

    public static Observable<String> sub() {
        return bus.asObservable();
    }

    public static void pub(String user) {
        bus.onNext(user);
    }
}
