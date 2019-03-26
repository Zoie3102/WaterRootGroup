package com.example.waterrootapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


    }
    public void onWater(View v){
        TextView output= (TextView) findViewById(R.id.nextWater);
        output.setText("Pressed");
        //Example code to write a message to the database
//        FirebaseApp.initializeApp(this);
//        if (FirebaseApp==null)
//        Log.d("got here");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("test1");
//
//        myRef.setValue("Hello, World!");

        //end Example code
    }
}
