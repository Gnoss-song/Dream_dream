package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var imgRes = intArrayOf(R.drawable.imgflag1,R.drawable.imgflag2,R.drawable.imgflag3,R.drawable.imgflag4,R.drawable.imgflag5,R.drawable.imgflag6,R.drawable.imgflag7,R.drawable.imgflag8)
    var data1 = arrayOf("토고","프랑스","스위스","스페인","일본","독일","브라질","대한민국")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter1 = RecyclerAdapter()
        binding.recycler1.adapter = adapter1

        binding.recycler1.layoutManager = LinearLayoutManager(this)
        //binding.recycler1.layoutManager = GridLayoutManager(this,2)
//        binding.recycler1.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        //binding.recycler1.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)

    }


    //RecyclerView의 Adapter클래스
    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){


        // 항목 구성을 위해 사용할 ViewHolder 객체가 필요할 때 호출되는 메서드.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            //항목으로 사용할 View 객체를 생성한다.
            val itemView = layoutInflater.inflate(R.layout.row,null)
            val holder = ViewHolderClass(itemView)
            itemView.setOnClickListener(holder)

            // onCreateViewHolder를 통해 itemView객체를 생성한다.
            // 생성된itemView는 ViewHolderClass의 주생성자로 넘어간다.
            // rowImageView와 rowTextView가 row.xml의 item들과 연결된다.

            return holder
        }

        //ViewHolder를 통해 항목을 구성할 때 항목 내의 View 객체에 데이터를 셋팅한다.
        //holder는 ViewHolderClass(itemView). 뷰이다. 이 뷰에는 rowImgaeView와 rowTextView가 저장되어있다.
        //이것을 onBindViewHolder를 통해서 세팅한다. 그리고 세팅하는 위치는 position으로 index처리를 통해 어디에 들어갈지 정한다.

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            holder.rowImageView.setImageResource(imgRes[position])
            holder.rowTextView.text= data1[position]

        }

        override fun getItemCount(): Int {
            return imgRes.size
        }

        
        //ViewHolder클래스를 작성
        inner class ViewHolderClass(itemView:View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
            //항목 View 내부의 View 객체의 주소값을 담는다.
            val rowImageView: ImageView = itemView.findViewById(R.id.rowImageView)
            val rowTextView : TextView = itemView.findViewById(R.id.rowTextView)
            //row.xml 에 우리가 설정한 아이디들. 아이템들의 이름. 변수가 자동으로 추가가 되어서 만듦


            //onClick이벤트 처리.Holder내부에 설정한다.
            override fun onClick(v: View?) {
                binding.textView.text = data1[adapterPosition]
            }
        }

    }
}