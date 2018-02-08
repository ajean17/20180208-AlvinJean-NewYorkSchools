package com.example.x5.a20180208_alvinjean_nyschools.di.modules;

import com.example.x5.a20180208_alvinjean_nyschools.di.scope.ActivityScope;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListPresenter;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListView;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class HighSchoolListModule {

    @Provides
    @ActivityScope
    public HighSchoolListPresenter highSchoolListPresenter(OkHttpClient client) {
        return new HighSchoolListPresenter(client);
    }
}
