package com.example.practice03.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice03.R
import com.example.practice03.databinding.FragmentDetailViewBinding

class GridFragment : Fragment() {

    private lateinit var binding : FragmentDetailViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_grid,container,false)
        return view
    }
}