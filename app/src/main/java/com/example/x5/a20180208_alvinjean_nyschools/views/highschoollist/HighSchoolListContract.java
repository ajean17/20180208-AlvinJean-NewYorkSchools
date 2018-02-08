package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.utils.BasePresenter;
import com.example.x5.a20180208_alvinjean_nyschools.utils.BaseView;

import java.util.List;

public interface HighSchoolListContract {
    interface View extends BaseView {
        void updateHighSchoolList(List<HighSchool> highSchoolList);
    }

    interface Presenter extends BasePresenter<View> {
        void getHighSchools();
        //void getSATScores();
    }
}
