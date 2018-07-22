package com.testname.vriatui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testname.vriatui.R;
import com.testname.vriatui.model.Address;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

public class SuccessMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        initMap();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(SuccessMapActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Address.Location location = (Address.Location) getIntent().getSerializableExtra("location");
        LatLng latLng = new LatLng(location.getX(), location.getY());
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(HUE_RED)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
