package com.mapo.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mapo.recyclerview.databinding.ActivityTwiceDetailBinding

class TwiceDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTwiceDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwiceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgRes = intent.getIntExtra("twicePick",-1)
        with(binding){
            memberName.text = intent.getStringExtra("twiceName")
            memberIV.setImageResource(imgRes)
        }


    }


}