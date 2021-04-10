package com.basic.recyclerview_1_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: MultiAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)
        mAdapter = MultiAdapter(randomInitData(), this@MainActivity)
        recyclerView.adapter = mAdapter
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }
    private fun randomInitData() : MutableList<MyData>{
        val r = Random(System.currentTimeMillis())
        val myData = mutableListOf<MyData>()
        for (i in 0..50 ){
            val data = MyData()
            data.type = r.nextInt(3) + 1
            data.message = "아이템 $i"
            myData.add(data)
        }
        return myData
    }
}