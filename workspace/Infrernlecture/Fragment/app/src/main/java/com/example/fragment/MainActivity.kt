package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frag1 = FirstFragment()
        val frag2 = SecondFragment()

        binding.button.setOnClickListener{
            //Fragment 작업 시작
            val tran = supportFragmentManager.beginTransaction()
            //Fragment를 셋팅한다.내가 프래그먼트를 붙이려는 레이아웃의 아이디와 프래그먼트를 기입
            tran.add(R.id.container1,frag1)

            tran.commit()



        }

        binding.button2.setOnClickListener{

            val tran2 = supportFragmentManager.beginTransaction()
            tran2.replace(R.id.container1,frag2)
            tran2.commit()
        }
    }
}