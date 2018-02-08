package com.example.x5.a20180208_alvinjean_nyschools.views.satdetail;

import android.content.Context;
import android.util.Log;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SATDetailPresenter implements SATDetailContract.Presenter {
    private static final String TAG = SATDetailPresenter.class.getSimpleName() + "_TAG";
    private SATDetailContract.View view;
    private Context context;
    private OkHttpClient client;
    private List<SATScore> satScoreList;

    SATDetailPresenter(Context context) {
        this.context = context;
        client = new OkHttpClient();
    }

    @Override
    public void attachView(SATDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getSATData(final String highSchoolDBN) {
        Log.d(TAG, "getSATData: Getting DATA FOR SAT DBN: " + highSchoolDBN);
        final Request request = new Request.Builder().url("https://data.cityofnewyork.us/resource/734v-jeq5.json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<SATScore>>(){}.getType();
                Collection<SATScore> enums = gson.fromJson(json, collectionType);

                satScoreList = new ArrayList<>(enums);
                for(SATScore satScore: satScoreList) {
                    if(satScore.getDbn().equals(highSchoolDBN)){
                        view.initUI(satScore);
                        break;
                    }
                }
            }
        });
        /*DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_DBN,
                FeedEntry.COLUMN_NAME_NUM_TAKERS,
                FeedEntry.COLUMN_NAME_READING_AVG,
                FeedEntry.COLUMN_NAME_MATH_AVG,
                FeedEntry.COLUMN_NAME_WRITING_AVG,
                FeedEntry.COLUMN_SCHOOL_NAME
        };
        String selection = FeedEntry.COLUMN_NAME_DBN + " = ?";
        String[] selectionArgs = {highSchoolDBN};
        Cursor cursor = database.query( //**Requires 7 parameters**
                FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                selection,                   //Selection (WHERE)
                selectionArgs,                   //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort Order
        );
        SATScore entrySATScore = new SATScore();
        while(cursor.moveToNext()) {
            entrySATScore.setDbn(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_DBN)));
            entrySATScore.setNumOfSatTestTakers(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NUM_TAKERS)));
            entrySATScore.setSatCriticalReadingAvgScore(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_READING_AVG)));
            entrySATScore.setSatMathAvgScore(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_MATH_AVG)));
            entrySATScore.setSatWritingAvgScore(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_WRITING_AVG)));
            entrySATScore.setSchoolName(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_SCHOOL_NAME)));
        }
        Log.d(TAG, "getSATData: SATScoreInfo = " + entrySATScore.toString());
        view.initUI(entrySATScore);
        cursor.close();*/
    }
}
