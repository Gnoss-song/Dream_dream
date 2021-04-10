package com.basic.recyclerview_1_3

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleView: TextView = itemView.findViewById(R.id.text_title)

    fun setMyData(data: MyData) {
        titleView.text = data.message
    }
}