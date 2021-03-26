package com.example.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basic.databinding.ActivityLauncherBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //private lateinit var currentBtn: Button
    //private lateinit var displayView: TextView
    private lateinit var binding: ActivityLauncherBinding

    private val formatter = SimpleDateFormat(" 현재 시간은 yyyy-MM-dd  HH:mm:ss 입니다",Locale.KOREA)

    private fun updateTime() {
        binding.displayView.text = formatter.format(Date())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 요즘 방식의 Activity 에서의 뷰바인딩
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.currentBtn.setOnClickListener {
            updateTime()
        }

        /**
         * Activity 에서 안드로이드 전통적인 방식의 UI 인플레이션
         *
        setContentView(R.layout.activity_launcher)
        val currentBtn = findViewById(R.id.currentBtn) as Button
        currentBtn.setOnClickListener {
        updateTime()
        }
        displayView = findViewById(R.id.displayView)
         */
    /////////////////////////////////////////////////////////////////////////
        /**
         * 직접 코드로 간단한 UI를 구현
         *
         *
        val lineContainer = LinearLayout(this)
        var layoutParam = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
        )
        lineContainer.run{
        layoutParams = layoutParam
        orientation = LinearLayout.VERTICAL
        }

        val message = resources.getString(R.string.title_message)
        val tView = TextView(this)
        val tFace = Typeface.create(Typeface.SERIF, Typeface.BOLD)
        with(tView){
        text = message
        textSize = 16f
        typeface = tFace
        gravity = Gravity.CENTER_HORIZONTAL
        }
        currentBtn = Button(this)
        with(currentBtn) {
        width = LinearLayout.LayoutParams.WRAP_CONTENT
        height = LinearLayout.LayoutParams.MATCH_PARENT
        text = """현재시간보기"""
        }
        displayView = TextView(this)
        displayView.apply {
        text = "현재 시간은 여기서 보여짐"
        setTextColor(Color.BLACK)
        typeface = tFace
        gravity = Gravity.CENTER_HORIZONTAL
        }
        //컨테이너 레이아웃에 각 위젯을 부착
        lineContainer.run {
        addView(tView)
        addView(currentBtn)
        addView(displayView)
        }
        //현재 Activity 레이아웃을 연결
        //root(lineContainer)를 연결하여 드로윙을 의뢰
        setContentView(lineContainer)

        currentBtn.setOnClickListener {
        updateTime()
        }*/
    }

}
