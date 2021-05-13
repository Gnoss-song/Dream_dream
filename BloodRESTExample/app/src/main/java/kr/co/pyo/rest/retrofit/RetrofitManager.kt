package kr.co.pyo.rest.retrofit

import kr.co.pyo.rest.common.BloodRestService
import kr.co.pyo.rest.common.TARGET_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pyo in soo
 */


class RetrofitManager {
//    object BloodService{
//        var bloodService: BloodRestService = Retrofit.Builder()
//            .baseUrl(TARGET_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(BloodRestService::class.java)
    companion object{
    object BloodService{
        var bloodService: BloodRestService = Retrofit.Builder()
            .baseUrl(TARGET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BloodRestService::class.java)
    }
    }
}