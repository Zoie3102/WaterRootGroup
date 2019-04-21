package com.example.waterrootapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static android.support.constraint.Constraints.TAG;


public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
     public static boolean firstTime;

    @Override    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firstTime",true);

        if (firstTime==true){
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
            }, 10000);
        }
        else {
            Intent i  = new Intent(SplashScreenActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }


    }

    public void onExit (View v){
        Intent startNewActivity = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(startNewActivity);


    }
}
