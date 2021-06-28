package kr.co.pickup.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kr.co.gooroomeelite.utils.LoginUtils.Companion.isLogin
import kr.co.gooroomeelite.utils.RC_SIGN_IN
import kr.co.pickup.Home.MainActivity
import kr.co.pickup.R
import kr.co.pickup.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.postDelayed({
            startProcess()
        }, 1000L)
    }

    private fun startProcess() {
        if(isLogin()) {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            signIn()
        }
    }

    private fun signIn() {
        startActivityForResult(
            Intent(this, LoginActivity::class.java),
            RC_SIGN_IN
        )
        finish()
    }

}