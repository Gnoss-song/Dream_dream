package com.thread.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FirstFragment : Fragment() {
    companion object{
        fun newInstance() = FirstFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentRootView = inflater.inflate(R.layout.fragment_first,container,false)
        val btn:Button = fragmentRootView.findViewById(R.id.btnSend)
        val inputTitle = fragmentRootView.findViewById<EditText>(R.id.inputTitle)
        val showTitle = fragmentRootView.findViewById<TextView>(R.id.showTitle)
        btn.setOnClickListener {
            showTitle.text = inputTitle.text.toString()
            inputTitle.text = null
        }

        return fragmentRootView
    }


}