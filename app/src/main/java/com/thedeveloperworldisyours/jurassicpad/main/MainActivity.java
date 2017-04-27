package com.thedeveloperworldisyours.jurassicpad.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.thedeveloperworldisyours.jurassicpad.R;
import com.thedeveloperworldisyours.jurassicpad.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        navigation();
        initFragment();

    }

    private void navigation() {
        // Set up the mToolbar.
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        checkNotNull(actionBar);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    public void initFragment() {

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_activity_contentFrame);
        mainFragment = mainFragment.newInstance();



        if (mainFragment != null) {
            Utils.addFragmentToActivity(getSupportFragmentManager(),
                    mainFragment, R.id.main_activity_contentFrame);
        }
//        initializeDagger();
//        new CustomerPresenter(mChat, customerFragment);
    }
}
