package com.example.x5.a20180208_alvinjean_nyschools.di.components;

import com.example.x5.a20180208_alvinjean_nyschools.di.modules.SATDetailModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.ActivityScope;
import com.example.x5.a20180208_alvinjean_nyschools.views.satdetail.SATDetailPresenter;

import dagger.Component;

@ActivityScope
@Component(modules = SATDetailModule.class, dependencies = AppComponent.class)
public interface SATDetailComponent {
    //Activity coupled dependency injection of the following Presenter
    SATDetailPresenter getSATDetailPresenter();
}
