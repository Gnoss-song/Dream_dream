package com.example.mapo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class TabLayoutBasicActivity : AppCompatActivity() {
    private lateinit var badge: BadgeDrawable
    private var badgeCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_basic)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        badge = tabLayout.getTabAt(0)!!.orCreateBadge

        badge.isVisible = true
        badge.number = badgeCount
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val param = when (tab.position) {
                    0 -> {
                        badge.number = ++badgeCount
                        "상품검색"
                    }
                    1 -> "카메라"
                    2 -> "이용안내"
                    else -> throw IllegalStateException("Unexpected value: " + tab.position)
                }
                val ft = supportFragmentManager.beginTransaction()
                with(ft){
                    replace(R.id.tabContent, FragmentContent.newInstance(param))
                    commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    class FragmentContent : Fragment() {
        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
            val root = inflater.inflate(R.layout.bottom_fragment, container, false)
            val titleTV = root.findViewById<TextView>(R.id.contentTitle)
            val paramValue = arguments?.getString("title")
            titleTV.text = paramValue
            return root
        }

        companion object {
            fun newInstance(param: String): Fragment {
                val fragment: Fragment = FragmentContent()
                val bundle = Bundle()
                bundle.putString("title", param)
                fragment.arguments = bundle
                return fragment
            }
        }
    }
}