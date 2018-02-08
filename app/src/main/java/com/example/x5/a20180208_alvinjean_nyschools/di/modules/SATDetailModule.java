package com.example.x5.a20180208_alvinjean_nyschools.di.modules;

import com.example.x5.a20180208_alvinjean_nyschools.di.scope.ActivityScope;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;
import com.example.x5.a20180208_alvinjean_nyschools.views.satdetail.SATDetailPresenter;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class SATDetailModule {
    @Provides
    @ActivityScope
    public SATDetailPresenter satDetailPresenter(OkHttpClient client) {
        return new SATDetailPresenter(client);
    }
}
