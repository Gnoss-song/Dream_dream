package com.activity.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SubActivityOne : AppCompatActivity() {
    private val currentClassName by lazy{
        this@SubActivityOne.javaClass.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_one)
        val finishBtn = findViewById<Button>(R.id.finishBtn)
        finishBtn.setOnClickListener { finish() }

        Log.e(this@SubActivityOne.javaClass.toString(), """onCreate()""")
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
