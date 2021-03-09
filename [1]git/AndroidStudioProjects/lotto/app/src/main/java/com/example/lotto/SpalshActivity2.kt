package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpalshActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh2)

        animationView.setOnClickListener {it:View!
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}