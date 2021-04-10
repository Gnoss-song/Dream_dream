package kr.co.material.basic.materialwidget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.material.basic.R

class BottomNavigationViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_view)

        if (savedInstanceState == null) {
            with(supportFragmentManager.beginTransaction()){
                add(R.id.fragmentContainer, BottomFragment.newInstance("상품검색"))
                commit()
            }
        }
        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navigationView.setOnNavigationItemSelectedListener { item ->

            var param = when (item.itemId) {
                R.id.searchItem -> "상품검색"
                R.id.cameraItem -> "카메라"
                R.id.callItem -> "이용안내"
                else -> throw IllegalStateException("Unexpected value: " + item.itemId)
            }
            val ft = supportFragmentManager.beginTransaction()
            with(ft){
                replace(R.id.fragmentContainer, BottomFragment.newInstance(param))
                commit()
            }
            true
        }
    }

    class BottomFragment : Fragment() {
        companion object {
            fun newInstance(param: String): Fragment {
                val fragment: Fragment = BottomFragment()
                val bundle = Bundle()
                bundle.putString("title", param)
                fragment.arguments = bundle
                return fragment
            }
        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val root = inflater.inflate(R.layout.bottom_fragment, container, false)
            val titleTV = root.findViewById<TextView>(R.id.contentTitle)
            val paramValue = arguments?.getString("title")
            titleTV.text = paramValue
            return root
        }
    }
}