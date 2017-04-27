package com.thedeveloperworldisyours.jurassicpad.data;

import android.util.LruCache;

import retrofit.Retrofit;
import rx.Observable;

/**
 * Created by javierg on 27/04/2017.
 */

public class GitHubInteractor {

    private LruCache<String, SearchResult> cache;
    private GitHubService service;

    public GitHubInteractor(Retrofit retrofit, LruCache<String, SearchResult> cache) {
        this.cache = cache;
        service = retrofit.create(GitHubService.class);
    }

    public Observable<SearchResult> searchUsers(final String query) {
        return Observable.concat(cachedResults(query), networkResults(query)).first();
    }

    private Observable<SearchResult> cachedResults(String query) {
        return Observable.just(cache.get(query))
                .filter((SearchResult result) -> {
                        return result != null;
                    });
    }

    private Observable<SearchResult> networkResults(final String query) {
        return service.searchUsers(query)
                .doOnNext((SearchResult result) ->
                        cache.put(query, result));
    }
}
