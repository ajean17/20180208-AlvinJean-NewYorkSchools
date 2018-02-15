package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SATDetailPresenter implements SATDetailContract.Presenter {
    private static final String TAG = SATDetailPresenter.class.getSimpleName() + "_TAG";
    private SATDetailContract.View view;
    RemoteDataProvider remoteDataProvider;

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
        Observer<SATScore> satScoreObserver = new Observer<SATScore>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(SATScore satScore) {
                Log.d(TAG, "onNext: " + satScore.getSchoolName());
                view.showScores(satScore);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        remoteDataProvider.getSATScoreArray()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<SATScore[], ObservableSource<SATScore>>() {
                    @Override
                    public ObservableSource<SATScore> apply(SATScore[] satScores) throws Exception {
                        return Observable.fromArray(satScores);
                    }
                })
                .filter(satScore -> satScore.getDbn().equals(highSchoolDBN))
                .subscribe(satScoreObserver);
    }
}
