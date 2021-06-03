package kr.co.softcampus.joyce

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-06-03
 * @desc
 */

@Dao
interface MemoDAO {
    @Insert(onConflict = REPLACE)
    //PK가 똑같으면 덮어쓴다(Replace)
    fun insert(memo:MemoEntity)

    @Query("SELECT * FROM memo")
    //memo : 엔티티 테이블 이름
    fun getAll():List<MemoEntity>

    @Delete
    fun delete(memo:MemoEntity)
}