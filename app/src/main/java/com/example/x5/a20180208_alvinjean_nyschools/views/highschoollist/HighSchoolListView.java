package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.example.x5.a20180208_alvinjean_nyschools.R;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.AppComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.DaggerAppComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.DaggerHighSchoolListComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.HighSchoolListComponent;
import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.views.satdetail.SATDetailView;

import java.util.List;

import javax.inject.Inject;

public class HighSchoolListView extends AppCompatActivity implements HighSchoolListContract.View, HighSchoolListAdapter.HighSchoolListItemListener{

    private static final String TAG = HighSchoolListView.class.getSimpleName() + "_TAG";
    public static final String HIGH_SCHOOL_LIST_EXTRA = "com.example.x5.a20180207_alvinjean_nyschools.views.highschoolList_HIGH_SCHOOL_LIST_EXTRA";
    @Inject
    HighSchoolListPresenter presenter;
    private HighSchoolListAdapter highSchoolListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("New York High Schools");
        AppComponent appComponent = DaggerAppComponent.builder().build();
        HighSchoolListComponent component = DaggerHighSchoolListComponent.builder()
                .appComponent(appComponent).build();
        presenter = component.getHighSchoolListPresenter();
        presenter.attachView(this);
        setUpRecyclerView();
        presenter.getHighSchools();
    }

    private void setUpRecyclerView() {
        highSchoolListAdapter = new HighSchoolListAdapter(this);
        RecyclerView highSchoolRecyclerView = findViewById(R.id.rv_high_schools);
        highSchoolRecyclerView.setAdapter(highSchoolListAdapter);
        highSchoolRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateHighSchoolList(final List<HighSchool> highSchoolList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                highSchoolListAdapter.updateDataSet(highSchoolList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClicked(HighSchool highSchool) {
        Intent intent = new Intent(this, SATDetailView.class);
        intent.putExtra(HIGH_SCHOOL_LIST_EXTRA, highSchool.getDbn());
        startActivity(intent);
    }
}
