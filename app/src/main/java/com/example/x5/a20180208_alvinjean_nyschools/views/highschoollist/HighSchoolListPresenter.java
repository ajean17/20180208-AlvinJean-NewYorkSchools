package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteAPI;
import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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
        /*Retrofit retrofit = remoteDataProvider.create();
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
        });*/
        Observer<HighSchool> observer = new Observer<HighSchool>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(HighSchool highSchool) {
                Log.d(TAG, "onNext: ");
                //highSchoolList.add(highSchool);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                //view.updateHighSchoolList(highSchoolList);
            }
        };

        Observer<HighSchool[]> observer1 = new Observer<HighSchool[]>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(HighSchool[] highSchools) {
                Log.d(TAG, "onNext: ");
                highSchoolList = new ArrayList<>(Arrays.asList(highSchools));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                view.updateHighSchoolList(highSchoolList);
            }
        };

        remoteDataProvider.getHighSchoolArray()
                //.observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.io())
                /*.flatMap(new Function<HighSchool[], Observable<HighSchool>>() {

                    @Override
                    public Observable<HighSchool> apply(HighSchool[] highSchools) throws Exception {
                        Log.d(TAG, "apply: size = " + highSchools.length);
                        return Observable.fromArray(highSchools);
                    }
                })
                .subscribe(observer);*/
                .subscribe(observer1);
    }
}
