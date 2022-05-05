package com.example.mobileprogrammingfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPS extends AppCompatActivity {

    TextView longitude, latitude, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        longitude = (TextView) findViewById(R.id.longitude);
        latitude = (TextView) findViewById(R.id.latitude);
        country = (TextView) findViewById(R.id.country);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1023);
        }
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double lo = loc.getLongitude();
        double la = loc.getLatitude();

        Geocoder gdc = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gdc.getFromLocation(la, lo, 1);
            if (addresses.size() > 0)
                country.setText("Country: "+addresses.get(0).getLocality());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        longitude.setText("Longitude: "+lo);
        latitude.setText("Latitude: "+la);

    }

}