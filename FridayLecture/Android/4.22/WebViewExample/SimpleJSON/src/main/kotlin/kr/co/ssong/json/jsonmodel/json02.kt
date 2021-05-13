package kr.co.ssong.json.jsonmodel

import org.json.JSONArray
import org.json.JSONObject
import javax.swing.UIManager.put

/**
 * JSON write 기본 예제
 */

fun main(){
    val writeJSON = JSONObject()
    var colors: JSONArray

    writeJSON.apply{
        put("mark","AUDI")
        put("model", "2014")
        put("type","PETROL")
        put("maker", "AUDI Germany")
        put("cost", 30000)

        colors = JSONArray()
        with(colors){
            put("GRAY")
            put("BLACK")
            put("WHITE")
        }
    }.put("colors", colors)

    println(writeJSON.toString())
}
