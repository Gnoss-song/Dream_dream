package kr.co.softcampus.joyce

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-06-03
 * @desc
 */

@Entity(tableName = "memo")

data class MemoEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long?,
    var memo:String="")
