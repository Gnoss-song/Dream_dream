package kr.co.softcampus.playground.utils

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-05-10
 * @desc
 */
object Constants {
    const val TAG : String = "로그"
}

enum class SEARCH_TYPE{
    PHOTO,
    USER
}

object API{
    const val BASE_URL : String  = "https://api.unsplash.com/"

    const val CLIENT_ID : String = ""

    const val SEARCH_PHOTOS : String = "search/photos"
    const val SEARCH_USERS : String = "search/users"

}