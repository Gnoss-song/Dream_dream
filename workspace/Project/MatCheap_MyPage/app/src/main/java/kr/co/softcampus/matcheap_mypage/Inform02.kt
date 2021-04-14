package kr.co.softcampus.matcheap_mypage

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.softcampus.matcheap_mypage.databinding.Inform02Binding

class Inform02 : AppCompatActivity() {
    private lateinit var binding: Inform02Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Inform02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val imgRes = intent.getIntExtra("marketIV",-1)
        with(binding){
            marketIV.setImageResource(imgRes)
            marketName.text=intent.getStringExtra("marketName")
            marketLocation.text=intent.getStringExtra("marketLocation")
            marketDistance.text=intent.getStringExtra("marketDistance")
            marketRank.text=intent.getStringExtra("marketRank")
            marketClass.text=intent.getStringExtra("marketClass")
        }
    }
}