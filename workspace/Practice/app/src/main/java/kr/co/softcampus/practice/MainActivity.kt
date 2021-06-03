package kr.co.softcampus.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.practice.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frag = CalendarFragment()

        binding.button.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container,frag)
                .addToBackStack(null)
                .commit()
        }
    }
}