package com.rocketapp.aceacademics;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;

import static android.content.Context.ALARM_SERVICE;


public class MyBroadcastReceiver extends BroadcastReceiver {

    boolean isNotificationCallAnswered;
    ArrayList<String> subjectNames=new ArrayList<>();
    ArrayList<String> totalClasses=new ArrayList<>();
    ArrayList<String> totalAttended=new ArrayList<>();
    ArrayList<String> weekends = new ArrayList<>();
    ArrayList<String> expectationalValue = new ArrayList<>();
    float expecationalValue;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            getExpectationalValue(context);
            getTotalAttended(context);
            getTotalClasses(context);
            getWeekends(context);
            getSubjects(context);
        }
        catch (Exception e){}
        startNotifications(context);
    }
    private void createNextBroadcast(Context context){
        Intent intentNew = new Intent(context, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intentNew, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 60000, pendingIntent);
    }
    private int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getHours();
    }
    private void giveNotification(Context context, String name, float current, float inFuture, float outFuture){
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(name , name, NotificationManager.IMPORTANCE_HIGH);
        }
        notificationChannel.enableLights(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        Intent intentNew = new Intent(context, Records.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intentNew, 0);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, name)
                .setContentTitle("Low attendence in "+name)
                .setContentText("Current attendence   "+current+"%")
                .setSmallIcon(R.drawable.list)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Current attendence   "+current+"%")
                        .addLine("Attendence after   "+inFuture+"%")
                        .addLine("Attendence if not attended    "+outFuture+"%"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                ;

        Calendar calendar = Calendar.getInstance();

        notificationManager.notify(calendar.getTime().getSeconds()+name.charAt(0), notification.build());
    }
    void getTotalClasses(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfClasses", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalClasses = sample;
    }

    void getTotalAttended(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfAttended", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalAttended = sample;
    }
    void getWeekends(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfWeekends", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        weekends = sample;
    }

    void getExpectationalValue(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfExpectationalValue", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        expectationalValue = sample;
    }

    void getSubjects(Context context){
        SharedPreferences prefs = context.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKey", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        subjectNames=sample;
    }

    public String returnName(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return subject.substring(0, indexOfSpace);
    }

    public int searchWeekends(String subject) {
        int n = 0;
        while (n < weekends.size()) {
            if (subject.equals(returnName(weekends.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int searchExpectationlValue(String subject) {
        int n = 0;
        while (n < expectationalValue.size()) {
            if (subject.equals(returnName(expectationalValue.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int searchClasses(String subject) {
        int n = 0;
        while (n < totalClasses.size()) {
            if (subject.equals(returnName(totalClasses.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int searchAttended(String subject) {
        int n = 0;
        while (n < totalAttended.size()) {
            if (subject.equals(returnName(totalAttended.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int returnNumber(String subject){
        int indexOfSpace=subject.indexOf(' ');
        return Integer.parseInt(subject.substring(indexOfSpace+1));
    }

    public String returnWeekend(String subject){
        int indedOfSpace=subject.indexOf(' ');
        return subject.substring(indedOfSpace+1);
    }

    public float returnExpectationalValue(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return Float.parseFloat(subject.substring(indexOfSpace + 1));
    }

    public boolean getNotification(String s){
        return s.charAt(0)=='1';
    }
    public boolean getMonday(String s){
        return s.charAt(1)=='1';
    }
    public boolean getTuesday(String s){
        return s.charAt(2)=='1';
    }
    public boolean getWednesday(String s){
        return s.charAt(3)=='1';
    }
    public boolean getThursday(String s){
        return s.charAt(4)=='1';
    }
    public boolean getFriday(String s){
        return s.charAt(5)=='1';
    }
    public boolean getSaturday(String s){
        return s.charAt(6)=='1';
    }
    public boolean getSunday(String s){
        return s.charAt(7)=='1';
    }

    private void startNotifications(Context context){
        for(int i=0;i<subjectNames.size();i++){
            String subject=subjectNames.get(i);
            float classes=returnNumber(totalClasses.get(searchClasses(subject)));
            int attended=returnNumber(totalAttended.get(searchAttended(subject)));
            String weekend=returnWeekend(weekends.get(searchWeekends(subject)));
            float expectedValue=returnExpectationalValue(expectationalValue.get(searchExpectationlValue(subject)));
            float currentPercentage=(attended/classes)*100;
            System.out.println("----------------------------------------"+classes+" "+attended+" "+currentPercentage+" "+expectedValue);

            float ifMissed=(float)((attended)/(classes+1.0));
            String temp2=""+(ifMissed);
            temp2=temp2+"0000";
            float ifMissedWithPrecision=Float.parseFloat(temp2.substring(0, 5))*100;

            Calendar calendar=Calendar.getInstance();
            String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

            System.out.println("-----------------------------------------------------------> "+ dayLongName);


            //System.out.println(getNotification(weekend)+ "   " + (weekend.charAt(returnIndexOfWeekends(dayLongName))=='1'));

            if(ifMissed<expectedValue && getNotification(weekend) && (weekend.charAt(returnIndexOfWeekends(dayLongName))=='1')){
                //giveNotification(context, "subject "+temp2, expectedValue, 0, 0);
                String temp=""+currentPercentage+"0000";
                float currentPercentageWithPrecision= Float.parseFloat(temp.substring(0, 5));

                float ifAttended=(float)((attended+1)/(classes+1.0) + 0.0000);
                String temp1=""+ifAttended+"0000";
                float ifAttendedWithPrecision=Float.parseFloat(temp1.substring(0, 5))*100;

                System.out.println("------------------------------------------------------------------------------------------------------------> "+subject);

                giveNotification(context, subject, currentPercentageWithPrecision, ifAttendedWithPrecision, ifMissedWithPrecision);
                try{Thread.sleep(2000);}
                catch (Exception e){}
            }
        }
    }
    private int returnIndexOfWeekends(String dayLongName){
        if(dayLongName.equalsIgnoreCase("monday"))
            return 1;
        else if(dayLongName.equalsIgnoreCase("tuesday"))
            return 2;
        else if(dayLongName.equalsIgnoreCase("wednesday"))
            return 3;
        else if(dayLongName.equalsIgnoreCase("thursday"))
            return 4;
        else if(dayLongName.equalsIgnoreCase("friday"))
            return 5;
        else if(dayLongName.equalsIgnoreCase("saturday"))
            return 6;
        else
            return 7;
    }
}