package com.example.x5.a20180208_alvinjean_nyschools;

import android.app.Application;
import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.di.components.AppComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.DaggerAppComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.DaggerHighSchoolListComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.DaggerSATDetailComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.HighSchoolListComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.components.SATDetailComponent;
import com.example.x5.a20180208_alvinjean_nyschools.di.modules.ContextModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.modules.NetworkModule;

public class HighSchoolAppication extends Application {

    private static final String BASE_URL = "https://data.cityofnewyork.us/resource/";
    private AppComponent appComponent;
    private HighSchoolListComponent highSchoolListComponent;
    private SATDetailComponent satDetailComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ContextModule contextModule = new ContextModule(getApplicationContext());
        NetworkModule networkModule = new NetworkModule(BASE_URL);
        appComponent = DaggerAppComponent.builder()
                .contextModule(contextModule)
                .networkModule(networkModule)
                .build();
    }

    public static HighSchoolAppication getAppComponent(Context context) {
        return (HighSchoolAppication) context.getApplicationContext();
    }

    public HighSchoolListComponent getHighSchoolListComponent() {
        highSchoolListComponent = DaggerHighSchoolListComponent.builder()
                .appComponent(appComponent).build();
        return highSchoolListComponent;
    }

    public SATDetailComponent getSatDetailComponent() {
        satDetailComponent = DaggerSATDetailComponent.builder()
                .appComponent(appComponent).build();
        return satDetailComponent;
    }

    public void clearHighSchoolListComponent() {
        highSchoolListComponent = null;
    }

    public void clearSATDetailComponent() {
        satDetailComponent = null;
    }
}
