package kr.co.softcampus.matcheap_mypage

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.softcampus.matcheap_mypage.databinding.Inform02Binding

class Inform02 : AppCompatActivity() {
    private val binding by lazy { Inform02Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)
    }
}