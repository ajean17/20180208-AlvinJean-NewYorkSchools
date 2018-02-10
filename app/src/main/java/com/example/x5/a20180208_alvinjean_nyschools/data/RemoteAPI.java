package com.example.x5.a20180208_alvinjean_nyschools.data;

import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;
import com.example.x5.a20180208_alvinjean_nyschools.models.SATScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteAPI {

    @GET("{path}.json")
    Call<HighSchool[]> getNewYorkHighSchools(@Path("path") String path);

    @GET("{path}.json")
    Call<SATScore[]> getSATScores(@Path("path") String path);
}
