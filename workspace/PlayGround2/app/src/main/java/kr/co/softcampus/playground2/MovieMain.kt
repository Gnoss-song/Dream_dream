package kr.co.softcampus.playground2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.playground2.databinding.ActivityMovieMainBinding

class MovieMain : AppCompatActivity() {
    private lateinit var binding: ActivityMovieMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_movie_main)

        binding = ActivityMovieMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieRecyclerAdapter()

    }



    private fun itemData():MutableList<DataMovie>{
        val itemData = mutableListOf<DataMovie>()
        itemData.add(DataMovie(R.drawable.iron,"아이언맨"))
        itemData.add(DataMovie(R.drawable.spider,"스파이더맨"))
        itemData.add(DataMovie(R.drawable.blackpancer,"블랙팬서"))
        itemData.add(DataMovie(R.drawable.doctor,"닥터스트레인지"))
        itemData.add(DataMovie(R.drawable.hulk,"헐크"))
        itemData.add(DataMovie(R.drawable.thor,"토르"))

        return itemData


    }
}