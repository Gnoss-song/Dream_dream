package kr.co.softcampus.textinputlayout
classpath "org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version"

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        .setOnClickListener {it : View!
        button1.text
            textInputLayout1.editText?.clearFocus()
            // Focus없애는 문장//
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm.hideSoftInputFromWindow((textInputLayout1.editText?.windowToken)
            // 키보드 내려가기.
        }
    }
}