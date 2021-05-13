package com.pyo.lbs.map.friend

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pyo.lbs.map.common.LBSMapApplication
import com.pyo.lbs.map.databinding.ActivityMainFriendBinding

class FindFriendMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main_friend)
        binding = ActivityMainFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.locationRegBtn.setOnClickListener{
            val target = Intent(LBSMapApplication.applicationContext(), LocationRegistrationActivity::class.java)
            startActivity(target)
        }
        binding.friendListBtn.setOnClickListener {
            val intent = Intent(LBSMapApplication.applicationContext(),FriendListActivity::class.java)
            startActivity(intent)
        }
        binding.friendListBtn.isEnabled = false
    }
    override fun onRestart() {
        super.onRestart()
        binding.friendListBtn.isEnabled = true
    }
}