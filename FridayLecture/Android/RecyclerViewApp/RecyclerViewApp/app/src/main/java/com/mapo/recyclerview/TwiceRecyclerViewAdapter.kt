package com.mapo.recyclerview

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView

class TwiceRecyclerViewAdapter(
    private val twiceList: MutableList<Twice>,
    private val owner: Activity
) : RecyclerView.Adapter<TwiceRecyclerViewAdapter.TwiceHolder>() {



    inner class TwiceHolder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {

        val rootView = rowRoot as RelativeLayout

        val twiceIV: ImageView =  rowRoot.findViewById(R.id.twiceMemberIV)
        val twiceTV: TextView =  rowRoot.findViewById(R.id.memberNameTV)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwiceHolder {
        val view = LayoutInflater.from(parent.context).inflate(
               R.layout.twice_recyclerview_item, parent, false)
        return TwiceHolder(view)
    }
    override fun onBindViewHolder(holder: TwiceHolder, position: Int) {
         val twiceData = twiceList[position]
         with(holder){
             twiceIV.setImageResource(twiceData.twiceImage)
             twiceTV.text = twiceData.twiceName
         }



        holder.rootView.setOnClickListener {
            val target = Intent(owner,TwiceDetailActivity::class.java)
            target.putExtra("twicePick",twiceData.twiceImage)
            target.putExtra("twiceName",twiceData.twiceName)
            owner.startActivity(target)
        }






    }
    override fun getItemCount() = twiceList.size
}