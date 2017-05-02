package com.bugull.hdg.myapplication;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationInfo.OnLocationChangeListener {

    private static final String TAG = "MainActivity";

    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationInfo.getInstance(locationManager).setOnLocationChangeListener(this);
        LocationInfo.getInstance(locationManager).getLocation(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "requestCode:" + requestCode);

        for (String permission : permissions) {
            Log.d(TAG, "permission:" + permission);
        }

        for (int grantResult : grantResults) {
            Log.d(TAG, "grantResult:" + grantResult);
        }
    }

    @Override
    public void onLocationChange(Location location) {
        List<Address> addresses = LocationInfo.getInstance(locationManager).getAddress(this, location);
        if(addresses!=null && addresses.size() > 0) {
            for (Address address : addresses) {
                Log.d(TAG, "location:" + address);
            }
        }
    }
}
