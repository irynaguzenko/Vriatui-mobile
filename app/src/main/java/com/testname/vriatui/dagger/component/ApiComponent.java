package com.testname.vriatui.dagger.component;

import com.testname.vriatui.activity.LocationActivity;
import com.testname.vriatui.activity.ProblemActivity;
import com.testname.vriatui.dagger.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(ProblemActivity problemActivity);
    void inject(LocationActivity problemActivity);
}
