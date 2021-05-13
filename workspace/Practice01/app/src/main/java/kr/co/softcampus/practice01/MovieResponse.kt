package kr.co.softcampus.practice01


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("boxOfficeResult")
    val boxOfficeResult: BoxOfficeResult
) {
    data class BoxOfficeResult(
        @SerializedName("boxofficeType")
        val boxofficeType: String, // 일별 박스오피스
        @SerializedName("showRange")
        val showRange: String, // 20210328~20210328
        @SerializedName("dailyBoxOfficeList")
        val dailyBoxOfficeList: List<DailyBoxOffice>
    ) {
        data class DailyBoxOffice(
            @SerializedName("rnum")
            val rnum: String, // 1
            @SerializedName("rank")
            val rank: String, // 1
            @SerializedName("rankInten")
            val rankInten: String, // 0
            @SerializedName("rankOldAndNew")
            val rankOldAndNew: String, // OLD
            @SerializedName("movieCd")
            val movieCd: String, // 20215850
            @SerializedName("movieNm")
            val movieNm: String, // 고질라 VS. 콩
            @SerializedName("openDt")
            val openDt: String, // 2021-03-25
            @SerializedName("salesAmt")
            val salesAmt: String, // 1126166460
            @SerializedName("salesShare")
            val salesShare: String, // 52.9
            @SerializedName("salesInten")
            val salesInten: String, // -115196470
            @SerializedName("salesChange")
            val salesChange: String, // -9.3
            @SerializedName("salesAcc")
            val salesAcc: String, // 3151710220
            @SerializedName("audiCnt")
            val audiCnt: String, // 117247
            @SerializedName("audiInten")
            val audiInten: String, // -10590
            @SerializedName("audiChange")
            val audiChange: String, // -8.3
            @SerializedName("audiAcc")
            val audiAcc: String, // 325108
            @SerializedName("scrnCnt")
            val scrnCnt: String, // 1292
            @SerializedName("showCnt")
            val showCnt: String // 4719
        )
    }
}