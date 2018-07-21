package com.testname.vriatui.dagger;

import android.app.Application;

import com.testname.vriatui.dagger.component.ApiComponent;
import com.testname.vriatui.dagger.component.DaggerApiComponent;
import com.testname.vriatui.dagger.module.ApiModule;

public class VriatuiApplication extends Application {
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent
                .builder()
                .apiModule(new ApiModule(this.getApplicationContext()))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
