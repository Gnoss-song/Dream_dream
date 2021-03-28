package com.pyo.android.simple.widget
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

class SimpleViewCallBackEventActivity:Activity() {
    fun onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        val viewCallBack = ViewCallBackMethodImpl(this)
        val rootLayout = LinearLayout(this)
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        rootLayout.setLayoutParams(params)
        viewCallBack.setLayoutParams(params)
        viewCallBack.setHint(" 뷰 콜백 메소드 ")
        viewCallBack.setTextSize(20)
        viewCallBack.setTypeface(Typeface.DEFAULT_BOLD)
        rootLayout.addView(viewCallBack)
        setContentView(rootLayout)
    }
    //재정의 해야 만 함
    @SuppressLint("AppCompatCustomView")
    private inner class ViewCallBackMethodImpl(content:Context):EditText(content) {
        //터치 이벤트 재정의
        fun onTouchEvent(event:MotionEvent):Boolean {
            super.onTouchEvent(event)
            val eventCode = event.getAction()
            if (eventCode == MotionEvent.ACTION_DOWN)
            {
                Toast.makeText(getApplicationContext(), (" 터치 이벤트 발생 좌표( X = "
                        + event.getX() + ",Y = " + event.getY() + " )"), Toast.LENGTH_SHORT
                ).show()
            }
            return true
        }
        //키 이벤트 재정의
        fun onKeyDown(keyCode:Int, event:KeyEvent):Boolean {
            super.onKeyDown(keyCode, event)
            if (keyCode == KeyEvent.KEYCODE_BACK)
            {
                Toast.makeText(getApplicationContext(), (" 백키 누름 keyCode = " +
                        keyCode + ")"), Toast.LENGTH_SHORT).show()
                finish()
            }
            return true
        }
        /*//키 이벤트 재정의
     @Override
     public boolean onKeyUp(int keyCode, KeyEvent event){
          super.onKeyDown(keyCode, event);
              Toast.makeText(getApplicationContext(), " 키 업 이벤트 발생 ( keyCode = " +
                      keyCode , Toast.LENGTH_SHORT).show();
          return true;
     }
     //트랙볼 정의
     @Override
     public boolean onTrackballEvent(MotionEvent event){
          super.onTrackballEvent(event);
     Toast.makeText(getApplicationContext(), " 트랙볼 이벤트 발생 좌표( X = "
                      + event.getX() + ",Y = " + event.getY( ) + ")", Toast.LENGTH_SHORT).show();
          return true;
     }*/
    }
}