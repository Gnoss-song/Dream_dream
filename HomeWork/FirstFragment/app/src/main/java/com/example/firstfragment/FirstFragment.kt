package com.example.firstfragment

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment :Fragment(){
    companion object {
        fun newInstance() : Fragment {
            val fragment = FirstFragment()
            return fragment
        }
        fun newInstance(message: String) : Fragment {
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
            savedInstanceState: Bundle?): View?
    {
        val fragmentRootView = inflater.inflate(R.layout.firstfragment, container, false)
        val btn :Button = fragmentRootView.findViewById<Button>(R.id.btnSend)
        val inputTitle = fragmentRootView.findViewById<EditText>(R.id.inputTitle)
        val showTitle = fragmentRootView.findViewById<TextView>(R.id.showTitle)
        val message = arguments?.getString("message")
        showTitle.text=message
        btn.setOnClickListener {
            showTitle.text = inputTitle.text.toString()
            inputTitle.text = null
        }

        return fragmentRootView
    }
}