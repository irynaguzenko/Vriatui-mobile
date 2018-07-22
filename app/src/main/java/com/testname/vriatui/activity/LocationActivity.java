package com.testname.vriatui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.testname.vriatui.R;
import com.testname.vriatui.model.IncidentRequest;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        findViewById(R.id.house).setOnClickListener(setLocationListener(true));
        findViewById(R.id.geoPosition).setOnClickListener(setLocationListener(false));
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
}
