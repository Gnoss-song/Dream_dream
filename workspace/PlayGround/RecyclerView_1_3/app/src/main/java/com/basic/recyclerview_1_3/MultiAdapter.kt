package com.basic.recyclerview_1_3

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class MultiAdapter(var items: MutableList<MyData>, val owner: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val currentType = items[position]
        when (currentType.type) {
            MyData.TYPE_LEFT -> return VIEW_TYPE_LEFT
            MyData.TYPE_CENTER -> return VIEW_TYPE_CENTER
            MyData.TYPE_RIGHT -> return VIEW_TYPE_RIGHT
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            VIEW_TYPE_LEFT -> {
               val view = inflater.inflate(R.layout.view_left, parent, false)
                LeftViewHolder(view)
            }
            VIEW_TYPE_CENTER -> {
                val view = inflater.inflate(R.layout.view_center, parent, false)
                CenterViewHolder(view)
            }
            VIEW_TYPE_RIGHT -> {
                val view = inflater.inflate(R.layout.view_right, parent, false)
                RightViewHolder(view)
            }
            else -> throw RuntimeException("No Holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = items[position]
        when (currentItem.type) {
            VIEW_TYPE_LEFT -> (holder as LeftViewHolder).setMyData(items[position])
            VIEW_TYPE_CENTER -> (holder as CenterViewHolder).setMyData(items[position])
            VIEW_TYPE_RIGHT -> (holder as RightViewHolder).setMyData(items[position])
        }
    }

    override fun getItemCount() = items.size

    companion object {
        private const val VIEW_TYPE_LEFT = 1
        private const val VIEW_TYPE_CENTER = 2
        private const val VIEW_TYPE_RIGHT = 3
    }
}