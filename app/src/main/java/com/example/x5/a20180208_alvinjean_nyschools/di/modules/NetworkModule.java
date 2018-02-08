package com.example.x5.a20180208_alvinjean_nyschools.di.modules;

import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {
    @Provides
    @AppScope
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
