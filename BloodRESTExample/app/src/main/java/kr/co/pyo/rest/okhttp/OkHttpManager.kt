package kr.co.pyo.rest.okhttp

import kr.co.pyo.rest.common.HOST
import kr.co.pyo.rest.common.PORT
import kr.co.pyo.rest.common.PROTOCOL
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by pyo in soo
 */
class OkHttpManager {
    companion object{
        private lateinit var okHttpClient: OkHttpClient

        fun getOkHttpClient(): OkHttpClient {
            if (this::okHttpClient.isInitialized) {
                return okHttpClient
            } else {
                okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
            }
            return okHttpClient
        }

        /**
         * 보통 GET 방식에서는 이렇게 넘겨준다
         */
        fun getOkHttpUrl(targetURL: String) : HttpUrl{
            return HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .addPathSegments(targetURL)
                .build()
        }
    }
}