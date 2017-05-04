package com.thedeveloperworldisyours.jurassicpad;

import android.util.LruCache;

import com.google.gson.Gson;
import com.thedeveloperworldisyours.jurassicpad.data.GitHubInteractor;
import com.thedeveloperworldisyours.jurassicpad.data.GitHubService;
import com.thedeveloperworldisyours.jurassicpad.data.SearchItem;
import com.thedeveloperworldisyours.jurassicpad.data.SearchResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import java.util.Collections;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.functions.Action1;
import rx.observers.TestSubscriber;

/**
 * Created by javierg on 04/05/2017.
 */

public class GitHubInteractorTest {

    @Mock
    private LruCache<String, SearchResult> cache;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        when(cache.get(anyString())).thenReturn(null);

    }

    @Test
    public void mockServiceTest(){

        SearchResult result = new SearchResult();
        result.setTotalCount(1);
        SearchItem item = new SearchItem();
        item.setLogin("cabezas");
        item.setAvatarUrl("https://avatars.githubusercontent.com/u/583231?v\u003d3");
        result.setItems(Collections.singletonList(item));

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(result)));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(mockWebServer.url("df")))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestSubscriber<SearchResult> subscriber = new TestSubscriber<>();
        new GitHubInteractor(retrofit, cache).searchUsers("cabezas").subscribe(subscriber);

        subscriber.assertValue(result);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
