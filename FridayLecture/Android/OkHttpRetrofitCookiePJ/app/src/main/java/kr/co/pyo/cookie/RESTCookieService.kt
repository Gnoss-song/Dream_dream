package kr.co.pyo.cookie

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface RESTCookieService {

    @GET("/androidNetwork/createCookies.pyo")
    fun createCookieService(): Call<ResponseBody>

    @GET("/androidNetwork/sendCookies.pyo")
    fun sendCookieService(): Call<ResponseBody>
}