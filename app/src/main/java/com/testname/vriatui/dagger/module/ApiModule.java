package com.testname.vriatui.dagger.module;

import android.content.Context;

import com.testname.vriatui.api.IncidentApi;
import com.testname.vriatui.api.ProfileApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ApiModule {
    private static final String SERVER_URL = "https://vriatui2.herokuapp.com";

    private Context context;

    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public IncidentApi incidentApi() {
        return retrofit().create(IncidentApi.class);
    }

    @Provides
    @Singleton
    public ProfileApi profileApi() {
        return retrofit().create(ProfileApi.class);
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient())
                .build();
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
//        return new OkHttpClient().newBuilder()
//                .addInterceptor(chain -> {
//                    Request authorization = chain.request().newBuilder()
//                            .addHeader("Authorization", SaveSharedPreferences.getUserName(context))
//                            .build();
//                    return chain.proceed(authorization);
//                })
//                .build();
    }
}
