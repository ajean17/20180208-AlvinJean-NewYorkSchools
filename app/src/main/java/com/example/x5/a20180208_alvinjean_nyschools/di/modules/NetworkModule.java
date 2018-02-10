package com.example.x5.a20180208_alvinjean_nyschools.di.modules;

import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = ContextModule.class)
public class NetworkModule {
    /*@Provides
    @AppScope
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }*/

    @Provides
    @AppScope
    public RemoteDataProvider remoteDataProvider(Context context) {
        return new RemoteDataProvider(context);
    }
}
