package com.thedeveloperworldisyours.jurassicpad.main;

import com.thedeveloperworldisyours.jurassicpad.BasePresenter;
import com.thedeveloperworldisyours.jurassicpad.BaseView;

/**
 * Created by javierg on 27/04/2017.
 */

public interface MainContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void showUser();

        void showError();

        void setLoadingIndicator(boolean active);
    }
}
