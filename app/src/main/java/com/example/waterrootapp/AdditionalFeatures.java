package com.example.waterrootapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdditionalFeatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_features);
    }

    public void onReturn (View v){
        Intent startNewActivity = new Intent(AdditionalFeatures.this,MainActivity.class);
        startActivity(startNewActivity);


    }

    public void onLog (View v){
        Intent startNewActivity = new Intent(AdditionalFeatures.this,WaterLogActivity2.class);
        startActivity(startNewActivity);


    }

    public void onMoisture (View v){
        Intent startNewActivity = new Intent(AdditionalFeatures.this,MoistureActivity.class);
        startActivity(startNewActivity);


    }
    public void onInstructions (View v){
        Intent startNewActivity = new Intent(AdditionalFeatures.this,Instructions.class);
        startActivity(startNewActivity);
    }
    public void onPlantInfo (View v){
        Intent startNewActivity = new Intent(AdditionalFeatures.this,PlantInfo.class);
        startActivity(startNewActivity);
    }
}
