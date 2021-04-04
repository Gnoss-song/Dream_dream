package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import kr.co.mapo.basicwidget.databinding.ImeKeyBoardLayoutBinding

class KeyPadInputTypeActivity : Activity() {
    private val binding by lazy { ImeKeyBoardLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}