package com.example.waterrootapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firstTime",true);

        if (firstTime){
            new Handler().postDelayed(new Runnable() {
                @Override                public void run() {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime",firstTime);
                    editor.apply();

                    Intent i  = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000);
        }
        else {
            Intent i  = new Intent(SplashScreenActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }


    }
}
