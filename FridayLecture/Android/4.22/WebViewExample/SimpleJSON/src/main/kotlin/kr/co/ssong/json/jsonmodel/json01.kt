package kr.co.ssong.json.jsonmodel

import org.json.JSONObject

/**
 *  JSON read 기본 예제
 */

fun main(){

    val jsonRead = JSONObject(inputJSONType)

    val carObj = Car(
        jsonRead.optString("mark"),
        jsonRead.optString("model"),
        jsonRead.optString("type"),
        jsonRead.optString("maker"),
        jsonRead.optInt("cost"),
        jsonRead.optJSONArray("colors")
    ).apply {
        colors.forEach(::println)
    }
    println()
    println(carObj.toString())
}
