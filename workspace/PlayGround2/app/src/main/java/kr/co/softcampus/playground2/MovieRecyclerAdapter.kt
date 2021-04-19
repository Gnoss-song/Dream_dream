package kr.co.softcampus.playground2

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Array.get

class MovieRecyclerAdapter() :

    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    private val listData : ArrayList<DataMovie> = ArrayList()


    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.iv_movie)
        val title : TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listData = listData[position]
        with(holder){
            image.setImageResource(listData.image)
            title.text = listData.title
        }
    }

    override fun getItemCount() = listData.size

    fun addItem(data:DataMovie){
        listData.add(data)
    }
}