package com.example.practice02

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val TARGET_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

interface MovieRetrofit2 {

    @GET("searchDailyBoxOfficeList.json?key=dc28abb0518005f042e54d0774a164dc")
    fun getBoxOffice (
        @Query("targetDt") targetDt : String
    ) : Single<MovieResponse>
    //서버로부터 지속적인 요청이 아니라 한번만 받고 끝이므로

    companion object {
        private var _movieRetrofit : MovieRetrofit2? = null
        private val movieRetrofit get() = _movieRetrofit!!
        operator fun invoke() : MovieRetrofit2 {
            if(_movieRetrofit != null) {
                return movieRetrofit
            } else {
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
                _movieRetrofit =  Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(TARGET_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(MovieRetrofit2::class.java)
                return movieRetrofit
            }
        }
    }
}