package com.example.practice02

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrofit {

    @GET("searchDailyBoxOfficeList.json?key=dc28abb0518005f042e54d0774a164dc")
    fun getBoxOffice (
        @Query("targetDt") targetDt : String
    ) : Call<MovieResponse>

    companion object {
        operator fun invoke() : MovieRetrofit {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                var request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                Log.e("[요청 URL]", url.toString())
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MovieRetrofit::class.java)
        }
    }
}