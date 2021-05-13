package com.pyo.lbs.map.common

import com.pyo.lbs.map.friend.CoordinateJSON
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RESTInterface {
    @FormUrlEncoded
    @POST("/friendFind/friendInsert.pyo")
    fun friendInsert(
           @Field("name") name: String, @Field("latitude") lat: String, @Field("longitude") lng: String
         ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("/friendFind/findFriendCoordinate.pyo")
    fun findFriend(@Field("name") name: String) : Call<CoordinateJSON>

    @GET("/friendFind/friendList.pyo")
    fun findFriends() : Call<ResponseBody>
}