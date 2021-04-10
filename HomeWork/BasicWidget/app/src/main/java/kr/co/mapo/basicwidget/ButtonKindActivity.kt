package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.ToggleButton
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import kr.co.mapo.basicwidget.databinding.ButtonKindLayoutBinding
import kr.co.mapo.basicwidget.ButtonKindActivity as con

class ButtonKindActivity : Activity() {
    private val binding by lazy { ButtonKindLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val basicButton: Button = findViewById(R.id.basic_button)
        basicButton.setOnClickListener {
            Toast.makeText(
                applicationContext, "버튼 클릭됨! ",
                Toast.LENGTH_SHORT
            ).show()
        }
        val selectorButton: Button = findViewById(R.id.selector_applied_btn)
        val imageButton: ImageButton = findViewById(R.id.image_button)
        imageButton.setOnClickListener { selectorButton.isEnabled }
        if (!selectorButton.isEnabled) {
            selectorButton.isEnabled
            Toast.makeText(
                this,
                "처음으로 버튼이 활성화 됩니다.",
                Toast.LENGTH_SHORT
            ).show()
        } else
            Toast.makeText(
                this,
                " 이미지 버튼입니다.",
                Toast.LENGTH_SHORT
            ).show()
        val toggleButton : ToggleButton = findViewById(R.id.toggle_button)
        toggleButton.setOnClickListener{toggleButton.isChecked}
        }
}

