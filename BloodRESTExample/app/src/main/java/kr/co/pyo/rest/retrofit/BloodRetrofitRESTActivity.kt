package kr.co.pyo.rest.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.pyo.rest.databinding.ActivityBloodRestBinding

class BloodRetrofitRESTActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBloodRestBinding

    private var tabItems = listOf("헌혈요청","헌혈요청현황")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_blood_rest)
        binding = ActivityBloodRestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bloodAdapter = BloodFragmentAdapter(this)
        with(bloodAdapter){
            appendFragment(BloodRetrofitInsertFragment.newInstance())
            appendFragment(BloodRetrofitSelectFragment.newInstance())
            binding.viewPager2.adapter = this
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = tabItems[position]
            }
        ).attach()
    }
}