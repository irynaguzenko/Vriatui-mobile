package com.testname.vriatui.api;

import com.testname.vriatui.model.IdResponse;
import com.testname.vriatui.model.UpdateProfileRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileApi {

    @POST("/profile")
    Call<IdResponse> createProfile(@Body UpdateProfileRequest updateProfileRequest);
}
