package com.thedeveloperworldisyours.jurassicpad.main;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.LruCache;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.thedeveloperworldisyours.jurassicpad.data.GitHubInteractor;
import com.thedeveloperworldisyours.jurassicpad.data.Repository;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;
import com.thedeveloperworldisyours.jurassicpad.data.SearchResult;
import com.thedeveloperworldisyours.jurassicpad.schedulers.BaseSchedulerProvider;
import com.thedeveloperworldisyours.jurassicpad.data.RxUserBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by javierg on 27/04/2017.
 */

public class MainPresenter implements MainContract.Presenter{

    private CompositeSubscription mSubscriptions;

    private Repository mRepository;

    private MainContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;


    public MainPresenter(@NonNull Repository repository, @NonNull MainContract.View view, @NonNull BaseSchedulerProvider provider ) {
        mRepository = checkNotNull(repository, "repository cannot be null");
        mView = checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = checkNotNull(provider, "schedulerProvider cannot be null");
            mSubscriptions = new CompositeSubscription();
            mView.setPresenter(this);
    }

    @Override
    public void subscribe() {


    }

    @Override
    public void unSubscribe() {
        mSubscriptions.unsubscribe();
    }

    @Override
    public void listenerSearch(EditText editText) {
        LruCache<String, SearchResult> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB

        final GitHubInteractor interactor = new GitHubInteractor(mRepository.getRetrofit(), cache);

        mSubscriptions.add(RxUserBus.sub().subscribe((String s) ->{
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }));

        mSubscriptions.add(RxTextView.textChanges(editText)
                .observeOn(mSchedulerProvider.io())
                .debounce(1, TimeUnit.SECONDS)
                .filter((CharSequence charSequence) ->
                        charSequence.length() > 0
                )
                .switchMap((CharSequence charSequence) ->
                        interactor.searchUsers(charSequence.toString())
                )
                .flatMap((SearchResult searchResult) ->
                        Observable.from(searchResult.getItems())
                                .limit(20)
                                .toList()
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<SearchItem> searchItems) ->
                        mView.showUser(searchItems)
                , (Throwable throwable) ->
                        Log.e(MainActivity.class.getName(), throwable.getMessage())

                ));

    }
}
