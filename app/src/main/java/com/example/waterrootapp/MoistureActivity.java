package com.example.waterrootapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoistureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisture);
    }

    public void onReturn (View v){
        Intent startNewActivity = new Intent(MoistureActivity.this,AdditionalFeatures.class);
        startActivity(startNewActivity);


    }
}
