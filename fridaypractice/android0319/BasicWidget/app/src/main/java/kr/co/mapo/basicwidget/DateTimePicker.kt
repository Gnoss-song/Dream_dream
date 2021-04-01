package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat

class DateTimePicker : Activity(){
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val dateFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val textView :TextView = findViewById(R.id.text_display_current_datetime)
        val datePicker : DatePicker = findViewById(R.id.date_picker_widget)
        val timePicker : TimePicker = findViewById(R.id.time_picker_widget)

        fun Date(i: Int, monthOfYear: Int, dayOfMonth: Int, currentHour: Int, currentMinute: Int) {
        datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) {
            _, year, monthOfYear, dayOfMonth ->
            val date = Date(year - 1900, monthOfYear, dayOfMonth,timePicker.currentHour, timePicker.currentMinute)
            textView.text = dateFormat.format(date)
        }
    }
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val date = Date(datePicker.year, datePicker.month,
                    datePicker.dayOfMonth, hourOfDay, minute)
            textView.text = dateFormat.format(date)
        }
    }
}