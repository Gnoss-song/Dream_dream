package kr.co.softcampus.playground2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.playground2.databinding.ActivityMainBinding
import kr.co.softcampus.playground2.models.Contact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Data

        val dataList = ArrayList<Contact>()
        for(i in 0..99){
            dataList.add(Contact("아무개"+(i+1)+"호"))
        }


        //Adapter
        val adapter : ContactRecyclerAdapter = ContactRecyclerAdapter()
        //View
        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter



        //나중에 아이템 갱신
        adapter.setItems(dataList)
        adapter.notifyDataSetChanged()


        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MovieMain::class.java)
            startActivity(intent)
        })
    }



}