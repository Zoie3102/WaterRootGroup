package com.example.waterrootapp;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.Timer;
import java.util.TimerTask;

import static android.support.constraint.Constraints.TAG;
import static com.example.waterrootapp.MainActivity.CHANNEL_ID;
import static com.example.waterrootapp.MainActivity.time;
import static com.example.waterrootapp.MainActivity.wateredTodayEditor;
import static com.example.waterrootapp.MainActivity.wateredTodayPref;

/**
 * @author: Nilay McLaren
 * @date: 5/15/19
 * @description: A background service that automatically changes a variable in firebase to water a plant when the current time is the same as the time that the user typed in.
 */

public class TimerService extends Service {
    String current;
    Calendar calendar;
    public static int userhour = 13;
    public static int userminute = 38;
    public static int userday = 30;
    public static int usermonth = 4;
    public static int useryear = 2019;
    public static int userduration;
    public static String userTimer;

    /**
     * Creates an instance of the TimerService background service
     */
    @Override
    public void onCreate() {
        wateredTodayPref = getSharedPreferences("waterToday", MODE_PRIVATE);
        wateredTodayEditor = wateredTodayPref.edit();
        thread p = new thread();
        new Thread(p).start();
        addRecentMoistureListener();
        userTimer = userhour + "/" + userminute + "/" + usermonth + "/" + userday + "/" + useryear;
        super.onCreate();
    }

    /**
     * This method does what?????????????????
     * @param intent is the
     * @return
     */
    protected String getUserDate(@Nullable Intent intent) {
        String userDate = intent.getStringExtra("userDate");
        Log.d(TAG, userDate);
        return userDate;
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
        if (intent != null) {
            int position = intent.getIntExtra("position", 0);
        }
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Example Service").setContentText("ok").setContentIntent(pendingIntent).build();
        startForeground(1, notification);
        return START_NOT_STICKY;


    }

    /**
     * Waters the plant by changing the pumpOn variable in firebase and adds new information to the WaterLog
     */
    public void autoWater() {
        if (current.equals(userTimer)) {
            Log.d(TAG, "strings are equal");
            FirebaseDatabase database2 = FirebaseDatabase.getInstance();
            DatabaseReference commands2 = database2.getReference("commands");
sendIntent();
commands2.child("pumpOn").setValue(1);
            try {
                thread.sleep(userduration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            commands2.child("pumpOn").setValue(0);
            DatabaseReference log = database2.getReference("waterLog");
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String time = getCurrentTime();
            log.child(time).child("watered").setValue(true);
            log.child(time).child("moisture").setValue(0);
            log.child(time).child("duration").setValue(userduration / 1000);
            log.child(time).child("manual or automatic").setValue("Automatic");
        }
    }

    /**
     * Gets the current time and returns it formatted in a single string
     * Used for algorithmic purposes, this format is not shown to the user.
     * @return the formatted string that has the current hour, minute, day, month, and year
     */
    public String getTime() {
        calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String stringday = Integer.toString(day);
        String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        int minutes = calendar.get(Calendar.MINUTE);
        String stringminute = Integer.toString(minutes);
        current = new String(hour + "/" + stringminute + "/" + month + "/" + stringday + "/" + year);
        return current;
    }

    /**
     * This class is for the thread that automatically waters the plant.
     */
    class thread extends Thread {
        @Override
        public void run() {
            getTime();
            while (true) {
                getTime();
                if (current.equals(userTimer)) {
                    autoWater();
                    userday++;
                    userTimer = userhour + "/" + userminute + "/" + userday + "/" + usermonth + "/" + useryear;
                }
                try {
                    sleep(45000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void setWaterToday() {
//        View myLayout = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
//        TextView wateredTodayYet = (TextView) myLayout.findViewById(R.id.watered_yet);
//        if (wateredTodayPref.getBoolean("waterToday", false) == true) {
//            wateredTodayYet.setText("Plant HAS been watered in the last 24 hours");
//            Log.d(TAG, "okkokokok");
//        } else {
//            wateredTodayYet.setText("Plant HAS NOT been watered in the last 24 hours");
//        }
//    }



    public void sendIntent(){
        Intent local = new Intent();

        local.setAction("com.hello.action");

        this.sendBroadcast(local);
        Log.d(TAG, "sendIntent: ");
    }



    /**
     * This method returns a time string that used for tagging moisture
     * @return the formatted string that is used for logging moisture
     */
    public static String getTime2() {
        Calendar calendar3 = Calendar.getInstance();
        String year = Integer.toString(calendar3.get(Calendar.YEAR));
        String month = Integer.toString(calendar3.get(Calendar.MONTH) + 1);
        int day = calendar3.get(Calendar.DAY_OF_MONTH);
        String stringday = Integer.toString(day);
        String hour = Integer.toString(calendar3.get(Calendar.HOUR_OF_DAY));
        int minutes = calendar3.get(Calendar.MINUTE);
        String stringminute = Integer.toString(minutes);
        if (stringminute.length() == 1)
            stringminute = "0" + stringminute;
        return month + "\\" + stringday + "\\" + year + " at " + hour + ":" + stringminute;
    }

    /**
     * This method adds a listener to the recentMoisture and logs all moisture updates
     * The application has to do this because the arduino does not have access to a reliable clock,
     * so when the arduino pushes a moisture value to the database it does not have an accurate time
     * stamp for it.
     * This is the method that actually listens for moisture updates correctly.
     */
    public static void addRecentMoistureListener() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference moistureRec = database.getReference("moistureLog").child("recentMoisture");
//        final String timeString="Time: "+currentTime;
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the most recent moisture
                try {
                    long moistureVal = (long) dataSnapshot.getValue();
                    moistureRec.getParent().child(getTime2()).child("moisture").setValue(moistureVal);
                    Log.d("inService", "I just added a child ");
                } catch (java.lang.ClassCastException e) {
                    Log.d("oops", "I don't think that's the right object type.");
                    moistureRec.getParent().child(getTime2()).child("moisture").setValue("Ummm moisture is a long??!");

                } catch (java.lang.NullPointerException e) {
                    Log.wtf("inService", "moisture database structure missing!!!");
                }
                moistureRec.getParent().child("recentLog").removeValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        moistureRec.addValueEventListener(postListener);
    }


    /**
     * Return the current time and date. A 24 hour clock is used.
     * @return the current date and time in military time
     */
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(calendar.get(Calendar.MINUTE));
        String second = Integer.toString(calendar.get(Calendar.SECOND));
        String current = "Time: " + hour + ":" + minute +":" + second + "," + " on " + day + "\\" + month + "\\" + year;
        return current;
    }




}


