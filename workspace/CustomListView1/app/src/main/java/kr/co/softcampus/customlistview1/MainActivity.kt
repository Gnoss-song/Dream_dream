package kr.co.softcampus.customlistview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kr.co.softcampus.customlistview1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    val data1 = arrayOf(
        "문자열1","문자열2","문자열3","문자열4","문자열5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val adater1 = ArrayAdapter(this,R.layout.row,R.id.rowTextView,data1)
        binding.list1.adapter = adater1

        binding.list1.setOnItemClickListener{parent, view,position,id ->
            binding.textView.text = "${data1[position]}을 터치하셨습니다."
        }

    }
}