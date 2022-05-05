package com.example.mobileprogrammingfinalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class Credits extends AppCompatActivity {
    TextView textcredit;
    Animation animation;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textcredit=findViewById(R.id.txtCreditScene);
        textcredit.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                finish();
                return true;
            }
        });

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.credits);

        textcredit.setText("CREDITS \n\n"+"Creator:\n-- Marchel Gunawan Dwi Steya --\n" +
                "-- Bhetrand Dwilangga --\n-- Hendri Agus Salim --\n\nClick Anywhere to close it");
        textcredit.startAnimation(animation);
    }
}