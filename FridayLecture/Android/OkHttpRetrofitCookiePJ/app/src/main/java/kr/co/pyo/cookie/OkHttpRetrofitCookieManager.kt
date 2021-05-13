package kr.co.pyo.cookie

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

object OkHttpRetrofitCookieManager {
    private const val TARGET_ADDRESS = "http://172.16.200.184:5678/"
    private val retrofitBuilder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TARGET_ADDRESS)
    private var okHttpClient: OkHttpClient? = null

    val cookieService: RESTCookieService
        get() = retrofitBuilder.build().create(RESTCookieService::class.java)

    internal class HTTPHeaderSetCookieHeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            // cookies 가져오기
            val preferences: Set<String>? = MyAppSettingPreferenceManager.getInstance(AppGlobalContext.getContext()).getCookies()
            for (cookie in preferences as Set<String>) {
                builder.addHeader("Cookie", cookie)
            }
            // Web,Android,iOS 구분
            builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")
            return chain.proceed(builder.build())
        }
    }

    class HTTPHeaderGETCookieInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse: Response = chain.proceed(chain.request())
            if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
                val cookies: MutableSet<String> = HashSet()
                for (header in originalResponse.headers("Set-Cookie")) {
                    cookies.add(header)
                    Log.e("HTTP GET Cookie", header)
                }
                // cookies header -> preference
                MyAppSettingPreferenceManager.getInstance(AppGlobalContext.getContext()).setCookies(cookies)
            }
            return originalResponse
        }
    }
    init {
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(HTTPHeaderGETCookieInterceptor())
                .addNetworkInterceptor(HTTPHeaderSetCookieHeaderInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build()
        retrofitBuilder.client(okHttpClient) //OkHttp 와 연동
    }
}