package com.example.x5.a20180208_alvinjean_nyschools.di.modules;

import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.data.RemoteDataProvider;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class NetworkModule {

    String BASE_URL;

    public NetworkModule(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    @Provides
    @AppScope
    public Cache cache(Context context) {
        return  new Cache(context.getCacheDir(), 10 * 1024 * 1024);
    }

    @Provides
    @AppScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient client) {
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public RemoteDataProvider remoteDataProvider(Retrofit retrofit) {
        return new RemoteDataProvider(retrofit);
    }
}
