package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.util.EventLog
import android.view.MotionEvent
import android.view.View
import android.widget.ListView
import android.widget.Toast
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding

class EventPriorityActivity:Activity() {
    companion object{}
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)
    fun onCreate(bundle:Bundle) {
        super.onCreate(bundle)
        setContentView(R.layout.event_handler_layout)
        val priorityHandler = findViewById<ListView>(R.id.btn_prioriry_handler)
        priorityHandler.setOnTouchListener(
                object: View.OnTouchListener {
            /*
           * 1번째 이벤트 발생
                   */
            override fun onTouch(v:View, event:MotionEvent):Boolean {
                if (event.getAction() === MotionEvent.ACTION_DOWN)
                {
                    Toast.makeText(getApplicationContext(), "버튼 터치 리스너 클래스!",
                            Toast.LENGTH_SHORT).show()
                    //true를 리턴하면 여기서 종료 됨
                    //return true;
                }
                return true
            }
        })
    }
}
}
