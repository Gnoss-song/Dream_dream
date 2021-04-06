package kr.co.softcampus.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kr.co.softcampus.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val data1 = arrayOf(
            "문자열1","문자열2","문자열3","문자열4","문자열5",
            "문자열6","문자열7","문자열8","문자열9","문자열10",
            "문자열11","문자열12","문자열13","문자열14","문자열15",
            "문자열16","문자열17","문자열18","문자열19","문자열20",
            "문자열21","문자열22","문자열23","문자열24","문자열25",
            "문자열26","문자열27","문자열28","문자열29","문자열30")

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter1 = ArrayAdapter(this,android.R.layout.simple_list_item_1,data1)
        binding.list1.adapter = adapter1

        binding.list1.onItemClickListener = listener1
    }
    val listener1 = object : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
            when(parent?.id){
                R.id.list1 ->{
                    binding.textView.text = "${data1[position]}를 클릭했습니다."
                }
            }
        }
    }
}