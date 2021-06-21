package kr.co.pickup.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kr.co.pickup.R
import kr.co.pickup.databinding.FragmentDetailBinding
import kr.co.pickup.databinding.FragmentMapBinding

class DetailFragment(val owner: AppCompatActivity) : Fragment() {


    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        owner.setSupportActionBar(binding.toolbar)
        // Inflate the layout for this fragment
        return binding.root
    }
}


