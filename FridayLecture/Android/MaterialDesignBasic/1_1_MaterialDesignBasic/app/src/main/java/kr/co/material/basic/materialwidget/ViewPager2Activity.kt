package kr.co.material.basic.materialwidget

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kr.co.material.basic.R
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class ViewPager2Activity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        viewPager = findViewById(R.id.viewPager)
        val members = ArrayList<GirlsMember>()

        with(members){
            add(GirlsMember("소녀시대", R.drawable.girls_generation_all))
            add(GirlsMember("효연", R.drawable.girls_generation_hyoyeon))
            add(GirlsMember("제시카", R.drawable.girls_generation_jesica))
            add(GirlsMember("서현", R.drawable.girls_generation_seohyun))
            add(GirlsMember("써니", R.drawable.girls_generation_sunny))
            add(GirlsMember("수영", R.drawable.girls_generation_suyoung))
            add(GirlsMember("태연", R.drawable.girls_generation_taeyeon))
            add(GirlsMember("티파니", R.drawable.girls_generation_tifany))
            add(GirlsMember("윤아", R.drawable.girls_generation_yuna))
            add(GirlsMember("유리", R.drawable.girls_generation_yuri))
        }
        with(viewPager){
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            //setPageTransformer(MultiPageTransformer())
            setPageTransformer(GirlPageTransformer())
            adapter = ViewPagerAdapter(members)
        }
    }

    internal inner class GirlPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPos = abs(position)
            with(page){
                translationX = absPos * 500f
                translationY = absPos * 500f
                scaleX = 1f //현재 사이즈 그대로
                scaleY = 1f
            }
            when {
                position < -1 -> {
                    page.alpha = 0.1f
                }
                position <= 1 -> {
                    page.alpha = max(0.2f, 1 - absPos)
                }
                else -> {
                    page.alpha = 0.1f
                }
            }
        }
    }

    internal inner class MultiPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val pageMarginPX = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offSetPX = resources.getDimensionPixelOffset(R.dimen.offset)
            val parentPager = page.parent.parent as ViewPager2
            val offSet = position * -(2 * offSetPX + pageMarginPX)
            if (parentPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(parentPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offSet
                } else {
                    page.translationX = offSet
                }
            } else {
                page.translationY = offSet
            }
        }
    }
}