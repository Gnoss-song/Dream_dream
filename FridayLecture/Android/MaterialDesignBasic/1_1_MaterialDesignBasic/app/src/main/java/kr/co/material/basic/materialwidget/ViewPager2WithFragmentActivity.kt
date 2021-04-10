package kr.co.material.basic.materialwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kr.co.material.basic.R

class ViewPager2WithFragmentActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_with_fragment)
        viewPager = findViewById(R.id.pager)
        val pagerAdapter = GirlsFragmentStateAdapter(this)
        pagerAdapter.run{
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

            viewPager.adapter = pagerAdapter
        }
    }
}