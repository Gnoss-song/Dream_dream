package kr.co.softcampus.button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //버튼에 리스너를 설정한다.
        button1.setOnClickListener(listener1)
    }
    // 버튼을 클릭하면 동작하는 리스너 객체

    val listener1 = object : View.OnClickListener{
        override fun onClick(v: View?) {
            text1.text = "첫 번째 버튼을 눌렀습니다."
        }
    }
}