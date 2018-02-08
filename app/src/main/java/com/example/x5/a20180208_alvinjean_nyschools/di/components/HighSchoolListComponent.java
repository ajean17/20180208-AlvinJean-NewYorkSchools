package com.example.x5.a20180208_alvinjean_nyschools.di.components;

import com.example.x5.a20180208_alvinjean_nyschools.di.modules.HighSchoolListModule;
import com.example.x5.a20180208_alvinjean_nyschools.di.scope.ActivityScope;
import com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist.HighSchoolListPresenter;

import dagger.Component;

@ActivityScope
@Component(modules = HighSchoolListModule.class, dependencies = AppComponent.class)
public interface HighSchoolListComponent {
    HighSchoolListPresenter getHighSchoolListPresenter();
}
