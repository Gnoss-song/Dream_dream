package kr.co.softcampus.matcheap_mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMy0103Binding

class My0103 : AppCompatActivity() {
    private val binding by lazy {ActivityMy0103Binding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my0103)
    }
}