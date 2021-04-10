package com.example.practice22

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView

class TwiceRecyclerViewAdapter (
    private val twiceList:MutableList<Twice>,
    private val owner: Activity,
    private val itemClick: (Twice) -> Unit
) : RecyclerView.Adapter<TwiceRecyclerViewAdapter.TwiceHolder>(){

    //Holder는 내부클래스로 주로 둔다.
    //한행에 놓여질 데이터들에 대한 지정. 이미지뷰는 어디서. 텍스트 뷰는 어디서.
    inner class TwiceHolder(rowRoot:View,itemClick: (Twice) -> Unit) : RecyclerView.ViewHolder(rowRoot){
        val twiceIV:ImageView = rowRoot.findViewById(R.id.twiceMemberIV)
        val twiceTV: TextView = rowRoot.findViewById(R.id.memberNameTV)
        fun click(twice : Twice,context:Context){
            itemView.setOnClickListener{itemClick(twice)}
        }
    }
    //한행을 표현할 레이아웃을 인플레이션(객체화)하는 곳.홀더가 재사용할수있도록 넘겨주는것.
    //return을 홀더로 넘겨서 view를 가지고 있을수 있도록.
    //view : RelativeLayout으로 만들었기 때문에 타입 : RelativeLayout.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwiceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.twice_recyclerview_item, parent,false)
        return TwiceHolder(view,itemClick)
    }

    //데이터하고 위젯하고 매핑시켜주는것
    override fun onBindViewHolder(holder: TwiceHolder, position: Int){
        val twiceData = twiceList[position]
        with(holder){
            twiceIV.setImageResource(twiceData.twiceImage)
            twiceTV.text = twiceData.twiceName
        }
    }

    override fun getItemCount() = twiceList.size
}