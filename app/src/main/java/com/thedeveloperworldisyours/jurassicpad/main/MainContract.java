package com.thedeveloperworldisyours.jurassicpad.main;

import com.thedeveloperworldisyours.jurassicpad.BasePresenter;
import com.thedeveloperworldisyours.jurassicpad.BaseView;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;

import java.util.List;

/**
 * Created by javierg on 27/04/2017.
 */

public interface MainContract {

    interface Presenter extends BasePresenter {

        void listenerSearch(String string);

    }

    interface View extends BaseView<Presenter> {
        void showUser(List<SearchItem> list);

        void showError();

        void setLoadingIndicator(boolean active);
    }
}
