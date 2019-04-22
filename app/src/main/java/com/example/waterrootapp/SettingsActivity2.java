package com.example.waterrootapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.SplashScreenActivity.firstTime;
import static com.example.waterrootapp.SplashScreenActivity.sharedPreferences;
import static com.example.waterrootapp.SplashScreenActivity.switchon;


public class SettingsActivity2 extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";



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

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("switchkey", false);
        instructionsSwitch.setChecked(silent);




        instructionsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    switchon = true;






//
                }
                else{
                    Log.d(TAG, "onCheckedChanged: off");


                }
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.commit();




            }


        });
    }

    public void onReturn (View v){
        Intent startNewActivity = new Intent(SettingsActivity2.this,MainActivity.class);
        startActivity(startNewActivity);


    }

    public static boolean getSwitchOn(){


        if(switchon){
            return true;
        }
        else{
            return false;
        }
    }

}
