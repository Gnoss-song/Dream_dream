package com.example.recyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var imgRes = intArrayOf(R.drawable.imgflag1,R.drawable.imgflag2,R.drawable.imgflag3,R.drawable.imgflag4,R.drawable.imgflag5,R.drawable.imgflag6,R.drawable.imgflag7,R.drawable.imgflag8)
    var data1 = arrayOf("토고","프랑스","스위스","스페인","일본","독일","브라질","대한민국")
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter1 = RecyclerAdapter()
        binding.recycler1.adapter = adapter1

        binding.recycler1.layoutManager = LinearLayoutManager(this)
    }

    inner class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val itemView = layoutInflater.inflate(R.layout.rowrecycler,null)
            val holder = ViewHolderClass(itemView)

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowIV.setImageResource(imgRes[position])
            holder.rowTV.text = data1[position]

        }

        override fun getItemCount(): Int {
            return imgRes.size
        }
        inner class ViewHolderClass(itemView:View) : RecyclerView.ViewHolder(itemView){
            val rowIV : ImageView = findViewById(R.id.rowIV)
            val rowTV : TextView = findViewById(R.id.rowTV)

        }

    }
}