package com.example.x5.a20180208_alvinjean_nyschools.di.components;

import com.example.x5.a20180208_alvinjean_nyschools.di.modules.HighSchoolListModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.modules.NetworkModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.modules.SATDetailModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListPresenter;
import com.example.x5.a20180208_alvinjean_nyschools.views.satdetail.SATDetailPresenter;

import dagger.Component;
import okhttp3.OkHttpClient;

@AppScope
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    OkHttpClient getOkHttpClient();
}
