package com.example.mobileprogrammingfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LinearLayout stage1, stage2, stage3, stage4, stage5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                Toast.makeText(this, "You Login at "+addresses.get(0).getLocality(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage1 = (LinearLayout) findViewById(R.id.stage_1);
        stage2 = (LinearLayout) findViewById(R.id.StageTwo);
        stage3 = (LinearLayout) findViewById(R.id.StageThree);
        stage4 = (LinearLayout) findViewById(R.id.StageFourth);
        stage5 = (LinearLayout) findViewById(R.id.StageFive);
    }

    public void stageOne(View v){
        Intent intent = new Intent(this, StageOne.class);
        startActivity(intent);
    }

    public void stageTwo(View v){
        Intent intent = new Intent(this, StageTwo.class);
        startActivity(intent);
    }

    public void stageThree(View v){
        Intent intent = new Intent(this, StageThree.class);
        startActivity(intent);
    }

    public void stageFourth(View v){
        Intent intent = new Intent(this, StageFourth.class);
        startActivity(intent);
    }

    public void stageFive(View v){
        Intent intent = new Intent(this, StageFive.class);
        startActivity(intent);
    }
}