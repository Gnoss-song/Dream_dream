package kr.co.pickup.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.pickup.R
import kr.co.pickup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}