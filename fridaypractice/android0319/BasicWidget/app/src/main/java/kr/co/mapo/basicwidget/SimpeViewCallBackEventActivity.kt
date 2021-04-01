package kr.co.mapo.basicwidget
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding

class SimpleViewCallBackEventActivity:Activity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewCallBack = ViewCallBackMethodImpl(this)
        val rootLayout = LinearLayout(this)
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootLayout.layoutParams = params
        viewCallBack.layoutParams = params
        viewCallBack.hint = " 뷰 콜백 메소드 "
        viewCallBack.textSize = 20F
        viewCallBack.typeface = Typeface.DEFAULT_BOLD
        rootLayout.addView(viewCallBack)
        setContentView(rootLayout)
    }
    //재정의 해야 만 함
    private inner class ViewCallBackMethodImpl(content:Context):androidx.appcompat.widget.AppCompatEditText(content) {
        //터치 이벤트 재정의
        override fun onTouchEvent(event:MotionEvent):Boolean {
            super.onTouchEvent(event)
            val eventCode = event.action
            if (eventCode == MotionEvent.ACTION_DOWN)
            {
                Toast.makeText(
                    applicationContext, (" 터치 이벤트 발생 좌표( X = "
                        + event.x + ",Y = " + event.y + " )"), Toast.LENGTH_SHORT
                ).show()
            }
            return true
        }
        //키 이벤트 재정의
        override fun onKeyDown(keyCode:Int, event:KeyEvent):Boolean {
            super.onKeyDown(keyCode, event)
            if (keyCode == KeyEvent.KEYCODE_BACK)
            {
                Toast.makeText(
                    applicationContext, (" 백키 누름 keyCode = " +
                        keyCode + ")"), Toast.LENGTH_SHORT).show()
                finish()
            }
            return true
        }
      /*//키 이벤트 재정의
     override fun onKeyUp(keyCode:Int, event: KeyEvent): Boolean {
          super.onKeyDown(keyCode, event)
              Toast.makeText(
                  applicationContext, " 키 업 이벤트 발생 ( keyCode = " +
                      keyCode , Toast.LENGTH_SHORT).show()
          return true
     }
     //트랙볼 정의
     override fun onTrackballEvent(event: MotionEvent): Boolean {
          super.onTrackballEvent(event)
     Toast.makeText(
         applicationContext, " 트랙볼 이벤트 발생 좌표( X = "
                      + event.getX() + ",Y = " + event.getY( ) + ")", Toast.LENGTH_SHORT).show();
          return true
     }*/
    }
}