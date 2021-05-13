package kr.co.pyo.rest.glide

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kr.co.pyo.rest.R
import kr.co.pyo.rest.common.IMAGE_ADDRESS


class FragmentForViewPager : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.viewpager_item, container, false)
        val nameTV = view.findViewById<TextView>(R.id.memberName)
        val imageIV = view.findViewById<ImageView>(R.id.memberPicture)
        val bundle: Bundle? = arguments
        nameTV.text = bundle?.getString("name")
        val targetImage = """${IMAGE_ADDRESS}${bundle?.getString("image")}"""
        Glide.with(this).load(Uri.parse(targetImage)).into(imageIV)
        return view
    }
    companion object {
        fun newInstance(name: String, imageSrc: String): FragmentForViewPager {
            val fragment = FragmentForViewPager()
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("image", imageSrc)
            fragment.arguments = bundle
            return fragment
        }
    }
}