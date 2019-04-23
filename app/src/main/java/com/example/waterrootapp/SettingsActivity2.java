package com.example.waterrootapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.sql.Time;
import java.util.Timer;

import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.SplashScreenActivity.firstTime;
import static com.example.waterrootapp.SplashScreenActivity.sharedPreferences;
import static com.example.waterrootapp.SplashScreenActivity.switchon;


public class SettingsActivity2 extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
   public  SharedPreferences myPrefs;
    public  SharedPreferences.Editor editor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);



        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
          editor2 = myPrefs.edit();





        Switch timerSwitch = (Switch) findViewById(R.id.switch1);

        SharedPreferences settings2 = getSharedPreferences(PREFS_NAME, 0);
        boolean silent2 = settings2.getBoolean("timerkey", false);
        timerSwitch.setChecked(silent2);
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
                SharedPreferences settings2 = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings2.edit();
                editor.putBoolean("timerkey", isChecked);
                editor.commit();


            }


        });



        Switch instructionsSwitch = (Switch) findViewById(R.id.switch2);
if(instructionsSwitch.isChecked()){
    switchon = true;
}
else {
    switchon = false;
}
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("switchkey", false);
        instructionsSwitch.setChecked(silent);



        instructionsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    editor2.putBoolean("switchon", true);
                    editor2.apply();
                    Log.d(TAG, Boolean.toString(myPrefs.getBoolean("switchon", true)));



//                    switchon = true;
//
//                    //switchon = true;
//                    Log.d(TAG, Boolean.toString(getSwitchOn(mainView)));







                }
                else{

                    editor2.putBoolean("switchon", false);
                    editor2.apply();
                    Log.d(TAG, Boolean.toString(myPrefs.getBoolean("switchon", true)));




                }
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.commit();




            }


        });
        final EditText timeOfWater =  findViewById(R.id.editText);



        timeOfWater.addTextChangedListener(new TextChangedListener<EditText>(timeOfWater) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                String time = timeOfWater.getText().toString();
                Intent timeIntent = new Intent(SettingsActivity2.this, TimerService.class);
                timeIntent.putExtra("timeofday", time);
            }
        });
    }

    public void onReturn (View v){
        Intent startNewActivity = new Intent(SettingsActivity2.this,MainActivity.class);
        startActivity(startNewActivity);


    }


    public abstract class TextChangedListener<T> implements TextWatcher {
        private T target;

        public TextChangedListener(T target) {
            this.target = target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            this.onTextChanged(target, s);
        }

        public abstract void onTextChanged(T target, Editable s);
    }





}
