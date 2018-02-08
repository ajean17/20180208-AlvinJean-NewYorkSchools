package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.x5.a20180208_alvinjean_nyschools.R;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListView;


public class SATDetailView extends AppCompatActivity implements SATDetailContract.View{
    private static final String TAG = SATDetailView.class.getSimpleName() + "_TAG";
    private SATDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satdetail_view);
        presenter = new SATDetailPresenter(this);
        presenter.attachView(this);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra(HighSchoolListView.HIGH_SCHOOL_LIST_EXTRA))
                presenter.getSATData(intent.getStringExtra(HighSchoolListView.HIGH_SCHOOL_LIST_EXTRA));
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initUI(SATScore satScoreInfo) {
        Log.d(TAG, "initUI: " + satScoreInfo.toString());
    }
}
