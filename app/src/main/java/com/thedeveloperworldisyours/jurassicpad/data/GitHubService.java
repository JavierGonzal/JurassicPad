package com.thedeveloperworldisyours.jurassicpad.data;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by javierg on 27/04/2017.
 */

public interface GitHubService {
    @GET("/search/users?")
    Observable<SearchResult> searchUsers(@Query("q") String query);
}
