package kr.co.softcampus.matcheap_mypage

data class Item(val marketIV:Int,
                var marketName:String = "",
                var marketLocation: String = "",
                var marketClass: String ="",
                var marketDistance : String = "",
                var marketRank : String = "")
