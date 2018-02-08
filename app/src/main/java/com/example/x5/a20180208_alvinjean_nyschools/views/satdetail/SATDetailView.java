package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.x5.a20180208_alvinjean_nyschools.R;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListView;


public class SATDetailView extends AppCompatActivity implements SATDetailContract.View{
    private static final String TAG = SATDetailView.class.getSimpleName() + "_TAG";
    private SATDetailPresenter presenter;
    private TextView tvSchoolName, tvNumTaken, tvReadingAVG, tvMathAVG, tvWritingAVG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satdetail_view);
        presenter = new SATDetailPresenter(this);
        presenter.attachView(this);
        bindviews();
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra(HighSchoolListView.HIGH_SCHOOL_LIST_EXTRA))
                presenter.getSATData(intent.getStringExtra(HighSchoolListView.HIGH_SCHOOL_LIST_EXTRA));
        }
    }

    private void bindviews() {
        tvSchoolName = (TextView) findViewById(R.id.tv_sat_school_name);
        tvNumTaken = (TextView) findViewById(R.id.tv_sat_num_taken);
        tvReadingAVG = (TextView) findViewById(R.id.tv_sat_reading_avg);
        tvMathAVG = (TextView) findViewById(R.id.tv_sat_math_avg);
        tvWritingAVG = (TextView) findViewById(R.id.tv_sat_writing_avg);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showScores(final SATScore satScoreInfo) {
        Log.d(TAG, "initUI: " + satScoreInfo.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvSchoolName.setText(satScoreInfo.getSchoolName());
                if(!satScoreInfo.getSchoolName().equals(getString(R.string.lbl_no_sat_info))) {
                    tvNumTaken.setText(String.format("%s%s", getString(R.string.lbl_test_takers), satScoreInfo.getNumOfSatTestTakers()));
                    tvReadingAVG.setText(String.format("%s%s", getString(R.string.lbl_reading_avg), satScoreInfo.getSatCriticalReadingAvgScore()));
                    tvMathAVG.setText(String.format("%s%s", getString(R.string.lbl_math_avg), satScoreInfo.getSatMathAvgScore()));
                    tvWritingAVG.setText(String.format("%s%s", getString(R.string.lbl_writing_avg), satScoreInfo.getSatWritingAvgScore()));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
