package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteAPI;
import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

public class HighSchoolListPresenter implements HighSchoolListContract.Presenter {
    private static final String TAG = HighSchoolListPresenter.class.getSimpleName() + "_TAG";
    private HighSchoolListContract.View view;
    private List<HighSchool> highSchoolList;

    RemoteDataProvider remoteDataProvider;

    @Inject
    public HighSchoolListPresenter(RemoteDataProvider remoteDataProvider) {

        this.remoteDataProvider = remoteDataProvider;
    }

    @Override
    public void attachView(HighSchoolListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    //Retreive HighSchool data via JSON API URL
    @Override
    public void getHighSchools() {
        Retrofit retrofit = remoteDataProvider.create();
        RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        Call<HighSchool[]> call = remoteAPI.getNewYorkHighSchools("97mf-9njv");
        call.enqueue(new retrofit2.Callback<HighSchool[]>() {
            @Override
            public void onResponse(@NonNull Call<HighSchool[]> call, @NonNull retrofit2.Response<HighSchool[]> response) {
                if(response.body() != null) {
                    highSchoolList = new ArrayList<>(Arrays.asList(response.body()));
                    view.updateHighSchoolList(highSchoolList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<HighSchool[]> call, @NonNull Throwable t) {

            }
        });
    }
}
