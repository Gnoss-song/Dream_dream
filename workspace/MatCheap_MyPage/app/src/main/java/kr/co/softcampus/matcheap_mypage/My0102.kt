package kr.co.softcampus.matcheap_mypage

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.softcampus.matcheap_mypage.databinding.My0102Binding

class My0102 : AppCompatActivity(){
    private val binding by lazy { My0102Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)


    }
}