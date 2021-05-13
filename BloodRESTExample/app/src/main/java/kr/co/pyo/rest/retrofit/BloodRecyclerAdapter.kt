package kr.co.pyo.rest.retrofit

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.pyo.rest.R
import kr.co.pyo.rest.data.BloodEntity

class BloodRecyclerAdapter(
    private val bloodList: MutableList<BloodEntity>,
    private val owner: Activity
) :    RecyclerView.Adapter<BloodRecyclerAdapter.BloodHolder>() {

    inner class BloodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val requestCurrent: TextView = itemView.findViewById(R.id.requestCurrent)
        val writeTime: TextView = itemView.findViewById(R.id.writeTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodHolder {
        val view = LayoutInflater.from(owner).inflate(R.layout.blood_recycler_item, parent, false)
        return BloodHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BloodHolder, position: Int) {
        val bloodEntity = bloodList[position]
        with(holder){
            requestCurrent.text =  """현재 상황 : " ${bloodEntity.bloodType} [${bloodEntity.donationType}] ,  ${bloodEntity.bloodValue} 개 필요"""
            writeTime.text = bloodEntity.insertDate.substring(0, 16)
        }
    }
    override fun getItemCount() = bloodList.size
}