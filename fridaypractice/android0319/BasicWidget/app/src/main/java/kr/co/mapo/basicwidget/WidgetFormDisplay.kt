package kr.co.mapo.basicwidget

import com.pyo.android.simple.widget.AutoCompleteTextViewActivity

class WidgetFormDisplay : BaseActivity() {
    fun displayActivityList(){
        addActionMap("2.1.입력폼", TextInputActivity::class.java)
        addActionMap("2.2.키패드입력", KeyPadInputTypeActivity::class.java)
        addActionMap("2.3.키패드제어", KeyPadControlActivity::class.java)
        addActionMap("2.4.자동완성기능", AutoCompleteTextViewActivity::class.java)
        addActionMap("2.5.버튼류샘플", ButtonKindActivity::class.java)
        addActionMap("2.6.날짜입력/출력", DateTimePicker::class.java)
    }
}