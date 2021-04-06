import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import kr.co.softcampus.matcheap_mypage.R
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMainBinding

class MyDialog() : Activity(){
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)}
}