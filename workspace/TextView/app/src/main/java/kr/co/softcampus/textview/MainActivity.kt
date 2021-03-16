package kr.co.softcampus.textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //뷰의 주소 값을 얻어온다.
        val text1 = findViewById<TextView>(R.id.text1)

        text1.text = "안녕하세요"

        text34.text = "반갑습니다."
    }
}