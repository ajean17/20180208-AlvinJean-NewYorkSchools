package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HighSchoolListPresenter implements HighSchoolListContract.Presenter {
    private static final String TAG = HighSchoolListPresenter.class.getSimpleName() + "_TAG";
    private HighSchoolListContract.View view;
    private List<HighSchool> highSchoolList;
    @Inject
    OkHttpClient client;

    @Inject
    public HighSchoolListPresenter(OkHttpClient client) {
        this.client = client;
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
        final Request request = new Request.Builder().url("https://data.cityofnewyork.us/resource/97mf-9njv.json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<HighSchool>>(){}.getType();
                Collection<HighSchool> enums = gson.fromJson(json, collectionType);
                //Create an ArrayList from the JSON Array and populate the RecyclerView
                highSchoolList = new ArrayList<>(enums);
                view.updateHighSchoolList(highSchoolList);
            }
        });
    }
}
