package com.example.waterrootapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterLogActivity2 extends AppCompatActivity {
    public static String waterLogText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_log2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textOutput=findViewById(R.id.textOutput);
        printWaterLog(textOutput);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void onBack (View v){
        Intent startNewActivity = new Intent(WaterLogActivity2.this,AdditionalFeatures.class);
        startActivity(startNewActivity);


    }
    public void printWaterLog(TextView output){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference waterLog = database.getReference("waterLog");
        output.setText(waterLog.toString());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                waterLogText=dataSnapshot.getValue().toString();
                dataSnapshot.getChildren();
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        waterLog.addValueEventListener(postListener);
//        Query test=waterLog;
        output.setText(waterLogText);

    }
}
