package com.example.practice02

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private const val TARGET_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

interface MovieRetrofit {

    @GET("searchDailyBoxOfficeList.json?key=dc28abb0518005f042e54d0774a164dc")
    fun getBoxOffice(
            @Query("targetDt") targetDt: String
    ) : Call<MovieResponse>

    @FormUrlEncoded
    @POST("포스트url")
    fun getPost(
            @Field("parameter1") prameter1: String,
            @Field("parameter2") prameter2: String
    ) : Single<MovieResponse>

    @Multipart
    @POST
    fun insertPost(
            @Part("parameter") parameter: String,
            @Part picture: MultipartBody.Part
    ) : Single<MovieResponse>

    companion object {
        private var _movieRetrofit : MovieRetrofit? = null
        private val movieRetrofit get() = _movieRetrofit!!
        operator fun invoke() : MovieRetrofit {
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
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .build()
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                _movieRetrofit =  Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(TARGET_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                        .create(MovieRetrofit::class.java)
                return movieRetrofit
            }
        }
    }
}