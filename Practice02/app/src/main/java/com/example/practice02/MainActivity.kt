package com.example.practice02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practice02.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieRetrofit = MovieRetrofit.invoke()
        movieRetrofit.getBoxOffice("20210328").enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "서버요청 오류", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val adapter = MovieAdapter(response.body()!!.boxOfficeResult.dailyBoxOfficeList as ArrayList<MovieResponse.BoxOfficeResult.DailyBoxOffice>)
                binding.recyclerView.adapter = adapter
            }

        })

    }
}