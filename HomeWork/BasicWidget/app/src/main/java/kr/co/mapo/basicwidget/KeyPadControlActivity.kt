package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.button_kind_layout.*
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import kr.co.mapo.basicwidget.databinding.ImeKeyBoardControlLayoutBinding

abstract class KeyPadControlActivity : Activity() {
    private val binding by lazy { ImeKeyBoardControlLayoutBinding.inflate(layoutInflater) }
    private lateinit var keyPadManager: InputMethodManager
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        //IMF Manager 서비스 객체를 얻는다
        keyPadManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//            val keyPadShow = R.id.key_pad_show as Button
//            val keyPadHide = R.id.key_pad_hide as Button
//            val keyPadControl = R.id.key_pad_control_edit as EditText
            binding.keyPadShow.setOnClickListener{
                keyPadManager.showSoftInput(binding.keyPadControlEdit,InputMethodManager.RESULT_UNCHANGED_SHOWN)
            }
            binding.keyPadHide.setOnClickListener{
                    keyPadManager.hideSoftInputFromWindow(binding.keyPadControlEdit2.windowToken,InputMethodManager.RESULT_UNCHANGED_SHOWN)}
        }

}

