package com.activity.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val currentClassName by lazy{
        this@MainActivity.javaClass.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBtnOne = findViewById<Button>(R.id.actionBtnOne)
        val actionBtnTwo = findViewById<Button>(R.id.actionBtnTwo)
        actionBtnOne.setOnClickListener {
            startActivity(Intent(applicationContext, SubActivityOne::class.java))
        }
        actionBtnTwo.setOnClickListener {
            startActivity(Intent(applicationContext, SubActivityTwo::class.java))
        }

        Log.e(currentClassName, """onCreate()""")
    }
    override fun onStart() {
        super.onStart()
        Log.e(currentClassName, """onStart()""")
    }
    override fun onRestart(){
        super.onRestart()
        Log.e(currentClassName, """onRestart()""")
    }

    override fun onResume() {
        super.onResume()
        Log.e(currentClassName, """onResume()""")
    }

    override fun onPause() {
        super.onPause()
        Log.e(currentClassName, """onPause()""")
    }
    override fun onStop(){
        super.onStop()
        Log.e(currentClassName, """onStop()""")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e(currentClassName, """onDestroy()""")
    }
}
