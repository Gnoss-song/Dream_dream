package kr.co.softcampus.playground.retrofit

import com.google.gson.JsonElement
import kr.co.softcampus.playground.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-05-10
 * @desc
 */
interface IRetrofit {


    //https://www.unsplash.com/::: baseurl이다.
    //get어노테이션 뒤에 쓴것이 붙여져서 https://www.unsplash.com/search/photos/?query="searchTerm의 스트링값"

    @GET(API.SEARCH_PHOTOS)

    //매개변수 : SearchTerm , 반환 : JsonElement
    fun searchPhotos(@Query("query") searchTerm:String) : Call<JsonElement>


    //get어노테이션 뒤에 쓴것이 붙여져서 https://www.unsplash.com/search/users/?query=""
    @GET(API.SEARCH_USERS)

    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>

}