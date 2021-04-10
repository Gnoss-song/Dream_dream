package com.fragment.replace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FirstFragment : Fragment() {

    companion object{
        fun newInstance(message:String):Fragment{
            val fragment = FirstFragment()
            val bundle = Bundle()
            bundle.putString("message",message)
            fragment.arguments = bundle
            return fragment

        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceSate: Bundle?
    ): View? {
        val message = arguments?.getString("message")
        return inflater.inflate(R.layout.fragment_first,container, false)
    }
}
