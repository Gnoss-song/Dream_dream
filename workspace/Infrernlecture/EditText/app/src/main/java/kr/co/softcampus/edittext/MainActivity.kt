package kr.co.softcampus.edittext

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethod

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener{ it:View!
            textView1.text = editText1.text

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodService
            // getSystemService : 안드로이드 OS의 기능을 가져오겠다.
        }
    }
}