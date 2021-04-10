package com.example.practice22

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practice22.databinding.ActivityMainBinding
import com.example.practice22.databinding.TwiceDetailActivityBinding

class TwiceDetailActivity : AppCompatActivity(){
    private val binding by lazy { TwiceDetailActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}