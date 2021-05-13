package com.pyo.lbs.map.poi

import com.google.gson.annotations.SerializedName

/**
 * Created by pyo in soo
 */
data class TMapPOISearchResult(var searchPoiInfo: TMapPOIInfo)

data class TMapPOIInfo(
    var totalCount: String = "",
    var count: String = "",
    var page: String = "",
    var pois: TMapPOIS
)
data class TMapPOIS(var poi: MutableList<POIItem> = mutableListOf())

data class POIItem(
    var id: String = "",

    @SerializedName("name")
    var title: String = "",

    var telNo: String = "",
    var frontLat: String = "",
    var frontLon: String = "",
    var noorLat: String = "",
    var noorLon: String = "",
    var upperAddrName: String = "",
    var middleAddrName: String = "",
    var lowerAddrName: String = "",
    var detailAddrName: String = "",
    var subtitle: String = "",

    @SerializedName("desc")
    var description: String = "",

    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
) {


    override fun toString(): String {
        return title
    }

    fun updatePOIData() {
        subtitle = """$upperAddrName $middleAddrName  $lowerAddrName $detailAddrName"""
        latitude = (frontLat.toDouble() + noorLat.toDouble()) / 2
        longitude = (frontLon.toDouble() + noorLon.toDouble()) / 2
    }
}