package kr.co.softcampus.playground.retrofit

import android.util.Log
import com.google.gson.JsonElement
import kr.co.softcampus.playground.utils.API
import kr.co.softcampus.playground.retrofit.IRetrofit
import kr.co.softcampus.playground.utils.API.BASE_URL
import kr.co.softcampus.playground.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Response


/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-05-10
 * @desc
 */
class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
    }


    //레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    // 사진 검색 api 호출
    fun searchPhotos(searchTerm:String?,completion:(String)-> Unit){

        val term = searchTerm.let {
            it
        }?:""

        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            // 응답실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG,"RetrofitManager - onFailure{} called /t :$t")
            }
            //응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG,"RetrofitManager - onResponse{} called / response:${response.raw()}")

                completion(response.raw().toString())
            }



        })


    }


}