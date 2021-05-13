package com.example.practice02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.practice02.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val sunmi get() = "선미"
    val list = arrayListOf("선미", "혜미")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MovieRetrofit2.invoke().getBoxOffice("20210510")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ //요청성공
                        binding.recyclerView.adapter = MovieAdapter2(it.boxOfficeResult.dailyBoxOfficeList)
            }, { //실패
                Toast.makeText(applicationContext, "서버요청 오류", Toast.LENGTH_SHORT).show()
            })

        /*
        movieRetrofit.getBoxOffice("20210328").enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "서버요청 오류", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val list = response.body()!!.boxOfficeResult.dailyBoxOfficeList
                runOnUiThread {
                    val adapter = MovieAdapter2(list)
                    binding.recyclerView.adapter = adapter
                }
            }

        })
        */

        with(binding.bottomNavigationView){
            selectedItemId = R.id.menu2
            setOnNavigationItemSelectedListener {
                if(it.itemId == R.id.menu3) startActivity(Intent(this@MainActivity, PictureActivity::class.java))
                return@setOnNavigationItemSelectedListener true
            }
        }

    }

    fun test(v: View) {
        Log.e("[TEST]", "데이터바인딩 테스트")
    }
}