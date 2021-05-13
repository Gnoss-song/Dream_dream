package kr.co.ssong.practice04


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DailyBoxOfficeResponse(

    @SerializedName("boxOfficeResult")
    val boxOfficeResult: BoxOfficeResult = BoxOfficeResult()
) {
    data class BoxOfficeResult(
        @SerializedName("boxofficeType")
        val boxofficeType: String = "", // 일별 박스오피스
        @SerializedName("showRange")
        val showRange: String = "", // 20210510~20210510
        @SerializedName("dailyBoxOfficeList")
        val dailyBoxOfficeList: List<DailyBoxOffice> = listOf()
    ) {
        data class DailyBoxOffice(
            @SerializedName("rnum")
            val rnum: String = "", // 1
            @SerializedName("rank")
            val rank: String = "", // 1
            @SerializedName("rankInten")
            val rankInten: String = "", // 1
            @SerializedName("rankOldAndNew")
            val rankOldAndNew: String = "", // OLD
            @SerializedName("movieCd")
            val movieCd: String = "", // 20193068
            @SerializedName("movieNm")
            val movieNm: String = "", // 비와 당신의 이야기
            @SerializedName("openDt")
            val openDt: String = "", // 2021-04-28
            @SerializedName("salesAmt")
            val salesAmt: String = "", // 57802810
            @SerializedName("salesShare")
            val salesShare: String = "", // 16.4
            @SerializedName("salesInten")
            val salesInten: String = "", // -116735410
            @SerializedName("salesChange")
            val salesChange: String = "", // -66.9
            @SerializedName("salesAcc")
            val salesAcc: String = "", // 2796022110
            @SerializedName("audiCnt")
            val audiCnt: String = "", // 6252
            @SerializedName("audiInten")
            val audiInten: String = "", // -11766
            @SerializedName("audiChange")
            val audiChange: String = "", // -65.3
            @SerializedName("audiAcc")
            val audiAcc: String = "", // 304780
            @SerializedName("scrnCnt")
            val scrnCnt: String = "", // 633
            @SerializedName("showCnt")
            val showCnt: String = "" // 1485
        )
    }
}