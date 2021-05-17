package kr.co.softcampus.practice01

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-05-17
 * @desc
 */

const val BASE_URL= "http://52.78.206.155:8081/"


interface TestService {
    @GET("api/notice")
    fun getTest(
    ) : Single<TestResponse>


    @GET("api/notice/{id}")
    fun getTest2(
        @Query("id") id : Int
    ) : Single<TestResponse>


    companion object{
        private var _testService : TestService? = null
        private val testService get() = _testService!!
        operator fun invoke() : TestService {
            if(_testService != null){
                return testService
            }else {
                val requestInterceptor = Interceptor {
                    val url = it.request()
                        .url()
                        .newBuilder()
                        .build()
                    val request = it.request()
                        .newBuilder()
                        .url(url)
                        .build()
                    return@Interceptor it.proceed(request)
                }
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                _testService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build()
                    .create(TestService::class.java)
            }
            return testService
        }
    }
}

