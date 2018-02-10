package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteAPI;
import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class SATDetailPresenter implements SATDetailContract.Presenter {
    private static final String TAG = SATDetailPresenter.class.getSimpleName() + "_TAG";
    private SATDetailContract.View view;
    RemoteDataProvider remoteDataProvider;
    private List<SATScore> satScoreList;

    @Inject
    public SATDetailPresenter(RemoteDataProvider remoteDataProvider) {
        this.remoteDataProvider = remoteDataProvider;
    }

    @Override
    public void attachView(SATDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    //Retrieve the SAT Scores from the JSON API URL
    @Override
    public void getSATData(final String highSchoolDBN) {
        Retrofit retrofit = remoteDataProvider.create();
        final RemoteAPI remoteAPI = retrofit.create(RemoteAPI.class);
        retrofit2.Call<SATScore[]> call = remoteAPI.getSATScores("734v-jeq5");
        call.enqueue(new retrofit2.Callback<SATScore[]>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<SATScore[]> call, @NonNull retrofit2.Response<SATScore[]> response) {
                if(response.body() != null) {
                    satScoreList = new ArrayList<>(Arrays.asList(response.body()));
                    SATScore scoreToShow = new SATScore();
                    //Find the SAT scores based on the DBN of the school selected
                    //If nothing is found, notify the user in the UI
                    scoreToShow.setSchoolName("NO SAT INFORMATION AVAILABLE");
                    for(SATScore satScore: satScoreList) {
                        if(satScore.getDbn().equals(highSchoolDBN)){
                            scoreToShow = satScore;
                            break;
                        }
                    }
                    view.showScores(scoreToShow);
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<SATScore[]> call, @NonNull Throwable t) {

            }
        });
    }
}
