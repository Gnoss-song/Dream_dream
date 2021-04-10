package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import kr.co.mapo.basicwidget.databinding.GestureEventLayoutBinding

class GestureTouchEventActivity : Activity(){
    private val binding by lazy { GestureEventLayoutBinding.inflate(layoutInflater) }
    private val mSaveText : String ?= null
    private val mGestures : GestureDetector ?= null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(mGestures){}
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}