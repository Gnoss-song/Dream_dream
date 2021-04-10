package kr.co.material.basic.common

import kr.co.material.basic.R
import java.util.*
import java.util.Collections.shuffle

object GirlGroupRandomInit {
    private val random = Random(System.currentTimeMillis())

    private var girlGenerationMaps: HashMap<Int, String> = HashMap()
    init {
        girlGenerationMaps[R.drawable.girls_generation_all] = "소녀시대"
        girlGenerationMaps[R.drawable.girls_generation_hyoyeon] = "효연"
        girlGenerationMaps[R.drawable.girls_generation_seohyun] = "서현"
        girlGenerationMaps[R.drawable.girls_generation_sunny] = "써니"
        girlGenerationMaps[R.drawable.girls_generation_suyoung] = "수영"
        girlGenerationMaps[R.drawable.girls_generation_taeyeon] = "태연"
        girlGenerationMaps[R.drawable.girls_generation_tifany] = "티파니"
        girlGenerationMaps[R.drawable.girls_generation_yuri] = "유리"
        girlGenerationMaps[R.drawable.girls_generation_jesica] = "제시카"
        girlGenerationMaps[R.drawable.girls_generation_yuna] = "윤아"
    }
    fun shuffleGirlsGeneration(): ArrayList<Int> {
        val keys = ArrayList(girlGenerationMaps.keys)
        shuffle(keys, random)
        return keys
    }

    fun getGirlGenerationName(key: Int): String {
        return girlGenerationMaps[key].toString()
    }

    private val twiceMaps = HashMap<Int, String>()
    init {
        twiceMaps[R.drawable.twice_all] = "트와이스"
        twiceMaps[R.drawable.twice_chaeyeong] = "채영"
        twiceMaps[R.drawable.twice_dahyeon] = "다현"
        twiceMaps[R.drawable.twice_jihyo] = "지효"
        twiceMaps[R.drawable.twice_jungyeon] = "수영"
        twiceMaps[R.drawable.twice_mina] = "미나"
        twiceMaps[R.drawable.twice_momo] = "모모"
        twiceMaps[R.drawable.twice_nayeon] = "나연"
        twiceMaps[R.drawable.twice_sana] = "사나"
        twiceMaps[R.drawable.twice_tzuyu] = "쯔위"
    }
    fun shuffleTwice(): ArrayList<Int> {
        val keys = ArrayList(twiceMaps.keys)
        shuffle(keys, random)
        return keys
    }

    fun getTwiceName(key: Int): String {
        return twiceMaps[key].toString()
    }

}