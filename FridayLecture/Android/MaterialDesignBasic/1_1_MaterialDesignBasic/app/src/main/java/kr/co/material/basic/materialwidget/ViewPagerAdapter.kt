package kr.co.material.basic.materialwidget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.material.basic.R
import kr.co.material.basic.materialwidget.ViewPagerAdapter.GirlHolder
import java.util.*

class ViewPagerAdapter(private val items: ArrayList<GirlsMember>) : RecyclerView.Adapter<GirlHolder>() {

    inner class GirlHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: TextView = itemView.findViewById(R.id.memberName)
        var memberImage: ImageView = itemView.findViewById(R.id.memberPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return GirlHolder(view)
    }
    override fun onBindViewHolder(holder: GirlHolder, position: Int) {
        val (name, picture) = items[position]
        with(holder){
            nameTV.text = name
            memberImage.setImageResource(picture)
        }
    }
    override fun getItemCount() = items.size
}