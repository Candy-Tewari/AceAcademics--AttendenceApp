package com.rocketapp.aceacademics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> subjectNames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Intent intentNew = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentNew, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            if(calendar.before(Calendar.getInstance()))
                calendar.add(Calendar.DATE, 1);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 60*1000, pendingIntent);
            //Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
            Set<String> set = prefs.getStringSet("yourKey", null);
            ArrayList<String> sample = new ArrayList<String>(set);
            subjectNames = sample;
            startActivity(new Intent(this, Records.class));
            finish();
        }
        catch (Exception e){
        }
    }

    public void getStarted(View view){
        startActivity(new Intent(this, Function.class));
        finish();
    }
}