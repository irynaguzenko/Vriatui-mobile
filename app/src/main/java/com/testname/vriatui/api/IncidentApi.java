package com.testname.vriatui.api;

import com.testname.vriatui.model.IncidentRequest;
import com.testname.vriatui.model.IncidentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IncidentApi {

    @POST("/incident")
    Call<IncidentResponse> incidentHappend(@Body IncidentRequest incidentRequest);
}
