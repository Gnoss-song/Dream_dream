package kr.co.ssong.json.gsonmodel

import org.json.JSONArray
import org.json.JSONObject

fun main(){


    val gson = GsonBuilder().create()

    val personInfo = gsnon

    val gsonRead = JSONObject(gsonString)

    val personObj = Person(
        gsonRead.optString("name"),
        gsonRead.optString("alias"),
        gsonRead.optBoolean("isAlive"),
        gsonRead.optInt("age"),
        gsonRead.optDouble("height_cm"),
        gsonRead.optJSONObject("address"),
        gsonRead.optJSONArray("phoneNumbers"),
        gsonRead.optJSONArray("hobby"),
        gsonRead.optBoolean("spouse")
    ).apply {
        val addressInfo = Address(
            address.optString("streetAddress"),
            address.optString("city")
        )
    }


    println()
    println(personObj.toString())
}

