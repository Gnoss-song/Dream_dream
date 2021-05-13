package kr.co.ssong.practice04

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

interface BoxOfficeService {

    //GET방식 요청 함수
    @GET("searchDailyBoxOfficeList.json")
    fun getDailyBoxOffice(
    @Query("key") key : String,
    @Query("targetDt") targetDt : String
    ): Single<DailyBoxOfficeResponse>

    //post방식 함수 : 파일 업로드를 안할경우(중요)
    @FormUrlEncoded
    @POST("searchDailyBoxOfficeList.json")
    fun writeData(
        @Field("파라미터명1 이름") parameter1 : String,
        @Field("파라미터명2 이름") parameter2 : String
    ) : Single<DailyBoxOfficeResponse>

    //post방식 함수 : 사진 업로드를 할경우(중요)
    @Multipart
    @POST
    fun writeFileData(
        @Part("parameter1 이름") parameter1: String,
        @Part("parameter2 이름") parameter2: String,
        @Part file : MultipartBody.Part
    ) : Single<DailyBoxOfficeResponse>

    //RETROFIT 객체 생성 과정//
    companion object {
        //인터페이스 - 컴패티언에서만 할수있는 함수. operator fun
        // 서비스.invoke로 사용할수있다.
        //ex ) BoxOfficeService.invoke
        //parameter가 존재하면 invoke를 빼고 할수있다.
        private var _boxOfficeService : BoxOfficeService? = null
        private val boxOfficeService get() = _boxOfficeService!!
        operator fun invoke(): BoxOfficeService{
            if(_boxOfficeService != null){
                return boxOfficeService
            }else {
                val requestInterceptor = Interceptor{
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
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .build()
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                _boxOfficeService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build()
                    .create(BoxOfficeService::class.java)
            }
            return boxOfficeService
        }
    }
}