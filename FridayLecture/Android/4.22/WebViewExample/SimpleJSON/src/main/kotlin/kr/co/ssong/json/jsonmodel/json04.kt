package kr.co.ssong.json.jsonmodel

import org.json.JSONArray
import org.json.JSONObject

fun main(){

    val jsonRead = JSONObject(jsonString)

    val personObj = Person(
        jsonRead.optString("name"),
        jsonRead.optString("alias"),
        jsonRead.optBoolean("isAlive"),
        jsonRead.optInt("age"),
        jsonRead.optDouble("height_cm"),
        jsonRead.optJSONObject("address"),
        jsonRead.optJSONArray("phoneNumbers"),
        jsonRead.optJSONArray("hobby"),
        jsonRead.optBoolean("spouse")
    ).apply {
        val addressInfo = Address(
            address.optString("streetAddress"),
            address.optString("city")
        )
    }


    println()
    println(personObj.toString())
}

