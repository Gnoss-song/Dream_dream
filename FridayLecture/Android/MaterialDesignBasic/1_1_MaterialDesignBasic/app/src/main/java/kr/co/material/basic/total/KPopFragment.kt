package kr.co.material.basic.total

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.material.basic.R
import kr.co.material.basic.common.GirlGroupApplication
import kr.co.material.basic.common.GirlGroupRandomInit.shuffleGirlsGeneration
import kr.co.material.basic.common.GirlGroupRandomInit.shuffleTwice
import java.util.*

class KPopFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val rv = inflater.inflate(R.layout.fragment_twice,
                container, false) as RecyclerView
        rv.layoutManager = LinearLayoutManager(GirlGroupApplication.getGirlGroupContext())
        rv.addItemDecoration(DividerItemDecoration(
                GirlGroupApplication.getGirlGroupContext(),
                LinearLayoutManager.VERTICAL
        ))
        val kPop  = shuffleTwice()
        with(kPop) {
            addAll(shuffleGirlsGeneration())
            shuffle()
        }
        rv.adapter = activity?.let { GirlGroupFinalRecyclerAdapter(kPop, it, "KPop") }
        return rv
    }

    companion object {

        fun newInstance(): KPopFragment {
            return KPopFragment()
        }
    }
}