package com.example.practice02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val list : List<MovieResponse.BoxOfficeResult.DailyBoxOffice>
    ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rank = itemView.findViewById<TextView>(R.id.rank)
        private val movieNM = itemView.findViewById<TextView>(R.id.movieNM)
        private val openDt = itemView.findViewById<TextView>(R.id.openDt)

        fun setItem(item : MovieResponse.BoxOfficeResult.DailyBoxOffice) {
            rank.text = item.rank
            movieNM.text = item.movieNm
            openDt.text = item.openDt
        }

    }
}