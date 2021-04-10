package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import kr.co.mapo.basicwidget.databinding.ActivityCallbackLayoutBinding
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding

class ActivityCallbackEventActivity : Activity() {
    private val binding by lazy { ActivityCallbackLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val eventCode: Int = event!!.action
        if (eventCode == MotionEvent.ACTION_DOWN) {
            Toast.makeText(
                applicationContext,
                "터치 이벤트 발생 좌표(X =" + event.x + ",Y = " + event.y + ")",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }
}
    //키 이벤트 재정의
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        super.onKeyDown(keyCode, event)
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            Toast.makeText(applicationContext,
//                "백키 누름 keyCode = " + keyCode + ")",
//                Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        return true
//    }
    //키 이벤트 재정의

//    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
//        super.onKeyUp(keyCode, event)
//        Toast.makeText(applicationContext,
//            "키 업 이벤트 발생 (keyCode =)" + keyCode +")",
//            Toast.LENGTH_SHORT).show()
//        return true
//    }
    //트랙볼 정의

//    override fun onTrackballEvent(event: MotionEvent?): Boolean {
//        super.onTrackballEvent(event)
//        Toast.makeText(applicationContext,
//            "트랙볼 이벤트 발생 좌표( X="+ event!!.x +"," +
//                    " Y =" + event!!.y + ")",
//            Toast.LENGTH_SHORT).show()
//        return true
//    }
//}