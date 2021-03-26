package com.basic.service

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.basic.service.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtn.setOnClickListener {
            executeBtnEvent(it)
        }
        binding.stopBtn.setOnClickListener {
            executeBtnEvent(it)
        }
    }
    private fun executeBtnEvent(btn: View) {
        val serviceIntent = Intent(this@MainActivity, NewsSimpleService::class.java)
        if (R.id.startBtn == btn.id) {
            serviceIntent.putExtra("newsSubject", 3)
            //android Oreo 부터는 다음과 같이 호출한다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        } else {
            stopService(serviceIntent)
        }
    }
}
