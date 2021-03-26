package com.basic.simple.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var intentView1: Button? = null
    private var intentView2: Button? = null
    private var intentView3: Button? = null
    private var intentView4: Button? = null
    private var intentView5: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentView1 = findViewById(R.id.intentBtn1)
        intentView2 = findViewById(R.id.intentBtn2)
        intentView3 = findViewById(R.id.intentBtn3)
        intentView4 = findViewById(R.id.intentBtn4)
        intentView5 = findViewById(R.id.intentBtn5)

        intentView1?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("content://contacts/people"))
            startActivity(intent)
        }
        intentView2?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel://010-3745-2833"))
            startActivity(intent)
        }
        intentView3?.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.nate.com"))
            startActivity(intent)
        }
        intentView4?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"))
            startActivity(intent)
        }
        intentView5?.setOnClickListener {
            val intent = Intent(Settings.ACTION_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}