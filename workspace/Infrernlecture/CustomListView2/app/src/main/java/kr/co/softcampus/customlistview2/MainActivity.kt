package kr.co.softcampus.customlistview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kr.co.softcampus.customlistview2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val imgRes = intArrayOf(
            R.drawable.imgflag1,R.drawable.imgflag2,R.drawable.imgflag3,R.drawable.imgflag4,
            R.drawable.imgflag5,R.drawable.imgflag6,R.drawable.imgflag7,R.drawable.imgflag8)


    val data1 = arrayOf(
            "토고","프랑스","스위스","스페인","일본","독일","브라질","대한민국")
    val data2 = arrayOf(
            "togo","france","swiss","spain","japan","germany","brazil","korea"
    )
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //simple adapter에 셋팅할 데이터를 가지고 있을 ArrayList

        val dataList = ArrayList<HashMap<String,Any>>()

        //전체 ArrayList에는 해쉬맵을 가지고 있다. 첫번째 항목을 구성하기 위해서 ArrayList의 첫번째 해쉬맵을 가지고오고
        //해쉬맵안의 데이터를 추출해서 항목에 셋팅을 한다. 해쉬맵 객체 하나 = 항목 하나를 구성하기 위한 데이터

        for(i in imgRes.indices){
            //imgRes.indices = 0~7까지 들어있는리스트 생성.
            //항목 하나를 구성하기 위해 필요한 데이터를 담고 있는 HashMap
            val map = HashMap<String,Any>()
            map["img"] = imgRes[i]
            map["data1"] = data1[i]
            map["data2"] = data2[i]
            //map이라는 Hashmap을 만듦. map의 구성은 imgRes,data1,data2로 이루어짐
            dataList.add(map)
        }

        //HashMap에 데이터를 저장했을 때 사용했던 이름 배열

        val keys = arrayOf("img","data1","data2")
        //데이터를 셋팅할 View의 id 배열 1:1대응 시켜줌.
        val ids = intArrayOf(R.id.rowImageView,R.id.rowTextView1,R.id.rowTextView2)

        val adapter1 = SimpleAdapter(this,dataList,R.layout.row,keys,ids)
        binding.list1.adapter = adapter1

        binding.list1.setOnItemClickListener{parent,view,position,id ->
            binding.textView.text = "${data1[position]}항목을 터치하였습니다."
        }
    }
}