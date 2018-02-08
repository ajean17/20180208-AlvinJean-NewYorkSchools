package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
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

public class HighSchoolListPresenter implements HighSchoolListContract.Presenter {
    private static final String TAG = HighSchoolListPresenter.class.getSimpleName() + "_TAG";
    private HighSchoolListContract.View view;
    private List<HighSchool> highSchoolList;
    private OkHttpClient client;
    //private SQLiteDatabase database;

    HighSchoolListPresenter(Context context) {
        client = new OkHttpClient();
        //DBHelper helper = new DBHelper(context);
        //database = helper.getWritableDatabase();
    }

    @Override
    public void attachView(HighSchoolListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getHighSchools() {
        final Request request = new Request.Builder().url("https://data.cityofnewyork.us/resource/97mf-9njv.json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<HighSchool>>(){}.getType();
                Collection<HighSchool> enums = gson.fromJson(json, collectionType);

                highSchoolList = new ArrayList<>(enums);
                view.updateHighSchoolList(highSchoolList);
            }
        });
    }

    /*@Override
    public void getSATScores() {
        if(hasEntries(database, FeedEntry.TABLE_NAME)) {
            Log.d(TAG, "getSATScores: Entries already populated");
        } else {
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

                    List<SATScore> SATScoreList = new ArrayList<>(enums);
                    for(SATScore satScore: SATScoreList) {
                        saveSATScore(satScore);
                    }
                }
            });
        }
    }

    private void saveSATScore(SATScore satScore) {
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_DBN, satScore.getDbn());
        values.put(FeedEntry.COLUMN_NAME_NUM_TAKERS, satScore.getNumOfSatTestTakers());
        values.put(FeedEntry.COLUMN_NAME_READING_AVG, satScore.getSatCriticalReadingAvgScore());
        values.put(FeedEntry.COLUMN_NAME_MATH_AVG, satScore.getSatMathAvgScore());
        values.put(FeedEntry.COLUMN_NAME_WRITING_AVG, satScore.getSatWritingAvgScore());
        values.put(FeedEntry.COLUMN_SCHOOL_NAME, satScore.getSchoolName());

        long recordID = database.insert(
                FeedEntry.TABLE_NAME,
                null,
                values
        );
        /*String saveResult;
        if(recordID > 0) {
            saveResult = "\nNote saved.";
            Log.d(TAG, saveResult);
        } else {
            saveResult = "\nNote not saved.";
            Log.d(TAG, saveResult);
        }
    }

    private boolean hasEntries(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
            return false;
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + tableName, null);
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }*/
}
