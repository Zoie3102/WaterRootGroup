package com.example.waterrootapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.SplashScreenActivity.firstTime;


public class SettingsActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        Switch timerSwitch = (Switch) findViewById(R.id.switch1);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    Intent intent = new Intent(SettingsActivity2.this,TimerService.class);


                    startService(intent);


         }
                else{
                    Intent intent = new Intent(SettingsActivity2.this,TimerService.class);

                    stopService(intent);
                    // Log.d(TAG, "service off ");

                }


            }


        });



        Switch instructionsSwitch = (Switch) findViewById(R.id.switch2);
        instructionsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    firstTime = true;



//
                }
                else{
                    Log.d(TAG, "onCheckedChanged: off");


                }


            }


        });
    }
}
