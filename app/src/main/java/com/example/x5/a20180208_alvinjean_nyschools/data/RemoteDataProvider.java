package com.example.x5.a20180208_alvinjean_nyschools.data;

import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataProvider {

    private Retrofit retrofit;
    private static final String HIGH_SCHOOL_PATH = "97mf-9njv";
    private static final String SAT_SCORE_PATH = "734v-jeq5";

    public RemoteDataProvider(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<HighSchool[]> getHighSchoolArray() {
        RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        return remoteAPI.getHighSchoolsOberservable(HIGH_SCHOOL_PATH);
    }

    public Observable<SATScore[]> getSATScoreArray() {
        RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        return remoteAPI.getSATScoreObervable(SAT_SCORE_PATH);
    }

}
