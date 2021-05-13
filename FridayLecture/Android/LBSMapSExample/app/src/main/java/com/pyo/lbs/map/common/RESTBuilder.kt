package com.pyo.lbs.map.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val IP_ADDRESS = "http://172.30.1.22:5678/"

class RESTBuilder {
    object FriendBuilder{
        var friendBuilder = Retrofit.Builder()
            .baseUrl("$IP_ADDRESS")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RESTInterface::class.java)!!
    }
}