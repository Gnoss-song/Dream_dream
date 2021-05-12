package kr.co.softcampus.playground.retrofit

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-05-10
 * @desc
 */
//싱글턴
object RetrofitClient {
    //레트로핏 클라이언트 선언
    //자료가 있을지 없을지 모르기 떄문에 null허용.
    private var retrofitClient: Retrofit?  = null
    //2가지 방법 허용.
//    private lateinit var retrofitClient : Retrofit


    //레트로핏 클라 가져오기

    fun getClient(baseUrl:String) : Retrofit?{
        Log.d(TAG,"RetrofitClient - getClient{} called")

        if(retrofitClient == null){

            //레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build()
        }
        return retrofitClient
    }
}