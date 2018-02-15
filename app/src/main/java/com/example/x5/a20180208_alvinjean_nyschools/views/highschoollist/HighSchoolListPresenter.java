package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HighSchoolListPresenter implements HighSchoolListContract.Presenter {
    private static final String TAG = HighSchoolListPresenter.class.getSimpleName() + "_TAG";
    private HighSchoolListContract.View view;
    private List<HighSchool> highSchoolList = new ArrayList<>();

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

        Observer<HighSchool> highSchoolObserver = new Observer<HighSchool>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: Highschool");
            }

            @Override
            public void onNext(HighSchool highSchool) {
                highSchoolList.add(highSchool);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: " + highSchoolList.size());
                view.updateHighSchoolList(highSchoolList);
            }
        };

        remoteDataProvider.getHighSchoolArray()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<HighSchool[], ObservableSource<HighSchool>>() {
                    @Override
                    public ObservableSource<HighSchool> apply(HighSchool[] highSchools) throws Exception {
                        return Observable.fromArray(highSchools);
                    }
                })
                .subscribe(highSchoolObserver);

    }
}
