package com.fragment.replace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class SecondFragment : Fragment() {
    companion object {
        fun newInstance(message:String):Fragment {
            val fragment2 = SecondFragment()
            val bundle = Bundle()
            val fragment = ()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceSate: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_second,
            container, false
        )
    }

}
