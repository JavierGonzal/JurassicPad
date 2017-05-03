package com.thedeveloperworldisyours.jurassicpad.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.thedeveloperworldisyours.jurassicpad.JurassicPadApplication;
import com.thedeveloperworldisyours.jurassicpad.R;
import com.thedeveloperworldisyours.jurassicpad.data.Repository;
import com.thedeveloperworldisyours.jurassicpad.schedulers.BaseSchedulerProvider;
import com.thedeveloperworldisyours.jurassicpad.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_toolbar)
    Toolbar mToolbar;

    @Inject
    Repository mRepository;

    @Inject
    BaseSchedulerProvider mSchedulerProvider;


//    @BindView(R.id.activity_main_search)
//    SearchView mSearchView;

    MainFragment mMainFragment;

    private String mSearchString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        initializeDagger();
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

        mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_activity_contentFrame);
        mMainFragment = mMainFragment.newInstance();



        if (mMainFragment != null) {
            Utils.addFragmentToActivity(getSupportFragmentManager(),
                    mMainFragment, R.id.main_activity_contentFrame);
        }
        new MainPresenter(mRepository, mMainFragment,mSchedulerProvider);
    }

    private void initializeDagger() {
        JurassicPadApplication app = (JurassicPadApplication) getApplication();
        app.getAppComponent().inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                mMainFragment.refresh(newText);
//                return false;
//            }
//        });

        return super.onCreateOptionsMenu(menu);
    }
}
