package kr.co.softcampus.matcheap_mypage

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.matcheap_mypage.databinding.ActivityMy0102Binding

class My0102 : AppCompatActivity() {

    var marketIV = intArrayOf(
        R.drawable.solip,
        R.drawable.poonyeon,
        R.drawable.wellbeing,
        R.drawable.western,
        R.drawable.halbum
    )
    var marketName = arrayOf("솔잎식당", "풍년갈비", "웰빙뚝배기", "웨스턴후라이드라이스", "할범탕수육")
    var marketLocation = arrayOf(
        "서울특별시 마포구 마포대로4길 46 (도화동)", "서울특별시 마포구 동교로 264 (연남동)",
        "서울특별시 마포구 동교로12길 21 (서교동)", "서울특별시 마포구 홍익로 26 (동교동)",
        "서울특별시 양천구 목동중앙북로 15 (목동)"
    )
    var marketClass = arrayOf("한식", "한식", "한식", "일식", "분식")
    var marketDistance = arrayOf("1.1km", "1.4km", "2.2km", "0.5km", "4.4km")
    var marketRank = arrayOf("4.8", "4.4", "3.3", "4.5", "5.0")

    var item = arrayListOf<Any>(
        marketIV,
        marketName,
        marketLocation,
        marketClass,
        marketDistance,
        marketRank
    )

    private lateinit var binding: ActivityMy0102Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMy0102Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter1 = RecyclerAdapter()
        binding.recycler.adapter = adapter1
        binding.recycler.layoutManager = LinearLayoutManager(this)

    }


    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val itemView = layoutInflater.inflate(R.layout.my_01_02, null)
            val holder = ViewHolderClass(itemView)

            

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.marketIV.setImageResource(marketIV[position])
            holder.marketName.text = marketName[position]
            holder.marketLocation.text = marketLocation[position]
            holder.marketRank.text = marketRank[position]
            holder.marketClass.text = marketClass[position]
            holder.marketDistance.text = marketDistance[position]


        }

        override fun getItemCount(): Int {
            return marketIV.size
        }

        inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val marketIV: ImageView = itemView.findViewById(R.id.marketIV)
            val marketName: TextView = itemView.findViewById(R.id.marketName)
            val marketLocation: TextView = itemView.findViewById(R.id.marketLocation)
            val marketDistance: TextView = itemView.findViewById(R.id.marketDistance)
            val marketRank: TextView = itemView.findViewById(R.id.marketRank)
            val marketClass: TextView = itemView.findViewById(R.id.marketClass)


        }
    }
}