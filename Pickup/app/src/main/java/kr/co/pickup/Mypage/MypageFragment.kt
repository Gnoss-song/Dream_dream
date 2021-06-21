package kr.co.pickup.Mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kr.co.pickup.R
import kr.co.pickup.databinding.FragmentMapBinding
import kr.co.pickup.databinding.FragmentMypageBinding

class MypageFragment(val owner: AppCompatActivity) : Fragment() {


    private lateinit var binding : FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }
}
