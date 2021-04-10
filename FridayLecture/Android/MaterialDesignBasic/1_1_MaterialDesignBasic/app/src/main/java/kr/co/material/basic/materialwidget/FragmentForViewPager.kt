package kr.co.material.basic.materialwidget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kr.co.material.basic.R

class FragmentForViewPager : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.viewpager_item, container, false)
        val nameTV = view.findViewById<TextView>(R.id.memberName)
        val imageIV = view.findViewById<ImageView>(R.id.memberPicture)
        val bundle = arguments
        nameTV.text = bundle?.getString("name")
        bundle?.getInt("image", -1)?.let { imageIV.setImageResource(it) }
        return view
    }

    companion object {

        fun newInstance(name: String?, imageSrc: Int): FragmentForViewPager {
            val fragment = FragmentForViewPager()
            with(Bundle()){
                putString("name", name)
                putInt("image", imageSrc)
                fragment.arguments = this
            }
            return fragment
        }
    }
}