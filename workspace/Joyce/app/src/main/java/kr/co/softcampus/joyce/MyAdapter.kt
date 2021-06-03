package kr.co.softcampus.joyce

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.joyce.databinding.ItemMemoBinding

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-06-03
 * @desc
 */
class MyAdapter(val context:Context,
var list:List<MemoEntity>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var binding : ItemMemoBinding


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.item_memo,parent,false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val memo = list[position]
        holder.memo.text
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val memo = itemView.findViewById<TextView>(R.id.textview_memo)
        val root = itemView.findViewById<ConstraintLayout>(R.id.root)

    }



}