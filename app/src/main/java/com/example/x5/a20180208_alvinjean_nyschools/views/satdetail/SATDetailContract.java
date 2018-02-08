package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.example.x5.a20180208_alvinjean_nyschools.utils.BasePresenter;
import com.example.x5.a20180208_alvinjean_nyschools.utils.BaseView;

public interface SATDetailContract {
    interface View extends BaseView {
        void showScores(SATScore satScoreInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getSATData(String highSchoolName);

    }
}
