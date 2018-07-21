package com.testname.vriatui.api;

import com.testname.vriatui.model.IdResponse;
import com.testname.vriatui.model.IncidentRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IncidentApi {

    @POST("/incident")
    Call<IdResponse> incidentHappend(@Body IncidentRequest incidentRequest);
}
