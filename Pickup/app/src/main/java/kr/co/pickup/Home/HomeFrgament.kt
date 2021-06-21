package kr.co.pickup.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.pickup.Adapter.RegionAdapter
import kr.co.pickup.Model.Region
import kr.co.pickup.R
import kr.co.pickup.databinding.FragmentHomeFrgamentBinding

class HomeFrgament(val owner: AppCompatActivity) : Fragment() {


    private lateinit var binding :FragmentHomeFrgamentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFrgamentBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val list = mutableListOf(
            Region("서울"),
            Region("경기"),
            Region("인천"),
            Region("대전"),
            Region("대구"),
            Region("충북"),
            Region("충남"),
            Region("부산"),
            Region("경북"),
            Region("울산"),
            Region("광주"),
            Region("강원"),
            Region("전북")
        )

        val adpater = RegionAdapter(list ,owner)
        binding.regionRecyclerview.layoutManager = LinearLayoutManager(owner,LinearLayoutManager.HORIZONTAL,false)
        binding.regionRecyclerview.adapter = adpater
        return binding.root
    }



}