package com.example.practice02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice02.databinding.RecycleItemBinding

class MovieAdapter2(
    private val list : List<MovieResponse.BoxOfficeResult.DailyBoxOffice>
    ) : RecyclerView.Adapter<MovieAdapter2.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setBind(list[position])
    }

    inner class MovieViewHolder(private val binding : RecycleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setBind(item : MovieResponse.BoxOfficeResult.DailyBoxOffice) {
            with(binding) {
                movie = item
                main = MainActivity()
                executePendingBindings()
            }
        }

    }
}