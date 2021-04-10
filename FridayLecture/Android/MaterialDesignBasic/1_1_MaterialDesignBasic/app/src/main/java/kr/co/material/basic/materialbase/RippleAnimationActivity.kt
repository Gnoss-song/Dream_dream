package kr.co.material.basic.materialbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kr.co.material.basic.R
import kr.co.material.basic.databinding.ActivityRippleAnimationBinding

class RippleAnimationActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityRippleAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_ripple_animation)
        binding = ActivityRippleAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.run {
                add(R.id.container, FragmentOne.newInstance())
                commit()
            }
        }
        val btn2 = findViewById<Button>(R.id.button2)
        btn2.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            with(ft){
                replace(R.id.container, FragmentOne.newInstance())
                commit()
            }
        }
        val btn1 = findViewById<Button>(R.id.button1)
        btn1.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            ft.run{
                replace(R.id.container, FragmentTwo.newInstance())
                commit()
            }
        }
        val btn3 = findViewById<Button>(R.id.button3)
        btn3.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            with(ft){
                replace(R.id.container, FragmentThree.newInstance())
                commit()
            }
        }
    }

    class FragmentOne : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.ripple_fragment_one, container, false)
        }
        companion object {
            fun newInstance(): Fragment {
                return FragmentOne()
            }
        }
    }

    class FragmentTwo : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.ripple_fragment_two, container, false)
        }

        companion object {
            fun newInstance(): Fragment {
                return FragmentTwo()
            }
        }
    }

    class FragmentThree : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.ripple_fragment_three, container, false)
        }
        companion object {
            fun newInstance(): Fragment {
                return FragmentThree()
            }
        }
    }
}