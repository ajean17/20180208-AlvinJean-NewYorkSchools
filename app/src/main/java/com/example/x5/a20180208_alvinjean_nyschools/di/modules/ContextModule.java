package com.example.x5.a20180208_alvinjean_nyschools.di.modules;


import android.content.Context;

import com.example.x5.a20180208_alvinjean_nyschools.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public Context context() {
        return context;
    }
}
