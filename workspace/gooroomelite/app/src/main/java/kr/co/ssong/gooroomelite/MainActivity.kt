package kr.co.ssong.gooroomelite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.ssong.gooroomelite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomsheet.setOnClickListener {
            val bottomsheet = BottomSheet(this)
            bottomsheet.show(supportFragmentManager,bottomsheet.tag)

        }
    }
}