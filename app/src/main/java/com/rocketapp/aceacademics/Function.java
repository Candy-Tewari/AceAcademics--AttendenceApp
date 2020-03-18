package com.rocketapp.aceacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.PreferenceChangeEvent;

public class Function extends AppCompatActivity {


   private int position=-1;
   private DrawerLayout drawerLayout;
   private ActionBarDrawerToggle actionBarDrawerToggle;
   ArrayList<String> subjectNames=new ArrayList<>();
   ArrayList<String> totalClasses=new ArrayList<>();
   ArrayList<String> totalAttended=new ArrayList<>();
   ArrayList<String> totalMST1=new ArrayList<>();
   ArrayList<String> totalMST2=new ArrayList<>();
   ArrayList<String> totalMajors=new ArrayList<>();
   ArrayList<String> totalExtras=new ArrayList<>();

   ArrayList<String> expectationalValue=new ArrayList<>();
   ArrayList<String> weekends=new ArrayList<>();

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public void goToHomePage(){
        startActivity(new Intent(this, MainActivity.class));
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
    public void comingSoon(View view){

        ConstraintLayout constraintLayout=findViewById(R.id.addfunction);
        if(constraintLayout.getVisibility()==View.VISIBLE){
            constraintLayout.setVisibility(View.GONE);
            constraintLayout.animate().translationYBy(-1000f).setDuration(300);
            allNormal();
            return;
        }
        constraintLayout.setVisibility(View.VISIBLE);
        constraintLayout.animate().translationYBy(1000f).setDuration(300);
        ListView listView=findViewById(R.id.listView);
        listView.setVisibility(View.GONE);
        Button button=findViewById(R.id.button5);
        button.setVisibility(View.GONE);
        EditText editText=findViewById(R.id.sub);
        editText.setText("");
    }
    private String returnNoSpaces(String subject){
        String newSubject="";
        for(int i=0;i<subject.length();i++){
            if(subject.charAt(i)==(char)32) {
                newSubject = newSubject + '_';
                continue;
            }
            newSubject=newSubject+subject.charAt(i);
        }
        return newSubject;
    }
    public void comingSoon(){
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        elementsPresents();
        ConstraintLayout constraintLayout=findViewById(R.id.addfunction);
        constraintLayout.setTranslationY(-1000f);
        drawerLayout=findViewById(R.id.drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
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
                    startActivity(new Intent(Function.this, Records.class));
                    Function.this.finish();
                }
                drawerLayout.closeDrawers();
                menuItem.setCheckable(false);
                return true;
            }
        });
    }
    public void logOut(){
        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("This will delete all the data and reset the entire application")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserName(Function.this);
                        startActivity(new Intent(Function.this, MainActivity.class));
                        finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return onOptionsItemSelected(item);
    }


    public void inflate(){
        ListView listView=findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, subjectNames);
        listView.setAdapter(arrayAdapter);
    }

    public void getSubject(View view) {

        EditText editText = findViewById(R.id.sub);

        Button delete=findViewById(R.id.del);
        if(delete.getVisibility()==View.VISIBLE){
            rename(editText.getText().toString(), position);
            return;
        }

        String subject = editText.getText().toString().trim();
        subject=returnNoSpaces(subject);
        if (subject.equals("")) {
            Toast.makeText(this, "Enter Subject", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!searchForDuplicates(subject)){
            subjectNames.add(subject);
            totalClasses.add(subject+" 0");
            totalAttended.add(subject+" 0");
            totalMST1.add(subject+" -1.0");
            totalMST2.add(subject+" -1.0");
            totalMajors.add(subject+" -1.0");
            totalExtras.add(subject+" -1.0");

            weekends.add(subject+" 10000000");
            expectationalValue.add(subject+" 75.0");
        }
        ConstraintLayout constraintLayout = findViewById(R.id.addfunction);
        constraintLayout.setVisibility(View.GONE);


        constraintLayout.animate().translationYBy(-1000f).setDuration(300);

        ListView listView = findViewById(R.id.listView);
        inflate();
        listView.setVisibility(View.VISIBLE);
        Button button = findViewById(R.id.button5);
        button.setVisibility(View.VISIBLE);
    }
    public String returnName(String subject){
        int indexOfSpace=subject.indexOf(' ');
        return subject.substring(0, indexOfSpace);
    }

    public int returnNumber(String subject){
        int indexOfSpace=subject.indexOf(' ');
        return Integer.parseInt(subject.substring(indexOfSpace+1));
    }

    public int searchClasses(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalClasses.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int searchAttended(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalAttended.get(n))))
                return n;
            n++;
        }
        return -1;
    }

    public int searchMST1(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalMST1.get(n))))
                return n;
            n++;
        }
        return -1;
    }
    public int searchMST2(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalMST2.get(n))))
                return n;
            n++;
        }
        return -1;
    }
    public int searchMajors(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalMajors.get(n))))
                return n;
            n++;
        }
        return -1;
    }
    public int searchExtras(String subject){
        int n=0;
        while(n<subjectNames.size()){
            if(subject.equals(returnName(totalExtras.get(n))))
                return n;
            n++;
        }
        return -1;
    }
    public int searchWeekends(String subject){
        int n=0;
        while(n<weekends.size()){
            if(subject.equals(returnName(weekends.get(n))))
                return n;
            n++;
        }
        return -1;
    }
    public int searchExpectationlValue(String subject){
        int n=0;
        while(n<expectationalValue.size()){
            if(subject.equals(returnName(expectationalValue.get(n))))
                return n;
            n++;
        }
        return -1;
    }


        private void remove(int positio){
            int tot=searchClasses(subjectNames.get(positio));
            int pot=searchAttended(subjectNames.get((positio)));
            int tat1=searchMST1(subjectNames.get(positio));
            int tat2=searchMST2(subjectNames.get(positio));
            int tat3=searchMajors(subjectNames.get(positio));
            int tat4=searchExtras(subjectNames.get(positio));
            int rat=searchWeekends(subjectNames.get(positio));
            int mat=searchExpectationlValue(subjectNames.get(positio));
            System.out.println("Removing: "+totalClasses.get(tot));
            System.out.println("Removing: "+totalAttended.get(pot));
            System.out.println("Removing: "+totalMST1.get(tat1));
            System.out.println("Removing: "+totalMST2.get(tat2));
            System.out.println("Removing: "+totalMajors.get(tat3));
            System.out.println("Removing: "+totalExtras.get(tat4));
            System.out.println("Removing: "+ weekends.get(rat));
            System.out.println("Removing: "+expectationalValue.get(mat));
            totalAttended.remove(pot);
            totalClasses.remove(tot);
            totalMST1.remove(tat1);
            totalMST2.remove(tat2);
            totalMajors.remove(tat3);
            totalExtras.remove(tat4);
            weekends.remove(rat);
            expectationalValue.remove(mat);
            subjectNames.remove(positio);
            inflate();
            allNormal();
        }

        public void removeSubject(View view){
            remove(position);
        }

        private void changeArrayListElement(int pos){
            ListView listVieww=findViewById(R.id.listView);
            listVieww.setVisibility(View.GONE);
            ConstraintLayout constraintLayout=findViewById(R.id.addfunction);
            constraintLayout.setVisibility(View.VISIBLE);

            constraintLayout.animate().translationYBy(1000f).setDuration(300);

            Button delete=findViewById(R.id.del);
            delete.setVisibility(View.VISIBLE);
            EditText editTextt=findViewById(R.id.sub);
            editTextt.setText(subjectNames.get(pos));
            position=pos;
            Button button = findViewById(R.id.button5);
            button.setVisibility(View.GONE);
        }

        private boolean searchForDuplicates(String s){
            if(subjectNames.contains(s)) {
                Toast.makeText(this, "ELement already Present", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }

        private void allNormal(){
            ListView listVieww=findViewById(R.id.listView);
            listVieww.setVisibility(View.VISIBLE);
            ConstraintLayout constraintLayout=findViewById(R.id.addfunction);
            constraintLayout.setVisibility(View.GONE);

            constraintLayout.animate().translationYBy(-1000f).setDuration(300);

            Button delete=findViewById(R.id.del);
            delete.setVisibility(View.GONE);
            Button button = findViewById(R.id.button5);
            button.setVisibility(View.VISIBLE);
        }

    private void rename(String subjectName, int positi){
        if(subjectName.equals(subjectNames.get(positi))) {
            allNormal();
            return;
        }
        if(subjectName.equals("")){
            remove(positi);
            allNormal();
            return;
        }
        if(!searchForDuplicates(subjectName)) {
            String subject=subjectNames.get(positi);
            int tot=searchClasses(subjectNames.get(positi));
            int pot=searchAttended(subjectNames.get(positi));
            int str=Integer.parseInt(totalClasses.get(tot).substring(subject.length()+1));
            int svtr=Integer.parseInt(totalAttended.get(pot).substring(subject.length()+1));
            totalAttended.set(pot, subjectName+" "+svtr);
            totalClasses.set(tot, subjectName+" "+str);
            subjectNames.set(positi, subjectName);
        }
        inflate();
        allNormal();
    }

    private void saveMyData(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKey",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(subjectNames);
        edit.putStringSet("yourKey", set);
        edit.apply();
    }

    public void done(View view){
        if(subjectNames.size()==0){
            Toast.makeText(this, "Add Subjects", Toast.LENGTH_SHORT).show();
            return;
        }
        saveMyData();
        totalAttended();
        totalClasses();
        totalMST1();
        totalMST2();
        totalMajors();
        totalExtras();
        totalWeekends();
        totalExpectationalValue();
        System.out.println("--------------------------------------------------------------------------------------"+totalAttended.size());
        startActivity(new Intent(this, Records.class));
    }

    private void elementsPresents(){
        try {
            getTotalAttended();
            getTotalClasses();
            getTotalMST1();
            getTotalMST2();
            getTotalMajors();
            getTotalExtras();
            getExpectationalValue();
            getWeekends();
            SharedPreferences prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
            Set<String> set = prefs.getStringSet("yourKey", null);
            ArrayList<String> sample = new ArrayList<String>(set);
            subjectNames=sample;
            ListView listView=findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    changeArrayListElement(position);
                }
            });
            inflate();
        }
        catch (Exception e){}
    }

    void getTotalClasses(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfClasses", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalClasses=sample;
    }

    void getTotalAttended(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfAttended", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalAttended=sample;
    }
    void getTotalMST1(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfMST1", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalMST1=sample;
    }
    void getTotalMST2(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfMST2", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalMST2=sample;
    }
    void getTotalMajors(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfMajors", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalMajors=sample;
    }
    void getTotalExtras(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfExtras", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        totalExtras=sample;
    }
    void getWeekends(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfWeekends", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        weekends=sample;
    }
    void getExpectationalValue(){
        SharedPreferences prefs = this.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("yourKeyOfExpectationalValue", null);
        ArrayList<String> sample = new ArrayList<String>(set);
        expectationalValue=sample;
    }
    private void totalClasses(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfClasses",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalClasses);
        edit.putStringSet("yourKeyOfClasses", set);
        edit.apply();
    }
    private void totalAttended(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfAttended",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalAttended);
        edit.putStringSet("yourKeyOfAttended", set);
        edit.apply();
    }

    private void totalMST1(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfMST1",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalMST1);
        edit.putStringSet("yourKeyOfMST1", set);
        edit.apply();
    }
    private void totalMST2(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfMST2",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalMST2);
        edit.putStringSet("yourKeyOfMST2", set);
        edit.apply();
    }
    private void totalMajors(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfMajors",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalMajors);
        edit.putStringSet("yourKeyOfMajors", set);
        edit.apply();
    }
    private void totalExtras(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfExtras",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(totalExtras);
        edit.putStringSet("yourKeyOfExtras", set);
        edit.apply();
    }
    private void totalWeekends(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfWeekends",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(weekends);
        edit.putStringSet("yourKeyOfWeekends", set);
        edit.apply();
    }
    private void totalExpectationalValue(){
        SharedPreferences prefs=this.getSharedPreferences("yourPrefsKeyOfExpectationalValue",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(expectationalValue);
        edit.putStringSet("yourKeyOfExpectationalValue", set);
        edit.apply();
    }
}