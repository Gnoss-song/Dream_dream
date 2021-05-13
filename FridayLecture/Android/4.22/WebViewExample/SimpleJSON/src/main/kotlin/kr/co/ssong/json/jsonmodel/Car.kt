package kr.co.ssong.json.jsonmodel

import org.json.JSONArray

const val inputJSONType=
    """{"mark":"AUDI","model":"2014","type":"PETROL","maker":"AUDIGermany", "cost":30000,"colors":["GRAY","BLACK","WHITE"]}"""

data class Car(
    val mark: String = "",
    val model: String = "",
    val type: String = "",
    val maker: String = "",
    val cost: Int = -1,
    val colors: JSONArray
)

