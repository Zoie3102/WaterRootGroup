package com.example.waterrootapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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


    }

    public void onWater(View v){
        TextView output= (TextView) findViewById(R.id.nextWater);
        output.setText("Pressed");
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
        Intent startNewActivity = new Intent(this,SettingsActivity.class);
        startActivity(startNewActivity);


    }
}
