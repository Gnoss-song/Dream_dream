package kr.co.softcampus.startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val secondIntent = Intent(this, SecondActivity::class.java)
        setContentView(binding.root)
        binding.button.setOnClickListener{startActivity(secondIntent)}
        binding.button2.setOnClickListener{startActivity(secondIntent)}

    }
}