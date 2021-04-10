package com.example.practice22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice22.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL,false)
        with(binding.twiceRV){
            layoutManager=manager
            addItemDecoration(
                DividerItemDecoration(context,
                LinearLayout.VERTICAL)
            )
            adapter = TwiceRecyclerViewAdapter(twiceData(),this@MainActivity)



        }

        val intent = Intent(this,TwiceDetailActivity::class.java)


        }


        }


    }
    private fun twiceData() : MutableList<Twice>{
        val twiceData = mutableListOf<Twice>()
        twiceData.add(Twice(R.drawable.twice_all, "트와이스멤버"))
        twiceData.add(Twice(R.drawable.twice_sana, "사나"))
        twiceData.add(Twice(R.drawable.twice_chaeyeong, "채영"))
        twiceData.add(Twice(R.drawable.twice_dahyeon, "다현"))
        twiceData.add(Twice(R.drawable.twice_jihyo, "지효"))
        twiceData.add(Twice(R.drawable.twice_jungyeon, "정연"))
        twiceData.add(Twice(R.drawable.twice_mina, "미나"))
        twiceData.add(Twice(R.drawable.twice_momo, "모모"))
        twiceData.add(Twice(R.drawable.twice_nayeon, "나연"))
        twiceData.add(Twice(R.drawable.twice_tzuyu, "쯔위"))
        return twiceData
    }


