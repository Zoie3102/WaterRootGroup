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

import java.util.Iterator;

public class WaterLogActivity2 extends AppCompatActivity {
    public static String waterLogText;
    public static TextView textOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_log2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textOutput=findViewById(R.id.textOutput);
        printWaterLog(textOutput);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBack(view);
//            }
//        });
    }
    public void onBack (View v){
        Intent startNewActivity = new Intent(WaterLogActivity2.this,AdditionalFeatures.class);
        startActivity(startNewActivity);
    }
    public void onRefresh(View v){
        printWaterLog(textOutput);
    }
    public void printWaterLog(TextView output){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference waterLog = database.getReference("waterLog");
        output.setText(waterLog.toString());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                waterLogText=dataSnapshot.getValue().toString();
                Iterator<DataSnapshot> iterator=dataSnapshot.getChildren().iterator();
                waterLogText="";

                while (iterator.hasNext()) {
                    DataSnapshot entry = iterator.next();
                    //add the formatted string representing the entry as it's supposed to be
                    if (entry.getChildrenCount()!=4){
                        waterLogText+="\nBadly Formatted Entry!!!!!";
                    }
                    else{
                        Iterator<DataSnapshot> entryIta=entry.getChildren().iterator();
                        String durationVal=entryIta.next().getValue().toString();
                        String manAutoVal=entryIta.next().getValue().toString();
                        long moistureVal= ((long) entryIta.next().getValue());
                        boolean waterVal=((boolean) entryIta.next().getValue());
//                        durationVal="mgh";
//                        manAutoVal="mgh";
//                        moistureVal=0;
//                        waterVal=false; Testing purposes

                        waterLogText+="\n"+entry.getKey().toString().substring(8)+"\n\t \t Duration: "+durationVal+
                                "\n\t \t Manual or Automatic: "+manAutoVal+"\n\t \t Moisture: "+
                                moistureVal+"\n\t \t Watered?: "+waterVal;


                    }
                }


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
