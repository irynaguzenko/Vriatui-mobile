package com.testname.vriatui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.testname.vriatui.R;
import com.testname.vriatui.api.IncidentApi;
import com.testname.vriatui.dagger.VriatuiApplication;
import com.testname.vriatui.model.Address;
import com.testname.vriatui.model.IncidentRequest;
import com.testname.vriatui.model.IncidentResponse;

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

    private void sendIncident(final IncidentRequest incidentRequest) {
        incidentApi.incidentHappend(incidentRequest).enqueue(new Callback<IncidentResponse>() {
            @Override
            public void onResponse(Call<IncidentResponse> call, Response<IncidentResponse> response) {
                IncidentResponse incidentResponse = response.body();
                if (incidentResponse != null) {
                    Log.i("Call", "Success " + incidentResponse.getId());
                    goToSuccessMap(incidentResponse.getAddress().getLocation());
                }
            }

            @Override
            public void onFailure(Call<IncidentResponse> call, Throwable t) {
                Log.i("Call", "Failure");
            }
        });
    }

    private void goToSuccessMap(Address.Location location) {
        Intent intent = new Intent(ProblemActivity.this, SuccessMapActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    @NonNull
    private IncidentRequest incident(String problem) {
        IncidentRequest incidentRequest = new IncidentRequest();
        incidentRequest.setProblem(problem);
        incidentRequest.setProfileId("5b53b21d0e89ab00044c7fcc");

        Intent intent = getIntent();
        incidentRequest.setHappenAtHome(intent.getBooleanExtra("happenAtHome", false));
        incidentRequest.setLocation((IncidentRequest.Location) intent.getSerializableExtra("geoPosition"));
        return incidentRequest;
    }
}
