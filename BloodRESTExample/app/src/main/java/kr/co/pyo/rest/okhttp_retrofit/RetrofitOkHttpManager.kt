package kr.co.pyo.rest.okhttp_retrofit

import android.util.Log
import kr.co.pyo.rest.common.BloodRestService
import kr.co.pyo.rest.common.TARGET_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitOkHttpManager {
    private var okHttpClient: OkHttpClient

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(TARGET_URL)

    val bloodRESTService: BloodRestService
        get() = retrofitBuilder.build().create(BloodRestService::class.java)

    init {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val newRequest: Request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(newRequest)
            }).addInterceptor(RetryInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS).build()
        retrofitBuilder.client(okHttpClient) //OkHttp와 연동
    }

    private class RetryInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            var response: Response = chain.proceed(request)
            var tryCount = 0
            val maxLimit = 2
            while (!response.isSuccessful && tryCount < maxLimit) {
                Log.d("TAG", "요청실패 - $tryCount")
                tryCount++
                response = chain.proceed(request)
            }
            return response
        }
    }
}