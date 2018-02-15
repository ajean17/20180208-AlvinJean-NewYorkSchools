package com.example.x5.a20180208_alvinjean_nyschools.data;

import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.google.gson.Gson;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataProvider {

    //String path;
    Context context;

    public RemoteDataProvider(Context context) {
        //this.path = path;
        this.context = context;
    }

    public Retrofit create() {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public Observable<HighSchool[]> getHighSchoolArray() {
        Retrofit retrofit = create();
        RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        return remoteAPI.getHighSchoolsOberservable("97mf-9njv");
    }

    public Observable<SATScore[]> getSATScoreArray() {
        Retrofit retrofit = create();
        RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        return remoteAPI.getSATScoreObervable("734v-jeq5");
    }

}
