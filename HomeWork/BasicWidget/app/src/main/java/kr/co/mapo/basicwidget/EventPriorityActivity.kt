package kr.co.mapo.basicwidget

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.EventLog
import android.view.MotionEvent
import android.view.View
import android.widget.ListView
import android.widget.Toast
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import kr.co.mapo.basicwidget.databinding.EventHandlerLayoutBinding

class EventPriorityActivity:Activity() {
    private val binding by lazy { EventHandlerLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val priorityHandler = findViewById<ListView>(R.id.btn_prioriry_handler)
        priorityHandler.setOnTouchListener { _, event ->
            if (event.action === MotionEvent.ACTION_DOWN) {
                Toast.makeText(
                        applicationContext, "버튼 터치 리스너 클래스!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            true
        }
    }
}
