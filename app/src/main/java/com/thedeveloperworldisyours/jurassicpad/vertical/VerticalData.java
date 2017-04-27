package com.thedeveloperworldisyours.jurassicpad.vertical;

/**
 * Created by javierg on 27/04/2017.
 */

public class VerticalData {

    private String mTitle;
    private String mSubTitle;

    public VerticalData(String title, String subTitle){
        mTitle = title;
        mSubTitle = subTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSubTitle() {
        return mSubTitle;
    }

    public void setmSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }
}
