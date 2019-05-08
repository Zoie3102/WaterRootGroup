package com.example.waterrootapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlantInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);
    }
    public void onBack (View v){
        Intent startNewActivity = new Intent(PlantInfo.this,AdditionalFeatures.class);
        startActivity(startNewActivity);
    }
    public void onGoogle(View v){
        openWebPage("http://google.com/");
    }
    public void onSpruce(View v){
        openWebPage("https://www.thespruce.com/outdoors-and-gardening-4127780");
    }
    //copied from https://developer.android.com/guide/components/intents-common#Browser then updated
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //end copied code
}
