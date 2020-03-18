package com.rocketapp.aceacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Attendence extends AppCompatActivity {

    String subject;
    int currentClasses, currentAttended;
    float expecationalValue;
    String switches;
    ArrayList<String> totalClasses = new ArrayList<>();
    ArrayList<String> totalAttended = new ArrayList<>();
    ArrayList<String> expectationalValue = new ArrayList<>();
    ArrayList<String> weekends = new ArrayList<>();
    Switch notification, monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    int arr[]=new int[8];

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

    void getTotalClasses() {
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfClasses", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalClasses = sample;
    }

    void getTotalAttended() {
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfAttended", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalAttended = sample;
    }
    void getWeekends() {
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfWeekends", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        weekends = sample;
    }

    void getExpectationalValue() {
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfExpectationalValue", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        expectationalValue = sample;
    }

    public String returnName(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return subject.substring(0, indexOfSpace);
    }

    public String returnSwitchKeys(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return subject.substring((indexOfSpace + 1));
    }

    public float returnExpectationalValue(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return Float.parseFloat(subject.substring(indexOfSpace + 1));
    }

    public int returnNumber(String subject) {
        int indexOfSpace = subject.indexOf(' ');
        return Integer.parseInt(subject.substring(indexOfSpace + 1));
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
    public void setSwitches(){
        notification=findViewById(R.id.switch1);
        monday=findViewById(R.id.switch2);
        tuesday=findViewById(R.id.switch3);
        wednesday=findViewById(R.id.switch4);
        thursday=findViewById(R.id.switch5);
        friday=findViewById(R.id.switch6);
        saturday=findViewById(R.id.switch7);
        sunday=findViewById(R.id.switch8);

        notification.setChecked(getNotification(switches));
        if(getNotification(switches)) {
            arr[0] = 1;
            notification.setText("ON    ");
        }
        monday.setChecked(getMonday(switches));
        if(getMonday(switches))
            arr[1]=1;
        tuesday.setChecked(getTuesday(switches));
        if(getTuesday(switches))
            arr[2]=1;
        wednesday.setChecked(getWednesday(switches));
        if(getWednesday(switches))
            arr[3]=1;
        thursday.setChecked(getThursday(switches));
        if(getThursday(switches))
            arr[4]=1;
        friday.setChecked(getFriday(switches));
        if(getFriday(switches))
            arr[5]=1;
        saturday.setChecked(getSaturday(switches));
        if(getSaturday(switches))
            arr[6]=1;
        sunday.setChecked(getSunday(switches));
        if(getSunday(switches))
            arr[7]=1;
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

    public void getTheTotalClassesAndAttendedData() {
        int tot = searchClasses(subject);
        int pot = searchAttended(subject);
        int rat = searchWeekends(subject);
        int mat = searchExpectationlValue(subject);
        currentAttended = returnNumber(totalAttended.get(tot));
        currentClasses = returnNumber(totalClasses.get(pot));
        switches = returnSwitchKeys(weekends.get(rat));
        expecationalValue = returnExpectationalValue(expectationalValue.get(mat));
    }

    public void setAllData() {
        //9 and 10
        EditText classes = this.findViewById(R.id.editText9);
        classes.setText(""+currentClasses);
        EditText attended = this.findViewById(R.id.editText10);
        attended.setText(""+currentAttended);
        EditText expect = findViewById(R.id.editText11);
        expect.setText(""+expecationalValue);
        TextView percent = findViewById(R.id.textView17);
        float percentage = currentAttended;
        percentage = (percentage / currentClasses) * 100;
        String percentString;
        try{ percentString=(percentage+"").substring(0, 5);}
        catch (Exception e){percentString=(percentage+"").toString().substring(0, (percentage+"").length());}
        percent.setText(percentString + "%");
        CircularProgressBar circularProgressBar = this.findViewById(R.id.circularProgressBar);
        //Toast.makeText(this, ""+percentString, Toast.LENGTH_SHORT).show();
        circularProgressBar.setProgressWithAnimation(Float.parseFloat(percentString), 2000L);
        setSwitches();
    }

    public void saveAllData(View view) {
        EditText classes = findViewById(R.id.editText9);
        EditText attended = findViewById(R.id.editText10);
        EditText expect = findViewById(R.id.editText11);
        try{ currentClasses = Integer.parseInt(classes.getText().toString());}
        catch (Exception e){currentClasses=0;}
        try{currentAttended = Integer.parseInt(attended.getText().toString());}
        catch (Exception e){currentAttended=0;}
        try{expecationalValue = Float.parseFloat(expect.getText().toString());}
        catch (Exception e){expecationalValue=0.0f;}
        if(expecationalValue>100.0f)
            expecationalValue=100.0f;
        if (currentAttended > currentClasses) {
            Toast.makeText(this, "Attended greater than total classes", Toast.LENGTH_SHORT).show();
            return;
        }
        int tot = searchClasses(subject);
        int pot = searchAttended(subject);
        int mat = searchExpectationlValue(subject);
        totalClasses.set(tot, subject + " " + currentClasses);
        totalAttended.set(pot, subject + " " + currentAttended);
        expectationalValue.set(mat, subject+" "+expecationalValue);
        Toast.makeText(this, "Data has been saved", Toast.LENGTH_SHORT).show();
        saveClasses();
        saveAttended();
        saveExpectationalValue();
        saveSwitches();
    }

    private void saveSwitches(){
        String s="";
        int i=0;
        while(i<8){
            s=s+arr[i];
            i++;
        }

        int mat = searchWeekends(subject);
        weekends.set(mat, subject + " " + s);

        SharedPreferences prefs=getSharedPreferences("yourPrefsKeyOfWeekends",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(weekends);
        edit.putStringSet("yourKeyOfWeekends", set);
        edit.apply();
    }

    private void saveClasses(){
        SharedPreferences prefs=getSharedPreferences("yourPrefsKeyOfClasses",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(totalClasses);
        edit.putStringSet("yourKeyOfClasses", set);
        edit.apply();
    }

    private void saveAttended(){
        SharedPreferences prefs=getSharedPreferences("yourPrefsKeyOfAttended",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(totalAttended);
        edit.putStringSet("yourKeyOfAttended", set);
        edit.apply();
    }

    private void saveExpectationalValue(){
        SharedPreferences prefs=getSharedPreferences("yourPrefsKeyOfExpectationalValue",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(expectationalValue);
        edit.putStringSet("yourKeyOfExpectationalValue", set);
        edit.apply();
    }

    public void clearUserName(Context ctx) {
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial= prefs.edit();
        editorial.clear();
        editorial.apply();

        SharedPreferences prefs1 = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial1=prefs1.edit();
        editorial1.clear();
        editorial1.apply();

        SharedPreferences prefs2 = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial2=prefs2.edit();
        editorial2.clear();
        editorial2.apply();

        SharedPreferences prefs3 = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial3=prefs3.edit();
        editorial3.clear();
        editorial3.apply();

        SharedPreferences prefs4 = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial4=prefs4.edit();
        editorial4.clear();
        editorial4.apply();

        SharedPreferences prefs5 = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial5=prefs5.edit();
        editorial5.clear();
        editorial5.apply();

        SharedPreferences prefs6 = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial6=prefs6.edit();
        editorial6.clear();
        editorial6.apply();

        SharedPreferences prefs7 = this.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial7=prefs7.edit();
        editorial7.clear();
        editorial7.apply();

        SharedPreferences prefs8 = this.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorial8=prefs8.edit();
        editorial8.clear();
        editorial8.apply();
    }

    public void logOut(){
        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("This will delete all the data and reset the entire application")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserName(Attendence.this);
                        startActivity(new Intent(Attendence.this, MainActivity.class));
                        finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final DrawerLayout drawerLayout=findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        subject = getIntent().getStringExtra("subject");
        TextView subjectId=findViewById(R.id.subjectId);
        subjectId.setText(subject);
        getTotalAttended();
        getTotalClasses();
        getWeekends();
        getExpectationalValue();
        getTheTotalClassesAndAttendedData(); // gets the current classes and attended
        setAllData();
        final DrawerLayout drawerLayout=findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.logout)
                    logOut();
                else if(menuItem.getItemId()== R.id.records) { }
                else {
                    startActivity(new Intent(Attendence.this, Records.class));
                    Attendence.this.finish();
                }
                drawerLayout.closeDrawers();
                menuItem.setCheckable(false);
                return true;
            }
        });
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(Attendence.this, "You'll receive notifications", Toast.LENGTH_SHORT).show();
                    buttonView.setText("ON    ");
                    arr[0]=1;
                }
                else{
                    Toast.makeText(Attendence.this, "Notifications closed", Toast.LENGTH_SHORT).show();
                    buttonView.setText("OFF   ");
                    arr[0]=0;
                }
            }
        });

        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[1]=1;
                else
                    arr[1]=0;
            }
        });

        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[2]=1;
                else
                    arr[2]=0;
            }
        });

        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[3]=1;
                else
                    arr[3]=0;
            }
        });

        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[4]=1;
                else
                    arr[4]=0;
            }
        });

        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[5]=1;
                else
                    arr[5]=0;
            }
        });

        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[6]=1;
                else
                    arr[6]=0;
            }
        });

        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    arr[7]=1;
                else
                    arr[7]=0;
            }
        });

        //Start of chaos

        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
        circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.BOTTOM_TO_END);
        circularProgressBar.setProgressMax(100f);
        circularProgressBar.setProgressBarColorStart(Color.RED);
        circularProgressBar.setProgressBarColorEnd(Color.RED);
        circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.LEFT_TO_RIGHT);
        circularProgressBar.setBackgroundProgressBarColorStart(Color.BLACK);
        circularProgressBar.setBackgroundProgressBarColorEnd(Color.BLACK);
        circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.BOTTOM_TO_END);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
        circularProgressBar.setProgressBarWidth(7f);
        circularProgressBar.setBackgroundProgressBarWidth(10f);
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);

        EditText classes = findViewById(R.id.editText9);
        classes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    EditText classes = findViewById(R.id.editText9);
                    EditText attended = findViewById(R.id.editText10);
                    int attendedField = Integer.parseInt(attended.getText().toString());
                    int classesField = Integer.parseInt(classes.getText().toString());
                    TextView percent = findViewById(R.id.textView17);
                    float percentage = attendedField;
                    percentage = (percentage / classesField) * 100;
                    String percentString;
                    try {
                        percentString = (percentage + "").substring(0, 5);
                    } catch (Exception e) {
                        percentString = (percentage + "").toString().substring(0, (percentage+"").length());
                    }
                    percent.setText(percentString + "%");
                    CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
                    circularProgressBar.setProgressWithAnimation(Float.parseFloat(percentString), 2000L);
                }
                catch (Exception e){
                    //Toast.makeText(Attendence.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText attended = findViewById(R.id.editText10);
        attended.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    EditText classes = findViewById(R.id.editText9);
                    EditText attended = findViewById(R.id.editText10);
                    int attendedField = Integer.parseInt(attended.getText().toString());
                    int classesField = Integer.parseInt(classes.getText().toString());
                    TextView percent = findViewById(R.id.textView17);
                    float percentage = attendedField;
                    percentage = (percentage / classesField) * 100;
                    String percentString;
                    try {
                        percentString = (percentage + "").substring(0, 5);
                    } catch (Exception e) {
                        percentString = (percentage + "").toString().substring(0, (percentage+"").length());
                    }
                    percent.setText(percentString + "%");
                    CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
                    circularProgressBar.setProgressWithAnimation(Float.parseFloat(percentString), 2000L);
                }
                catch (Exception e){
                    //Toast.makeText(Attendence.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, Records.class));
    }

}


/*
                    EditText classes = findViewById(R.id.editText9);
                    EditText attended = findViewById(R.id.editText10);
                    int attendedField=Integer.parseInt(attended.getText().toString());
                    int classesField=Integer.parseInt(classes.getText().toString());
                    TextView percent = findViewById(R.id.textView17);
                    float percentage = attendedField;
                    percentage = (percentage / classesField) * 100;
                    String percentString;
                    try{ percentString=(percentage+"").substring(0, 5);}
                    catch (Exception e){percentString=(percentage+"").toString().substring(0, percent.length());}
                    percent.setText(percentString + "%");
                    CircularProgressBar circularProgressBar = findViewById(R.id.circularProgressBar);
                    circularProgressBar.setProgressWithAnimation(Float.parseFloat(percentString), 2000L);
*/