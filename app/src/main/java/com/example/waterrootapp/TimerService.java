package com.example.waterrootapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.MainActivity.CHANNEL_ID;

public class TimerService extends Service {
String current;
String userTimer;
Calendar calendar;



    int userhour = 8;
    int userminute = 21;

    int userday = 17;

    int usermonth = 4;

    int useryear = 2019;
    @Override
    public void onCreate() {

        thread p = new thread();
       new Thread(p).start();

//         calendar = Calendar.getInstance();
//        String year = Integer.toString(calendar.get(Calendar.YEAR));
//        String month = Integer.toString(calendar.get(Calendar.MONTH));
//
//        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
//        String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//        String minute = Integer.toString(cxalendar.get(Calendar.MINUTE));
//
//         current = new String(hour + "/" + minute + "/" +day + "/" +month + "/" + year);
//        Log.d(TAG, current);




        userTimer = new String(userhour + "/" + userminute + "/" +userday + "/" +usermonth + "/" + useryear);// user time will be taken from the settings page
//        Log.d(TAG, userTimer);
//        autoWater();
        super.onCreate();

//        Log.d(TAG, "here");
//
//        Calendar calendar = Calendar.getInstance();
//        String year = Integer.toString(calendar.get(Calendar.YEAR));
//        String month = Integer.toString(calendar.get(Calendar.MONTH));
//
//        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
//        String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//        String minute = Integer.toString(calendar.get(Calendar.MINUTE));
//
//       String current = new String(hour + "/" + minute + "/" +day + "/" +month + "/" + year);
//        Log.d(TAG, current);
//       String userTimer = new String("22/10/9/3/2019");// user time will be taken from the settings page
//        Log.d(TAG, userTimer);
//        if(current.equals(userTimer)){
//            Log.d(TAG, "strings are equal");
//
//            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
//            DatabaseReference commands2 = database2.getReference("commands");
////
//            commands2.child("pumpOn").setValue(1);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//           commands2.child("pumpOn").setValue(0);
//            DatabaseReference log = database2.getReference("waterLog");
//
//            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//            String strDate = "Current Time : " + mdformat.format(calendar.getTime());
//            log.child(strDate).child("watered").setValue(true);
//            log.child(strDate).child("moisture").setValue(0);
//            log.child(strDate).child("duration").setValue("sec");

            //commands.child("pumpOn").setValue(0);


       // }
}


    protected int getDuration(@Nullable Intent intent) {

        String duration = intent.getStringExtra("Duration");
        int durationSeconds = Integer.parseInt(duration);
        Log.d(TAG, duration);
return durationSeconds;
        //  Download File logic

    }

    protected String getUserDate(@Nullable Intent intent) {

        String userDate = intent.getStringExtra("userDate");
        Log.d(TAG, userDate);
        return userDate;
        //  Download File logic

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Example Service").setContentText("ok").setContentIntent(pendingIntent).build();

        startForeground(1,notification);

        return START_NOT_STICKY;




    }

    public void autoWater(){





        if(current.equals(userTimer)) {
            Log.d(TAG, "strings are equal");

            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference commands2 = database2.getReference("commands");
//
            commands2.child("pumpOn").setValue(1);
            try {
                thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//

            commands2.child("pumpOn").setValue(0);
            DatabaseReference log = database2.getReference("waterLog");

            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = "Current Time : " + mdformat.format(calendar.getTime());
            log.child(strDate).child("watered").setValue(true);
            log.child(strDate).child("moisture").setValue(0);
            log.child(strDate).child("duration").setValue("sec");
        }









        }


        public String getTime(){
            calendar = Calendar.getInstance();
            String year = Integer.toString(calendar.get(Calendar.YEAR));
            String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);

            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String stringday = Integer.toString(day);


            String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            int minutes = calendar.get(Calendar.MINUTE);

            String stringminute = Integer.toString(minutes);
            current = new String(hour + "/" + stringminute + "/" +stringday + "/" +month + "/" + year);
            return current;


        }


    class thread extends Thread{

        //int seconds;

//        thread(){
//
//            calendar = Calendar.getInstance();
//            String year = Integer.toString(calendar.get(Calendar.YEAR));
//            String month = Integer.toString(calendar.get(Calendar.MONTH));
//
//            String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
//            String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//            String minute = Integer.toString(calendar.get(Calendar.MINUTE));
//
//            current = new String(hour + "/" + minute + "/" +day + "/" +month + "/" + year);
//        }
        @Override
        public void run() {

            getTime();

//            calendar = Calendar.getInstance();
//            String year = Integer.toString(calendar.get(Calendar.YEAR));
//            String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
//
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            String stringday = Integer.toString(day);
//
//
//            String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
//            int minutes = calendar.get(Calendar.MINUTE);
//
//            String stringminute = Integer.toString(minutes);
//            current = new String(hour + "/" + stringminute + "/" +stringday + "/" +month + "/" + year);

            Log.d(TAG, current);

            while(true){

                getTime();





                if(current.equals(userTimer)){
                    autoWater();


                    userday++;

                  //  day= day + 1;
                 //    stringday = Integer.toString(day);
                    //userday++;
                   // current = new String(hour + "/" + stringminute + "/" +stringday + "/" +month + "/" + year);
                    Log.d(TAG, getTime()); 


                    userTimer = new String(userhour + "/" + userminute + "/" +userday + "/" +usermonth + "/" + useryear);
                    Log.d(TAG, Integer.toString(userminute));






                }




                    try {
                    sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }






            }


        }
    }








}


