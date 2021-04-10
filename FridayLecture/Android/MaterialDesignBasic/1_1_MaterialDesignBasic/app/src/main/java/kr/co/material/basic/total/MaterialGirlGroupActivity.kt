package kr.co.material.basic.total

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.material.basic.R
import kr.co.material.basic.materialwidget.GirlsFragmentStateAdapter
import kr.co.material.basic.total.KPopFragment.Companion.newInstance

class MaterialGirlGroupActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    var toolbar: Toolbar? = null
    var mDrawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null
    lateinit var girlViewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    var tabItems = mutableListOf("트와이스", "소녀시대", "K-POP")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_final_girls_group)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.title = "K-Pop Girl Group"
        girlViewPager2 = findViewById(R.id.girlViewPager2)
        tabLayout = findViewById(R.id.tabs)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer)
        mDrawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout?.closeDrawers()
            true
        }
        girlViewPager2.adapter = generatePagerAdapter()

        TabLayoutMediator(tabLayout,girlViewPager2){tab,position->
            tab.text = tabItems[position]
        }.attach()
    }

    private fun generatePagerAdapter(): GirlsFragmentStateAdapter {
        val pagerAdapter = GirlsFragmentStateAdapter(this)
        with(pagerAdapter){
            appendFragment(TwiceFragment.newInstance())
            appendFragment(GirlGenerationFragment.newInstance())
            appendFragment(newInstance())
        }
        return pagerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            mDrawerLayout?.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private val delayTime = 1500L
    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) mDrawerLayout!!.closeDrawer(GravityCompat.START)
        val currentTime = System.currentTimeMillis()
        val intervalTime = currentTime - backPressedTime

        if (intervalTime in 0..delayTime) {
            super.onBackPressed()
        } else {
            backPressedTime = currentTime
            Toast.makeText(applicationContext,
                    "뒤로 버튼 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }
}