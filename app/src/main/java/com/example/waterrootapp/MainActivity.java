package com.example.waterrootapp;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.SplashScreenActivity.switchon;
import static com.example.waterrootapp.TimerService.userduration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static String time;
    public  static  SharedPreferences.Editor wateredTodayEditor;

    public static SharedPreferences wateredTodayPref;
    public static boolean wateredToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        wateredTodayPref = getSharedPreferences("waterToday",MODE_PRIVATE);
//wateredToday = wateredTodayPref.getBoolean("waterToday",false);
//        wateredTodayEditor.putBoolean("waterToday", false);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();





//        Switch timerSwitch = (Switch) findViewById(R.id.timer_switch);
//        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//
////                    Intent intent2 = new Intent(this,TimerService.class);
////                    intent2.putExtra("position",4);
////                    MainActivity.startService(intent2);
//
//                    Intent intent = new Intent(MainActivity.this,TimerService.class);
//
//
//                    startService(intent);
//
//
////                    FirebaseDatabase database = FirebaseDatabase.getInstance();
////                    DatabaseReference commands = database.getReference("commands");
//////
////                    commands.child("pumpOn").setValue(1);
////                    try {
////                        Thread.sleep(2000);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    commands.child("pumpOn").setValue(0);
//                }
//                else{
//                    Intent intent = new Intent(MainActivity.this,TimerService.class);
//
//                    stopService(intent);
//                   // Log.d(TAG, "service off ");
//
//                }
//
//
//            }
//
//
//        });
//
//




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

    public static final String CHANNEL_ID = "exampleServiceChannel";

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT

            );
        }
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

         Calendar calendar = Calendar.getInstance();
         String year = Integer.toString(calendar.get(Calendar.YEAR));
         String month = Integer.toString(calendar.get(Calendar.MONTH));

         String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
         String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
         String minute = Integer.toString(calendar.get(Calendar.MINUTE));

         String current = new String(hour + "/" + minute + "/" +day + "/" +month + "/" + year);
         Toast.makeText(getApplicationContext(),current,Toast.LENGTH_SHORT).show();


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
      //  waterToday();
        // setWaterToday();





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
        DatabaseReference log = database.getReference("waterLog");
        String time=getCurrentTime(v);
        log.child(time).child("watered").setValue(true);
        log.child(time).child("moisture").setValue(0);
        log.child(time).child("duration").setValue(0);
        log.child(time).child("manual or automatic").setValue("Manual");
    }

    public void onSetting (View v){
        Intent startNewActivity = new Intent(MainActivity.this,SettingsActivity2.class);
        startActivity(startNewActivity);


    }

    public void onAdditional (View v){
        Intent startNewActivity = new Intent(MainActivity.this,AdditionalFeatures.class);
        startActivity(startNewActivity);


    }

    public  void setWaterToday(){
        TextView wateredTodayYet = findViewById(R.id.watered_yet);
        if(wateredTodayPref.getBoolean("waterToday", false) == true){
            wateredTodayYet.setText("Plant has  been watered in the last 24 hours");

        }
        else{
            wateredTodayYet.setText("Plant has not been watered in the last 24 hours");
        }
    }


    public static void waterToday(){




            wateredTodayEditor.putBoolean("waterToday", true);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    wateredTodayEditor.putBoolean("waterToday", false);
                }
            }, 86400000);







    }






}
