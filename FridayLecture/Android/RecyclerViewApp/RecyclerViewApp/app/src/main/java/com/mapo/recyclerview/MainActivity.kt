package com.mapo.recyclerview

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mapo.recyclerview.databinding.ActivityMainBinding
import java.net.URI.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        with(binding.twiceRV){
            layoutManager = manager
            addItemDecoration(
                DividerItemDecoration(context,
                    LinearLayoutManager.VERTICAL))
            adapter = TwiceRecyclerViewAdapter(twiceData(), this@MainActivity)
        }
        val girlImage = findViewById<ImageView>(R.id.memberIV)

        girlImage.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this,girlImage,"hero")
            startActivity(intent,options.toBundle())
        }
    }
    private fun twiceData() : MutableList<Twice>{
        val twiceData = mutableListOf<Twice>()
        twiceData.add(Twice(R.drawable.twice_all, "트와이스멤버들"))
        twiceData.add(Twice(R.drawable.twice_sana, "사나"))
        twiceData.add(Twice(R.drawable.twice_chaeyeong, "채영"))
        twiceData.add(Twice(R.drawable.twice_dahyeon, "다연"))
        twiceData.add(Twice(R.drawable.twice_jihyo, "지효"))
        twiceData.add(Twice(R.drawable.twice_jungyeon, "정연"))
        twiceData.add(Twice(R.drawable.twice_mina, "미나"))
        twiceData.add(Twice(R.drawable.twice_momo, "모모"))
        twiceData.add(Twice(R.drawable.twice_nayeon, "나연"))
        twiceData.add(Twice(R.drawable.twice_tzuyu, "쯔유"))
        return twiceData
    }




}
