package com.thedeveloperworldisyours.jurassicpad.main;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.LruCache;
import android.widget.Toast;

import com.thedeveloperworldisyours.jurassicpad.data.GitHubInteractor;
import com.thedeveloperworldisyours.jurassicpad.data.Repository;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;
import com.thedeveloperworldisyours.jurassicpad.data.SearchResult;
import com.thedeveloperworldisyours.jurassicpad.utils.RxUserBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by javierg on 27/04/2017.
 */

public class MainPresenter implements MainContract.Presenter{

    private CompositeSubscription mSubscriptions;

    private Repository mRepository;

    private MainContract.View mView;

    public MainPresenter(@NonNull Repository repository, @NonNull MainContract.View view) {
        mRepository = checkNotNull(repository, "repository cannot be null");
        mView = checkNotNull(view, "view cannot be null!");

            mSubscriptions = new CompositeSubscription();
            mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        LruCache<String, SearchResult> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB

        final GitHubInteractor interactor = new GitHubInteractor(mRepository.getRetrofit(), cache);

        mSubscriptions.add(RxUserBus.sub().subscribe(new Action1<String>() {
            @Override public void call(String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }));

        mSubscriptions.add(RxTextView.textChanges(searchBar)
                .observeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override public Boolean call(CharSequence charSequence) {
                        return charSequence.length() > 0;
                    }
                })
                .switchMap(new Func1<CharSequence, Observable<SearchResult>>() {
                    @Override public Observable<SearchResult> call(CharSequence charSequence) {
                        return interactor.searchUsers(charSequence.toString());
                    }
                })
                .flatMap(new Func1<SearchResult, Observable<List<SearchItem>>>() {
                    @Override public Observable<List<SearchItem>> call(SearchResult searchResult) {
                        return Observable.from(searchResult.getItems())
                                .limit(20)
                                .toList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SearchItem>>() {
                    @Override public void call(List<SearchItem> searchItems) {
                        mView.showUser();
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(MainActivity.class.getName(), throwable.getMessage());
                    }
                }));
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.unsubscribe();
    }

}
