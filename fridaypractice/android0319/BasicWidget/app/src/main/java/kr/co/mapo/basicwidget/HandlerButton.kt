package kr.co.mapo.basicwidget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast

class HandlerButton : Button {
    private val context : Context

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.context = context
    }

    /*
	 * 2번째 우선 순위
	 */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        if (Toast.action == MotionEvent.ACTION_DOWN) {
            Toast.makeText(context, "버튼 구현 콜백 이벤트 발생!", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}