package com.fragment.replace

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    lateinit var firstFragment: FirstFragment
    lateinit var secondFragment: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        val btnFirst: Button = findViewById(R.id.btnFirst)
        btnFirst.setOnClickListener{
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container, firstFragment)
                ft.commit()
            }
        val btnSecond: Button = findViewById(R.id.btnSecond)
        btnSecond.setOnClickListener{

                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.container, secondFragment)
                ft.commit()
            }
    }
}
