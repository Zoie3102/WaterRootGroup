package com.example.waterrootapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.MainActivity.CHANNEL_ID;
import static com.example.waterrootapp.MainActivity.time;

public class TimerService extends Service {
String current;
Calendar calendar;




   public static int userhour = 13;
    public static  int userminute = 38;

    public static int userday = 30;

    public static int usermonth = 4;

    public static int useryear = 2019;
    public static int userduration;
    public static String userTimer;



   @Override
    public void onCreate( ) {
//Intent timeIntent = getIntent();
      // String songUrl = timeIntent.getStringExtra("YOUR_KEY_SONG_NAME");

       Log.d(TAG, "current=" + getTime());









       thread p = new thread();
       new Thread(p).start();
       thread2 q = new thread2();
       new Thread(q).start();

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




       userTimer = userhour + "/" + userminute + "/" +usermonth + "/" +userday + "/" + useryear;
   Log.d(TAG, userTimer);
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




    protected String getUserDate(@Nullable Intent intent) {

        String userDate = intent.getStringExtra("userDate");
        Log.d(TAG, userDate);
        return userDate;
        //  Download File logic

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.interrupted();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null){
            int position = intent.getIntExtra("position", 0);
        }



        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Example Service").setContentText("ok").setContentIntent(pendingIntent).build();

        startForeground(1,notification);

        return START_NOT_STICKY;




    }
//   public String getDuration(){
//    String data = getIntent().getExtras().getString("defaultKey");
//    return data;
//
//    }
//

    public void autoWater(){





        if(current.equals(userTimer)) {
            Log.d(TAG, "strings are equal");

            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference commands2 = database2.getReference("commands");
//
            commands2.child("pumpOn").setValue(1);
            try {
                thread.sleep(userduration);
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
            log.child(strDate).child("duration").setValue(userduration/1000);
            log.child(strDate).child("manual or automatic").setValue("Automatic");
//waterToday();

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
            current = new String(hour + "/" + stringminute + "/" +month + "/" +stringday + "/" + year);
            return current;


        }
    public String getTime2(){
        calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String stringday = Integer.toString(day);


        String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        int minutes = calendar.get(Calendar.MINUTE);

        String stringminute = Integer.toString(minutes);
        if (stringminute.length()==1)
            stringminute="0"+stringminute;
        current = new String(month + "\\" +stringday + "\\" + year+" at "+hour + ":" + stringminute);
        return current;


    }


    class thread extends Thread{


        @Override
        public void run() {

            getTime();

//

            Log.d(TAG, current);

            while(true){
                Log.d(TAG, getTime());

                getTime();





                if(current.equals(userTimer)){
                    autoWater();


                    userday++;


                    Log.d(TAG, getTime()); 


                    userTimer =userhour + "/" + userminute + "/" +userday + "/" +usermonth + "/" + useryear;
                    Log.d(TAG, Integer.toString(userminute));






                }




                    try {
                    sleep(45000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }






            }


        }
    }
    class thread2 extends Thread{


        @Override
        public void run() {



            Log.d(TAG, current);

            while(true){
                Log.d(TAG, getTime2());

                addMoistureLogListener(getTime2());
                try {
                    thread2.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }

    }
    public static void addMoistureLogListener(String currentTime){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference moistureLog = database.getReference("moistureLog").child("recentLog");
        final String timeString="Time: "+currentTime;
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                waterLogText=dataSnapshot.getValue().toString();
                DatabaseReference moistureRef=dataSnapshot.getChildren().iterator().next().getRef();
                long moistureVal=(long)dataSnapshot.getChildren().iterator().next().getValue();

                moistureLog.getParent().child(timeString).child("moisture").setValue(moistureVal);
                Log.d("inServece","I just added a child HAHAHAHAHAHAHAHAHA");
//                moistureLog.child("recentLog").removeValue(dataSnapshot.getChildren().iterator().next());


                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        moistureLog.addValueEventListener(postListener);
    }




    








}


