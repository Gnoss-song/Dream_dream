package kr.co.pyo.rest.okhttp_retrofit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BloodFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragments = arrayListOf<Fragment>()

    fun appendFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}