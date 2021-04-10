package kr.co.material.basic.remainder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kr.co.material.basic.R

class GirlGroupViewHolder(var itemRoot: View) : RecyclerView.ViewHolder(itemRoot) {
    var memberTV: TextView = itemView.findViewById(R.id.member_name)
    var memberIV: CircleImageView = itemView.findViewById(R.id.girls_group_member_image)
}