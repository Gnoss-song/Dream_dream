
package kr.co.mapo.basicwidget

import android.app.Activity
import android.os.Bundle
import android.text.InputFilter.AllCaps
import android.text.InputFilter.LengthFilter
import kr.co.mapo.basicwidget.databinding.TextInputLayoutBinding

class TextInputActivity : Activity() {
    private val binding by lazy { TextInputLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.inputFiltered.filters = arrayOf(AllCaps(),
                LengthFilter(2)
        )
    }
}