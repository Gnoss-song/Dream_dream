package kr.co.softcampus.matcheap_mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMy0104Binding

class My0104 : AppCompatActivity() {
    private val binding by lazy {ActivityMy0104Binding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my0104)
    }
}