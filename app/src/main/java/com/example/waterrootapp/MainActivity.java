package com.example.waterrootapp;

import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        FirebaseApp.initializeApp(this);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("test1");
        ImageButton water=findViewById(R.id.imageButton);
        water.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {



                MotionEvent event=motionEvent;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        onWater(view);
                        break;

                    case MotionEvent.ACTION_UP:
                        onNotWater(view);
                        break;

                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });
        water.setAdjustViewBounds(true);
        getResources();


    }


    public void displayPopup (View view){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rootLayout), R.string.water_message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public String getCurrentTime(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = "Current Time : " + mdformat.format(calendar.getTime());
        return strDate;

    }
    public void onWater(View v){
        TextView output= (TextView) findViewById(R.id.nextWater);
        output.setText("Pressed");
        ImageButton water=findViewById(R.id.imageButton);

        water.setImageDrawable(getResources().getDrawable(R.drawable.clickwaternow2));
        //Example code to write a message to the database
//        FirebaseApp.initializeApp(this);
//        if (FirebaseApp==null)
//        Log.d("got here");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commands = database.getReference("commands");
//
        commands.child("pumpOn").setValue(1);



        //end Example code
    }
    public void onNotWater(View v){
        TextView output= (TextView) findViewById(R.id.nextWater);
        output.setText("Not Pressed");
        ImageButton water=findViewById(R.id.imageButton);
        water.setImageDrawable(getResources().getDrawable(R.drawable.water_now3));
        //Example code to write a message to the database
//        FirebaseApp.initializeApp(this);
//        if (FirebaseApp==null)
//        Log.d("got here");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commands = database.getReference("commands");
//
        commands.child("pumpOn").setValue(0);
    }

    public void onSetting (View v){
        Intent startNewActivity = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(startNewActivity);


    }

    public void onAdditional (View v){
        Intent startNewActivity = new Intent(MainActivity.this,AdditionalFeatures.class);
        startActivity(startNewActivity);


    }
}
