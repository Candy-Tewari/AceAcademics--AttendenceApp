package com.rocketapp.aceacademics

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mikhaellopez.circularprogressbar.CircularProgressBar


class Information : AppCompatActivity() {

    lateinit var subjectList: ArrayList<String>
    lateinit var totalMST1: ArrayList<String>
    lateinit var totalMST2: ArrayList<String>
    lateinit var totalMajors: ArrayList<String>
    lateinit var totalExtras: ArrayList<String>
    lateinit var subject: String

    fun records() {
        val intent = Intent(this, Function::class.java)
        // start your next activity
        startActivity(intent)
        this@Information.finish()
    }

    fun clearUserName(ctx: Context?) {
        val prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE)
        val editorial = prefs.edit()
        editorial.clear()
        editorial.apply()

        val prefs1 = this.getSharedPreferences("yourPrefsKeyOfClasses", Context.MODE_PRIVATE)
        val editorial1 = prefs1.edit()
        editorial1.clear()
        editorial1.apply()

        val prefs2 = this.getSharedPreferences("yourPrefsKeyOfAttended", Context.MODE_PRIVATE)
        val editorial2 = prefs2.edit()
        editorial2.clear()
        editorial2.apply()

        val prefs3 = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE)
        val editorial3 = prefs3.edit()
        editorial3.clear()
        editorial3.apply()

        val prefs4 = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE)
        val editorial4 = prefs4.edit()
        editorial4.clear()
        editorial4.apply()

        val prefs5 = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE)
        val editorial5 = prefs5.edit()
        editorial5.clear()
        editorial5.apply()

        val prefs6 = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE)
        val editorial6 = prefs6.edit()
        editorial6.clear()
        editorial6.apply()

        val prefs7 = this.getSharedPreferences("yourPrefsKeyOfWeekends", Context.MODE_PRIVATE)
        val editorial7 = prefs7.edit()
        editorial7.clear()
        editorial7.apply()

        val prefs8 = this.getSharedPreferences("yourPrefsKeyOfExpectationalValue", Context.MODE_PRIVATE)
        val editorial8 = prefs8.edit()
        editorial8.clear()
        editorial8.apply()
    }

    fun logOut() {
        AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("This will delete all the data and reset the entire application")
                .setPositiveButton("Yes") { dialog, which ->
                    clearUserName(this@Information)
                    startActivity(Intent(this@Information, MainActivity::class.java))
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

    fun comingSoon() {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
    }

    fun printTotalMST1() {
        var n = 0
        while (n < totalMST1.size) {
            println(totalMST1[n])
            n++
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        subject = intent.getStringExtra("subject")
        var mst1 = findViewById<EditText>(R.id.editText5)

        getListView()
        getTotalMST1()
        getTotalMST2()
        getTotalMajors()
        getTotalExtras()
        printTotalMST1()
        println("==========================================================" + mst1.getText().toString() + " subject is: " + subject + " length of mst1 " + totalMST1.size)
        printTotalMST1()
        addItems()
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val navigationView = findViewById<View>(R.id.navigationView) as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.logout) logOut()
            else if (menuItem.itemId == R.id.records) records()
            else {
                startActivity(Intent(this@Information, Records::class.java))
                finish()
            }
            drawerLayout.closeDrawers()
            menuItem.isCheckable = false
            true

        }
        var subjectNameTextView = findViewById<TextView>(R.id.subjectId)
        subjectNameTextView.setText(subject)

        //Progress Bar start

        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            progressMax = 100f
            progressBarColorStart = Color.RED
            progressBarColorEnd = Color.RED
            progressBarColorDirection = CircularProgressBar.GradientDirection.LEFT_TO_RIGHT
            backgroundProgressBarColorStart = Color.BLACK
            backgroundProgressBarColorEnd = Color.BLACK
            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.BOTTOM_TO_END

            // Set Width
            progressBarWidth = 7f // in DP previous 7
            backgroundProgressBarWidth = 10f // in DP previous 3

            // Other
            roundBorder = true
            startAngle = 180f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT


        }

        //Progress Bar End

    }

    //Start Here
    fun returnName(subject: String): String {
        val indexOfSpace = subject.indexOf(' ')
        return subject.substring(0, indexOfSpace)
    }

    fun returnNumber(subject: String): Float {
        val indexOfSpace = subject.indexOf(' ')
        return subject.substring(indexOfSpace + 1).toFloat()
    }

    fun getListView() {
        val prefs = this.getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKey", null)
        subjectList = ArrayList(set!!)
    }

    fun getTotalMST1() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfMST1", null)
        totalMST1 = ArrayList(set!!)
    }

    fun getTotalMST2() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfMST2", null)
        totalMST2 = ArrayList(set!!)
    }

    fun getTotalMajors() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfMajors", null)
        totalMajors = ArrayList(set!!)
    }

    fun getTotalExtras() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("yourKeyOfExtras", null)
        totalExtras = ArrayList(set!!)
    }

    fun searchMST1(subject: String): Int {
        var n = 0
        while (n < subjectList.size) {
            if (subject.equals(returnName(totalMST1[n])))
                return n
            n++
        }
        return -1
    }

    fun searchMST2(subject: String): Int {
        var n = 0
        while (n < subjectList.size) {
            if (subject.equals(returnName(totalMST2[n])))
                return n
            n++
        }
        return -1
    }

    fun searchMajors(subject: String): Int {
        var n = 0
        while (n < subjectList.size) {
            if (subject.equals(returnName(totalMajors[n])))
                return n
            n++
        }
        return -1
    }

    fun searchExtras(subject: String): Int {
        var n = 0
        while (n < subjectList.size) {
            if (subject.equals(returnName(totalExtras[n])))
                return n
            n++
        }
        return -1
    }

    fun addItems() {
        var mst1 = findViewById<EditText>(R.id.editText5)
        var mst2 = findViewById<EditText>(R.id.editText6)
        var majors = findViewById<EditText>(R.id.editText7)
        var extras = findViewById<EditText>(R.id.editText8)

        mst1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeDuringRunTime()
            }
        })

        mst2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeDuringRunTime()
            }
        })

        majors.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeDuringRunTime()
            }
        })

        extras.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                changeDuringRunTime()
            }
        })


        val tat1 = searchMST1(subject)
        val tat2 = searchMST2(subject)
        val tat3 = searchMajors(subject)
        val tat4 = searchExtras(subject)
        if (returnNumber(totalMST1[tat1]) != -1.0f) {
            mst1.setText("" + returnNumber(totalMST1[tat1]))
        }
        if (returnNumber(totalMST2[tat2]) != -1.0f) {
            mst2.setText("" + returnNumber(totalMST2[tat2]))
        }
        if (returnNumber(totalMajors[tat3]) != -1.0f) {
            majors.setText("" + returnNumber(totalMajors[tat3]))
        }
        if (returnNumber(totalExtras[tat4]) != -1.0f) {
            extras.setText("" + returnNumber(totalExtras[tat4]))
        }
        var first = returnNumber(totalMST1[tat1])
        var second = returnNumber(totalMST2[tat2])
        var third = returnNumber(totalMajors[tat3])
        var fourth = returnNumber(totalExtras[tat4])
        if (first == -1.0f)
            first = 20.0f
        if (second == -1.0f)
            second = 20.0f
        if (third == -1.0f)
            third = 50.0f
        if (fourth == -1.0f)
            fourth = 10.0f
        val progress = first + second + third + fourth
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            setProgressWithAnimation(progress, 2000)
        }
        var textView = findViewById<TextView>(R.id.textView17)
        textView.setText("" + progress + "%")
    }

    private fun saveMyDataOfMST1() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMST1", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalMST1)
        edit.putStringSet("yourKeyOfMST1", set)
        edit.apply()
    }

    private fun saveMyDataOfMST2() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMST2", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalMST2)
        edit.putStringSet("yourKeyOfMST2", set)
        edit.apply()
    }

    private fun saveMyDataOfMajors() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfMajors", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalMajors)
        edit.putStringSet("yourKeyOfMajors", set)
        edit.apply()
    }

    private fun saveMyDataOfExtras() {
        val prefs = this.getSharedPreferences("yourPrefsKeyOfExtras", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(totalExtras)
        edit.putStringSet("yourKeyOfExtras", set)
        edit.apply()
    }

    fun changeDuringRunTime() {
        var mst1 = findViewById<EditText>(R.id.editText5)
        var mst2 = findViewById<EditText>(R.id.editText6)
        var majors = findViewById<EditText>(R.id.editText7)
        var extras = findViewById<EditText>(R.id.editText8)
        var grade = findViewById<TextView>(R.id.grade)
        var nextGrade = findViewById<TextView>(R.id.nextGrade)

        var data1: Float
        var data2: Float
        var data3: Float
        var data4: Float
        if (mst1.getText().toString().length == 0) {
            data1 = -1.0f
        } else {
            data1 = mst1.getText().toString().trim().toFloat()
        }
        if (mst2.getText().toString().length == 0) {
            data2 = -1.0f
        } else {
            data2 = mst2.getText().toString().trim().toFloat()
        }
        if (majors.getText().toString().length == 0) {
            data3 = -1.0f
        } else {
            data3 = majors.getText().toString().trim().toFloat()
        }
        if (extras.getText().toString().length == 0) {
            data4 = -1.0f
        } else {
            data4 = extras.getText().toString().trim().toFloat()
        }
        if (mst1.getText().toString().length != 0 && mst1.getText().toString().toFloat() > 20.0f) {
            Toast.makeText(this, "MST1 is only MM 20", Toast.LENGTH_SHORT).show()
            return
        }
        if (mst2.getText().toString().length != 0 && mst2.getText().toString().toFloat() > 20.0f) {
            Toast.makeText(this, "MST2 is only MM 20", Toast.LENGTH_SHORT).show()
            return
        }
        if (majors.getText().toString().length != 0 && majors.getText().toString().toFloat() > 50.0f) {
            Toast.makeText(this, "Majors is only MM 50", Toast.LENGTH_SHORT).show()
            return
        }
        if (extras.getText().toString().length != 0 && extras.getText().toString().toFloat() > 10.0f) {
            Toast.makeText(this, "Extras is only MM 10", Toast.LENGTH_SHORT).show()
            return
        }
        if (data1 == -1.0f)
            data1 = 20.0f
        if (data2 == -1.0f)
            data2 = 20.0f
        if (data3 == -1.0f)
            data3 = 50.0f
        if (data4 == -1.0f)
            data4 = 10.0f

        val progress = data1 + data2 + data3 + data4
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            setProgressWithAnimation(progress, 1000)
        }
        var textView = findViewById<TextView>(R.id.textView17)
        textView.setText("" + progress + "%")
        if (progress >= 90)
            grade.setText(" S ")
        else if (progress >= 80)
            grade.setText(" A ")
        else if (progress >= 70)
            grade.setText(" B ")
        else if (progress >= 60)
            grade.setText(" C ")
        else if (progress >= 50)
            grade.setText(" D ")
        else if (progress >= 40)
            grade.setText(" E ")
        else
            grade.setText(" U ")
        var next = (((progress.toInt()) / 10) + 1) * 10 - progress
        if (progress < 90)
            nextGrade.setText("" + next)
        else
            nextGrade.setText(" 0 ")
    }

    fun onclickSave(view: View) {
        var mst1 = findViewById<EditText>(R.id.editText5)
        var mst2 = findViewById<EditText>(R.id.editText6)
        var majors = findViewById<EditText>(R.id.editText7)
        var extras = findViewById<EditText>(R.id.editText8)

        var data1: Float
        var data2: Float
        var data3: Float
        var data4: Float
        if (mst1.getText().toString().length == 0) {
            data1 = -1.0f
        } else {
            data1 = mst1.getText().toString().trim().toFloat()
        }
        if (mst2.getText().toString().length == 0) {
            data2 = -1.0f
        } else {
            data2 = mst2.getText().toString().trim().toFloat()
        }
        if (majors.getText().toString().length == 0) {
            data3 = -1.0f
        } else {
            data3 = majors.getText().toString().trim().toFloat()
        }
        if (extras.getText().toString().length == 0) {
            data4 = -1.0f
        } else {
            data4 = extras.getText().toString().trim().toFloat()
        }
        if (mst1.getText().toString().length != 0 && mst1.getText().toString().toFloat() > 20.0f) {
            Toast.makeText(this, "MST1 is only MM 20", Toast.LENGTH_SHORT).show()
            return
        }
        if (mst2.getText().toString().length != 0 && mst2.getText().toString().toFloat() > 20.0f) {
            Toast.makeText(this, "MST2 is only MM 20", Toast.LENGTH_SHORT).show()
            return
        }
        if (majors.getText().toString().length != 0 && majors.getText().toString().toFloat() > 50.0f) {
            Toast.makeText(this, "Majors is only MM 50", Toast.LENGTH_SHORT).show()
            return
        }
        if (extras.getText().toString().length != 0 && extras.getText().toString().toFloat() > 10.0f) {
            Toast.makeText(this, "Extras is only MM 10", Toast.LENGTH_SHORT).show()
            return
        }
        val tat1 = searchMST1(subject)
        val tat2 = searchMST2(subject)
        val tat3 = searchMajors(subject)
        val tat4 = searchExtras(subject)


        totalMST1.set(tat1, subject + " " + data1)
        totalMST2.set(tat2, subject + " " + data2)
        totalMajors.set(tat3, subject + " " + data3)
        totalExtras.set(tat4, subject + " " + data4)
        saveMyDataOfMST1()
        saveMyDataOfMST2()
        saveMyDataOfMajors()
        saveMyDataOfExtras()
        Toast.makeText(this, "Data has been saved", Toast.LENGTH_SHORT).show()
    }
}
