package com.example.mobileprogrammingfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class LoadingMenu extends AppCompatActivity {

    ProgressBar progressBar;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_menu);

        load();



    }
    public void load()
    {
        progressBar=findViewById(R.id.progressBar);
        progressBar.setProgress(1);
        progressBar.setMax(10);

        boolean t = true;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run(){
                while(t){
                    try {
                        i++;
                        Thread.sleep(1000);
                        if(i==10)
                        {
                            Intent intent = new Intent(LoadingMenu.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);

                        }
                    });

                }
            }
        };
        new Thread(runnable).start();
    }
}