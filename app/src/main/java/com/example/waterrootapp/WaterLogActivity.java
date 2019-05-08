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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class WaterLogActivity extends AppCompatActivity {

    private static String waterLogText="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textOutput=findViewById(R.id.textOutput);
        printWaterLog(textOutput);


    }

    public void onBack (View v){
        Intent startNewActivity = new Intent(WaterLogActivity.this,AdditionalFeatures.class);
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
                waterLogText=dataSnapshot.getChildren().toString();
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
