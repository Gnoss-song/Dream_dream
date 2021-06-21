package kr.co.pickup.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.pickup.Detail.DetailFragment
import kr.co.pickup.Map.MapFragment
import kr.co.pickup.Mypage.MypageFragment
import kr.co.pickup.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFrgament(this)
        val mapFragment = MapFragment(this)
        val mypageFragment = MypageFragment(this)

        replaceFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.map -> replaceFragment(mapFragment)
                R.id.mypage -> replaceFragment(mypageFragment)
            }
            true
        }
    }

        private fun replaceFragment(fragment: Fragment){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer,fragment)
                commit()
            }
        }
}