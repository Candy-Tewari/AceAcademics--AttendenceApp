package com.rocketapp.aceacademics

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList


class Records : AppCompatActivity() {

    lateinit  var subjectList : ArrayList<String>
    lateinit var totalClasses: ArrayList<String>
    lateinit var totalAttended: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
        getListView()
        getTotalAttended()
        getTotalClasses()
        printIt()
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        addElementsToCard()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val navigationView = findViewById<View>(R.id.navigationView) as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.logout) logOut()
            else if(menuItem.itemId == R.id.records) records()
            else {

            }
            drawerLayout.closeDrawers()
            menuItem.isCheckable = false
            true
        }
    }

    fun returnName(subject: String): String{
        val indexOfSpace=subject.indexOf(' ')
        return subject.substring(0, indexOfSpace)
    }

    fun returnNumber(subject: String): Int{
        val indexOfSpace=subject.indexOf(' ')
        return subject.substring(indexOfSpace+1).toInt()
    }

    fun calculatePercentage(classes: Int, attendence: Int): String {
        val classesFloat=classes.toFloat()
        var percentageAttendence: Float
        if(classes==0)
            percentageAttendence=100.0f
        else
            percentageAttendence=(attendence/classesFloat) *100
        var countedAttendence=percentageAttendence.toString()
        countedAttendence+="00000"
        return countedAttendence.substring(0, 5)
    }

    fun searchClasses(subject: String): Int{
        var n=0
        while(n<subjectList.size){
            if(subject.equals(returnName(totalClasses[n])))
                return n
            n++
        }
       return -1
    }

    fun searchAttended(subject: String): Int{
        var n=0
        while(n<subjectList.size){
            if(subject.equals(returnName(totalAttended[n])))
                return n
            n++
        }
        return -1
    }

    fun printIt(){
        var n=0
        System.out.println(" "+subjectList.size+" "+totalAttended.size+" "+totalClasses.size)
        while(n<subjectList.size){
           System.out.print(totalClasses.get(n)+" ")
            n++
        }
        System.out.println("")
        n=0
        while(n<subjectList.size){
            System.out.print(totalAttended.get(n)+" ")
            n++
        }
        n=0
        while(n<subjectList.size){
            //System.out.println("Hello i am in print")
            var subject=subjectList.get(n)
            val tot=searchClasses(subjectList.get(n))
            val pot=searchAttended(subjectList.get((n)))
            if(tot==-1) {
                System.out.println("Error of -1 in searchClasses "+subject)
                n++
                continue;
            }
            if(pot==-1){
                System.out.println("Error of -1 in searchAttended "+subject)
                n++
                continue;
            }
            System.out.println("tot and pop calculated")
            var str=totalClasses.get(tot).substring(subject.length+1, totalClasses.get(tot).length)
            var svtr=totalAttended.get(pot).substring(subject.length+1, totalAttended.get(pot).length)
            System.out.println(subject+" "+str+" "+svtr)
            n++
        }
    }


    fun addInvidual(subject: String, classes: Int, attendence: Int){
        val AcademicButton=Button(this)
        var dynamicClasses=classes
        var dynamicAttendence=attendence
        val linearLayout=findViewById<LinearLayout>(R.id.parentOfAllUI) as LinearLayout
        val percentage=TextView(this)
        var percentageAttendence=calculatePercentage(classes, attendence)
        percentage.setText(""+percentageAttendence+"%")
        percentage.setTextSize(20.0f)
        val linearLayoutNew=LinearLayout(this)
        val linearLayoutForClasses=LinearLayout(this)
        val linearLayoutForAttended=LinearLayout(this)
        val linearLayoutForPercentage=LinearLayout(this)
        val linearLayoutForTopBar=LinearLayout(this)
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                630
        )
        //Top bar
        val paramsForTopTextViewOfSubjects = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                20
        )
        paramsForTopTextViewOfSubjects.setMargins(10, 10, 10, 10)
        //Top Bar

        val paramsForInnerText = LinearLayout.LayoutParams(
                400,
                50
        )
        val paramsForInnerTextClass = LinearLayout.LayoutParams(
                100,
                50
        )
        val buttonUse=LinearLayout.LayoutParams(
                80,
                80
        )
        val linearLayoutInsideCard=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val paramsPercentage=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val percentageAppearance=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val academicButton=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        percentageAppearance.setMargins(10, 5, 0, 0 )
        //paramsPercentage.setMargins(10, 200, 10, 0)
        paramsPercentage.setMargins(20, 10, 0, 10)
        buttonUse.setMargins(50, 20, 50, 10)

        linearLayoutInsideCard.setMargins(10, 15, 10, 15)
        paramsForInnerTextClass.setMargins(0, 10, 0, 10)
        percentage.setLayoutParams(percentageAppearance)
        val buttonForAddClasses=Button(this)
        val buttonForSubtractClasses=Button(this)

        val buttonForAddAttended=Button(this)
        val buttonForSubtractAttended=Button(this)

        val universalButton=Button(this)

        buttonForAddClasses.setBackgroundDrawable(ContextCompat.getDrawable(this@Records, R.drawable.plus) )
        buttonForAddClasses.setLayoutParams(buttonUse)

        buttonForAddAttended.setBackgroundDrawable(ContextCompat.getDrawable(this@Records, R.drawable.plus) )
        buttonForAddAttended.setLayoutParams(buttonUse)

        buttonForSubtractAttended.setBackgroundDrawable(ContextCompat.getDrawable(this@Records, R.drawable.negative) )
        buttonForSubtractAttended.setLayoutParams(buttonUse)

        buttonForSubtractClasses.setBackgroundDrawable(ContextCompat.getDrawable(this@Records, R.drawable.negative) )
        buttonForSubtractClasses.setLayoutParams(buttonUse)


        params.setMargins(50, 50, 50, 50)
        paramsForInnerText.setMargins(10, 10, 10, 10)
        linearLayoutNew.setLayoutParams(params);
        linearLayoutForTopBar.setOrientation(LinearLayout.HORIZONTAL)
        linearLayoutNew.setOrientation(LinearLayout.VERTICAL)
        linearLayoutForClasses.setOrientation(LinearLayout.HORIZONTAL)
        linearLayoutForAttended.setOrientation(LinearLayout.HORIZONTAL)
        linearLayoutForPercentage.setOrientation(LinearLayout.HORIZONTAL)
        val textFieldForClasses=TextView(this)
        textFieldForClasses.setText("Total Classes ")
        textFieldForClasses.setTextSize(17.0f)
        val textFieldForClassesCount=TextView(this)
        textFieldForClassesCount.setText(""+classes)
        textFieldForClassesCount.setLayoutParams(paramsForInnerTextClass)
        textFieldForClasses.setLayoutParams(paramsForInnerText)
        val textFieldForAttended=TextView(this)
        textFieldForAttended.setText("Classes attended ")
        textFieldForAttended.setTextSize(17.0f)
        textFieldForAttended.setLayoutParams(paramsForInnerText)
        val textFieldForAttendedCount=TextView(this)
        textFieldForAttendedCount.setText(""+attendence)
        textFieldForAttendedCount.setLayoutParams(paramsForInnerTextClass)
        linearLayoutNew.setBackgroundResource(R.drawable.corner)
        val textViewForPercentage=TextView(this)
        textViewForPercentage.setText("Current Percentage     ")
        textViewForPercentage.setTextSize(18.0f)
        val textView=TextView(this)
        textView.setBackgroundResource(R.drawable.corner)
        textView.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        var tempo=subject
        tempo=tempo.toUpperCase()
        if(tempo.length>28)
            tempo=tempo.substring(0, 29)
        textView.setText(tempo)
        textView.setTextSize(20.0f)
        textView.setTypeface(null, Typeface.BOLD);
        //textView.setLayoutParams(paramsForTopTextViewOfSubjects)
        textView.setMaxHeight(70)
        //linearLayoutNew.addView(textView)
        linearLayout.addView(linearLayoutNew)

        //changes start from here

        linearLayoutForTopBar.addView(textView)
        val textViewTopBarParams=LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2.0f
        )
        val universalButtonTopBarParams=LinearLayout.LayoutParams(
                100,100
        )
        universalButtonTopBarParams.setMargins(60, 10, 0, 0)
        academicButton.setMargins(30, 20, 30, 20)
        academicButton.gravity=Gravity.CENTER
        universalButton.setBackgroundResource(R.drawable.custom_round)

        AcademicButton.setText("Academics")
        AcademicButton.setTextColor(getColor(R.color.buttonTextColor))
        AcademicButton.setBackgroundResource(R.drawable.cornerbutton)
        AcademicButton.setTextSize(15f)
        textView.setLayoutParams(textViewTopBarParams)
        //linearLayoutForTopBar.addView(universalButton)
        universalButton.setLayoutParams(universalButtonTopBarParams)
        AcademicButton.setLayoutParams(academicButton)
        linearLayoutNew.addView(linearLayoutForTopBar)
        //linearLayoutNew.addView(textView)

        //changes end here


        linearLayoutForClasses.addView(textFieldForClasses)
        linearLayoutForClasses.addView(textFieldForClassesCount)
        linearLayoutForClasses.addView(buttonForAddClasses)
        linearLayoutForClasses.addView(buttonForSubtractClasses)
        linearLayoutForAttended.addView(textFieldForAttended)
        linearLayoutForAttended.addView(textFieldForAttendedCount)
        linearLayoutForAttended.addView(buttonForAddAttended)
        linearLayoutForAttended.addView(buttonForSubtractAttended)
        linearLayoutForClasses.setLayoutParams(linearLayoutInsideCard)
        linearLayoutForAttended.setLayoutParams(linearLayoutInsideCard)
        linearLayoutForPercentage.setLayoutParams(paramsPercentage)
        linearLayoutForPercentage.addView(textViewForPercentage)
        linearLayoutForPercentage.addView(percentage)

        linearLayoutForPercentage.addView(universalButton)

        linearLayoutNew.addView(linearLayoutForPercentage)
        linearLayoutNew.addView(linearLayoutForClasses)
        linearLayoutNew.addView(linearLayoutForAttended)
        linearLayoutNew.addView(AcademicButton)

        buttonForAddClasses.setOnClickListener{
            dynamicClasses++
            textFieldForClassesCount.setText(""+dynamicClasses)
            var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
            percentage.setText(""+percentageAttendence+"%")
            updateList(subject,dynamicClasses, dynamicAttendence)
        }

        buttonForSubtractClasses.setOnClickListener{
            if(dynamicClasses!=0) {
                dynamicClasses--
                textFieldForClassesCount.setText(""+dynamicClasses)
                var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
                percentage.setText(""+percentageAttendence+"%")

                if(dynamicAttendence>dynamicClasses){
                    dynamicAttendence--
                    textFieldForAttendedCount.setText(""+dynamicAttendence)
                    var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
                    percentage.setText(""+percentageAttendence+"%")
                }

                updateList(subject,dynamicClasses, dynamicAttendence)
            }
        }

        buttonForAddAttended.setOnClickListener{
            if(dynamicAttendence<dynamicClasses) {
                dynamicAttendence++
                textFieldForAttendedCount.setText("" + dynamicAttendence)
                var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
                percentage.setText(""+percentageAttendence+"%")
                updateList(subject,dynamicClasses, dynamicAttendence)
            }
        }

        buttonForSubtractAttended.setOnClickListener{
            if(dynamicAttendence>0) {
                dynamicAttendence--
                textFieldForAttendedCount.setText("" + dynamicAttendence)
                var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
                percentage.setText(""+percentageAttendence+"%")
                updateList(subject,dynamicClasses, dynamicAttendence)
            }
        }
        universalButton.setOnClickListener{
            dynamicAttendence++
            dynamicClasses++
            textFieldForAttendedCount.setText("" + dynamicAttendence)
            textFieldForClassesCount.setText(""+dynamicClasses)
            var percentageAttendence=calculatePercentage(dynamicClasses, dynamicAttendence)
            percentage.setText(""+percentageAttendence+"%")
            updateList(subject, dynamicClasses, dynamicAttendence)
        }
        linearLayoutNew.setOnClickListener{


            var intent=Intent(this, Attendence::class.java)
            intent.putExtra("subject", subject)
            startActivity(intent)
            this@Records.finish()

            //Update dynamic classes and dynamic attendence

            getTotalAttended()
            getTotalClasses()
            val tot=searchClasses(subject)
            val pot=searchAttended(subject)
            dynamicAttendence=returnNumber(totalAttended.get(pot))
            dynamicClasses=returnNumber(totalClasses.get(tot))
            textFieldForAttendedCount.setText("" + dynamicAttendence)
            textFieldForClassesCount.setText(""+dynamicClasses)

            //Update dynamic classes and dynamic attendence
        }
        AcademicButton.setOnClickListener {
            var intent=Intent(this, Information::class.java)
            intent.putExtra("subject", subject)
            startActivity(intent)
        }
    }

    private fun saveMyDataOfClasses() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalClasses)
        edit.putStringSet("yourKeyOfClasses", set)
        edit.apply()
    }

    private fun saveMyDataOfAttended() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalAttended)
        edit.putStringSet("yourKeyOfAttended", set)
        edit.apply()
    }

    fun updateList(subject: String, classes: Int, attended: Int){
        val indexClasses=searchClasses(subject)
        val indexAttended=searchAttended(subject)
        totalClasses.set(indexClasses, subject+" "+classes)
        totalAttended.set(indexAttended, subject+" "+attended)
        saveMyDataOfClasses()
        saveMyDataOfAttended()
    }

    fun addElementsToCard(){
        var n=0
        val size=subjectList.size
        while(n<size){
            val subject=subjectList.get(n)
            val indexClasses=searchClasses(subject)
            val indexAttended=searchAttended(subject)
            addInvidual(subject, returnNumber(totalClasses[indexClasses]), returnNumber(totalAttended[indexAttended]))
            n++
        }
    }

    fun getListView(){
        val prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKey", null)
        subjectList = ArrayList(set!!)
    }

    fun getTotalClasses(){
        val prefs = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfClasses", null)
        totalClasses = ArrayList(set!!)
    }

    fun getTotalAttended(){
        val prefs = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfAttended", null)
        totalAttended = ArrayList(set!!)
    }
    fun records(){
        val intent = Intent(this, Function::class.java)
        // start your next activity
        startActivity(intent)
    }
    fun logOut() {
        AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("This will delete all the data and reset the entire application")
                .setPositiveButton("Yes") { dialog, which ->
                    clearUserName(this@Records)
                    startActivity(Intent(this@Records, MainActivity::class.java))
                    Toast.makeText(this, "All data deleted", Toast.LENGTH_LONG).show()
                    finish()
                    finishAffinity()
                }
                .setNegativeButton("No", null)
                .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) true else onOptionsItemSelected(item)
    }

    fun getSharedPreferences(ctx: Context?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun clearUserName(ctx: Context?) {
        val prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE)
        val editorial= prefs.edit()
        editorial.clear()
        editorial.apply()

        val prefs1 = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE)
        val editorial1=prefs1.edit()
        editorial1.clear()
        editorial1.apply()

        val prefs2 = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE)
        val editorial2=prefs2.edit()
        editorial2.clear()
        editorial2.apply()

        val prefs3 = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE)
        val editorial3=prefs3.edit()
        editorial3.clear()
        editorial3.apply()

        val prefs4 = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE)
        val editorial4=prefs4.edit()
        editorial4.clear()
        editorial4.apply()

        val prefs5 = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE)
        val editorial5=prefs5.edit()
        editorial5.clear()
        editorial5.apply()

        val prefs6 = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE)
        val editorial6=prefs6.edit()
        editorial6.clear()
        editorial6.apply()

        val prefs7 = this.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE)
        val editorial7=prefs7.edit()
        editorial7.clear()
        editorial7.apply()

        val prefs8 = this.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE)
        val editorial8=prefs8.edit()
        editorial8.clear()
        editorial8.apply()
    }
}
