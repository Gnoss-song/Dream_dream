package com.fragment.backstack

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    private var mStackLevel = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newBtn: Button = findViewById(R.id.newFragment)
        if (savedInstanceState == null) {
            val newFragment: Fragment = CountingFragment.newInstance(mStackLevel)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragmentInFrame, newFragment).commit()
        } else {
            mStackLevel = savedInstanceState.getInt("level")
        }
        newBtn.setOnClickListener {
            addFragmentToStack()
        }
        val removeBtn: Button = findViewById(R.id.deleteFragment)
        removeBtn.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("level", mStackLevel)
    }
    private fun addFragmentToStack() {
        val newFragment: Fragment = CountingFragment.newInstance(++mStackLevel)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentInFrame, newFragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        //Back Stack 에 들어갈 때 이름을 부여할 수 있으나 이름부여는 거의 사용되지 않는다
        ft.addToBackStack(null)
        ft.commit()
    }
    class CountingFragment : Fragment() {
        private var mNum = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mNum = arguments?.getInt("num") ?: 1
        }
        companion object {
            fun newInstance(num: Int): CountingFragment {
                val fragment = CountingFragment()
                val args = Bundle()
                args.putInt("num", num)
                fragment.arguments = args
                return fragment
            }
        }
        override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?, savedInstanceState: Bundle?): View {
            val v: View = inflater.inflate(R.layout.fragment_simple_text, container, false)
            val tv: TextView = v.findViewById(R.id.messageText)
            tv.text = Editable.Factory.getInstance().newEditable("Current Fragment Level $mNum")
            return v
        }
    }
}
