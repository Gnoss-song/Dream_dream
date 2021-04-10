import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.util.Linkify
import kr.co.mapo.basicwidget.R
import kr.co.mapo.basicwidget.databinding.TextviewLayoutBinding

class TextViewDisplay : Activity() {
    private val binding by lazy { TextviewLayoutBinding.inflate(layoutInflater) }
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //코드로 링키파이를 적용하는 방법
        binding.linkifyText.setText(R.string.autolink_text)
        binding.linkifyText.setTextColor(Color.WHITE)
        binding.linkifyText.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD or Typeface.ITALIC)
        val linkedTextColor = Color.argb(255, 0, 255, 255)
        binding.linkifyText.setLinkTextColor(linkedTextColor)
        binding.linkifyText.textSize = 20f
        Linkify.addLinks(binding.linkifyText, Linkify.ALL)
    }
}