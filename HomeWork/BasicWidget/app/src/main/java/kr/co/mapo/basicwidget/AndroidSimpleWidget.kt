package kr.co.mapo.basicwidget

import TextViewDisplay
import com.pyo.android.simple.widget.AndroidEventListActivity

abstract class AndroidSimpleWidget : BaseActivity() {
    override fun displayActivityList(){
        addActionMap("1.TextView", TextViewDisplay::class.java)
        addActionMap("2.Form UI",WidgetFormDisplay::class.java)
        addActionMap("3.Event 처리",AndroidEventListActivity::class.java)
    }
}