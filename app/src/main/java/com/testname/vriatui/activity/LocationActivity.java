package com.testname.vriatui.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class LocationActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private float acelVal = SensorManager.GRAVITY_EARTH;
    private float shake = 0.00f;

    @Inject
    IncidentApi incidentApi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        ((VriatuiApplication) getApplication()).getApiComponent().inject(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        findViewById(R.id.house).setOnClickListener(setLocationListener(true));
        findViewById(R.id.geoPosition).setOnClickListener(setLocationListener(false));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float acelLast = acelVal;
            acelVal = (float) Math.sqrt(Math.pow(x, 2) +
                    Math.pow(y, 2) +
                    Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if (shake > 12) {
                Log.i("Shake", "DO NOT SHAKE ME");
                sendIncident(incident());
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Ignore
    }

    @NonNull
    private View.OnClickListener setLocationListener(final boolean happenAtHome) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent(happenAtHome));
            }
        };
    }

    @NonNull
    private Intent intent(boolean happenAtHome) {
        Intent intent = new Intent(LocationActivity.this, ProblemActivity.class);
        intent.putExtra("happenAtHome", happenAtHome);
        if (!happenAtHome) {
            intent.putExtra("geoPosition", getGeoPosition());
        }
        return intent;
    }

    public IncidentRequest.Location getGeoPosition() {
        return new IncidentRequest.Location(50.4049258, 30.6796192);
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
        Intent intent = new Intent(LocationActivity.this, SuccessMapActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    @NonNull
    private IncidentRequest incident() {
        IncidentRequest incidentRequest = new IncidentRequest();
        incidentRequest.setProblem("КРИТИЧНИЙ ВИКЛИК");
        incidentRequest.setProfileId("5b53b21d0e89ab00044c7fcc");
        incidentRequest.setLocation(getGeoPosition());
        return incidentRequest;
    }
}
