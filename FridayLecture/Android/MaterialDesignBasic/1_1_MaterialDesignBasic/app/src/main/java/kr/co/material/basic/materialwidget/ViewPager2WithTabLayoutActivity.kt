package kr.co.material.basic.materialwidget

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kr.co.material.basic.R
import java.util.*
import kotlin.math.abs

class ViewPager2WithTabLayoutActivity : AppCompatActivity() {
    private lateinit var tab: TabLayout
    private lateinit var viewPager2: ViewPager2
    private var tabItems = listOf("소녀시대", "효연", "제시카", "서현",
            "써니", "수영", "태연", "티파니", "윤아", "유리")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_with_tab_layout)
        tab = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
        with(viewPager2){
            setPageTransformer(FlipPagerTransformer())
        }
        with(generatePagerAdapter()){
            viewPager2.setPageTransformer(FlipPagerTransformer())
            viewPager2.adapter = this
        }
        TabLayoutMediator(tab, viewPager2, TabConfigurationStrategy {
            tab, position -> tab.text = tabItems[position] }
        ).attach()
    }

    private fun generatePagerAdapter(): GirlsFragmentStateAdapter {
        val pagerAdapter = GirlsFragmentStateAdapter(this)
        with(pagerAdapter){
            appendFragment(FragmentForViewPager.newInstance("소녀시대", R.drawable.girls_generation_all))
            appendFragment(FragmentForViewPager.newInstance("효연", R.drawable.girls_generation_hyoyeon))
            appendFragment(FragmentForViewPager.newInstance("제시카", R.drawable.girls_generation_jesica))
            appendFragment(FragmentForViewPager.newInstance("서현", R.drawable.girls_generation_seohyun))
            appendFragment(FragmentForViewPager.newInstance("써니", R.drawable.girls_generation_sunny))
            appendFragment(FragmentForViewPager.newInstance("수영", R.drawable.girls_generation_suyoung))
            appendFragment(FragmentForViewPager.newInstance("태연", R.drawable.girls_generation_taeyeon))
            appendFragment(FragmentForViewPager.newInstance("티파니", R.drawable.girls_generation_tifany))
            appendFragment(FragmentForViewPager.newInstance("윤아", R.drawable.girls_generation_yuna))
            appendFragment(FragmentForViewPager.newInstance("유리", R.drawable.girls_generation_yuri))
        }
        return pagerAdapter
    }

    internal inner class FlipPagerTransformer : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            with(page){
                translationX = -position * width
                cameraDistance = 12000f
                when {
                    position < 0.5 && position > -0.5 -> {
                        View.VISIBLE
                    }
                    else -> {
                        View.INVISIBLE
                    }
                }.also { visibility = it }

                when {
                    position < -1 -> {     // [-Infinity,-1)
                        alpha = 0f
                    }
                    position <= 0 -> {    // [-1,0]
                        alpha = 1f
                        rotationY = 180 * (1 - abs(position) + 1)
                    }
                    position <= 1 -> {    // (0,1]
                        alpha = 1f
                        (-180 * (1 - abs(position) + 1)).also { rotationY = it }
                    }
                    else -> alpha = 0f
                }
            }

        }
    }
}