package com.example.firstfragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val fragment = supportFragmentManager.findFragmentById(R.id.first) as Fragment
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if(savedInstanceState == null){
            ft.add(R.id.container1,FirstFragment.newInstance("마포"))
            ft.add(R.id.container2,FirstFragment.newInstance("화이팅"))
            ft.commit()
        }
    }
}

