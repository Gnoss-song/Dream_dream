package kr.co.ssong.json.gsonmodel

import org.json.JSONArray
import org.json.JSONObject


const val gsonString = """{
"name": "표찬연",
"alias": "제트팩표",
"isAlive": true,
"age": 25,
"height_cm": 167.6,
"address" : {
"streetAddress" : "구로디지털단지",
"city" : "서울",
"state" : "대한민국",
"postalCode" : "234-258"
},
"phoneNumbers": [
{
"type": "home",
"number": "02-234-1234"
},
{
"type": "office",
"number": "02-147-4567"
}
],
"hobby": ["3쿠션", "볼링", "야구", "등산"],
"spouse" : true
}"""

data class Person(
    val name: String = "",
    val alias: String = "",
    val isAlive: Boolean = false,
    val age: Int = -1,
    val height_cm: Double = 0.0,
    val address: JSONObject,
    val phoneNumbers:JSONArray,
    val hobby : JSONArray,
    val spouse : Boolean = false
)

data class Address(
    val streetAddress:String,
    val city : String,
    val state : String,
    val postalCode : String)

data class phoneNumbers(val phoneNumbers:MutableList<PhoneNumberInfo>)

data class PhoneNumberInfo(
    val type :String,
    val number : String
)


data class hobby(val hobby: MutableList<String>)