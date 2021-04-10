package kr.co.softcampus.matcheap_mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMy0102Binding

class My0102 : AppCompatActivity() {
    private val binding by lazy { ActivityMy0102Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my0102)
    }
}