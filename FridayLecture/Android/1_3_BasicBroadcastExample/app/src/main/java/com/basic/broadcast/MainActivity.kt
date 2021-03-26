package com.basic.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

const val BR_ACTION_NAME = "com.basic.broadcast.BR_ACTION_NAME"

class MainActivity : AppCompatActivity() {

    private var myBroadcast: BroadcastReceiver? = null
    private var increment = 0
    private lateinit var brMessage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        brMessage = findViewById(R.id.brMessage)
        val btn = findViewById<Button>(R.id.sendBtn)
        btn.setOnClickListener{
            val intent = Intent(BR_ACTION_NAME)
            intent.putExtra("key", ++increment)
            sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        /** 1. intent filter를 만든다
         * 2. intent filter에 BR ACTION 을 추가한다.
         * 3. BroadCastReceiver를 구현한다.(선언방식은 상속받아 만든다)
         * 4. intent filter와 BroadCastReceiver를 등록한다.
         **/
        if (myBroadcast != null) return
        val theFilter = IntentFilter()
        theFilter.addAction(BR_ACTION_NAME)

        myBroadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val receivedIntent = intent.getIntExtra("key", 0)
                if (intent.action == BR_ACTION_NAME)
                    brMessage.text = String.format("호출된 횟수는 %d 번 입니다", receivedIntent)
            }
        }
        registerReceiver(myBroadcast, theFilter)
    }
    override fun onStop() {
        super.onStop()
        if (myBroadcast != null) {
            unregisterReceiver(myBroadcast)
            myBroadcast = null
        }
    }
}
