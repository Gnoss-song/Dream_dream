package kr.co.softcampus.matcheap_mypage

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

class InformDetailAdapter (
        private val itemList: MutableList<Item>,
        private val owner : Activity
) : RecyclerView.Adapter<InformDetailAdapter.ViewHolderClass>(){



        inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

                val itemView = itemView as RelativeLayout

                val marketIV: ImageView = itemView.findViewById(R.id.marketIV)
                val marketName: TextView = itemView.findViewById(R.id.marketName)
                val marketLocation: TextView = itemView.findViewById(R.id.marketLocation)
                val marketDistance: TextView = itemView.findViewById(R.id.marketDistance)
                val marketRank: TextView = itemView.findViewById(R.id.marketRank)
                val marketClass: TextView = itemView.findViewById(R.id.marketClass)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.inform_01, parent, false)
                return ViewHolderClass(view)
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
                val itemData = itemList[position]
                with(holder){
                        marketIV.setImageResource(itemData.marketIV)
                        marketName.text = itemData.marketName
                        marketLocation.text = itemData.marketLocation
                        marketDistance.text = itemData.marketDistance
                        marketRank.text = itemData.marketRank
                        marketClass.text = itemData.marketClass
                }
                holder.itemView.setOnClickListener{
                        val target = Intent(owner,Inform02::class.java)
                        target.putExtra("marketIV",itemData.marketIV)
                        target.putExtra("marketName",itemData.marketName)
                        target.putExtra("marketLocation",itemData.marketLocation)
                        target.putExtra("marketDistance",itemData.marketDistance)
                        target.putExtra("marketRank",itemData.marketRank)
                        target.putExtra("marketClass",itemData.marketClass)

                }
        }
        override fun getItemCount() = itemList.size
}

