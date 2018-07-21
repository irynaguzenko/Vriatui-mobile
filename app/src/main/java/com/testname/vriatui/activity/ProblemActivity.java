package com.testname.vriatui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.testname.vriatui.R;
import com.testname.vriatui.api.IncidentApi;
import com.testname.vriatui.dagger.VriatuiApplication;
import com.testname.vriatui.model.IdResponse;
import com.testname.vriatui.model.IncidentRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemActivity extends AppCompatActivity {

    @Inject
    IncidentApi incidentApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem);
        ((VriatuiApplication) getApplication()).getApiComponent().inject(this);

        View.OnClickListener listener = sendIncidentListener();
        findViewById(R.id.allergy).setOnClickListener(listener);
        findViewById(R.id.bleeding).setOnClickListener(listener);
        findViewById(R.id.burn).setOnClickListener(listener);
        findViewById(R.id.cold).setOnClickListener(listener);
        findViewById(R.id.fracture).setOnClickListener(listener);
        findViewById(R.id.head).setOnClickListener(listener);
        findViewById(R.id.heart).setOnClickListener(listener);
        findViewById(R.id.intestines).setOnClickListener(listener);
        findViewById(R.id.other).setOnClickListener(listener);
    }

    @NonNull
    private View.OnClickListener sendIncidentListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problem = v.getResources().getResourceEntryName(v.getId());
                sendIncident(incident(problem));
            }
        };
    }

    private void sendIncident(IncidentRequest incidentRequest) {
        incidentApi.incidentHappend(incidentRequest).enqueue(new Callback<IdResponse>() {
            @Override
            public void onResponse(Call<IdResponse> call, Response<IdResponse> response) {
                Log.i("Call", "Success " + response.body().getId());
            }

            @Override
            public void onFailure(Call<IdResponse> call, Throwable t) {
                Log.i("Call", "Failure");
            }
        });
    }

    @NonNull
    private IncidentRequest incident(String problem) {
        IncidentRequest incidentRequest = new IncidentRequest();
        incidentRequest.setProblem(problem);
        incidentRequest.setProfileId("5b53666f45d9280004f04a52");
        incidentRequest.setHappenAtHome(true);
        return incidentRequest;
    }
}
