package kr.co.softcampus.joyce

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-06-03
 * @desc
 */
@Database(entities = arrayOf(MemoEntity::class),version = 1)

abstract class MemoDatabase:RoomDatabase() {
    abstract fun memoDao() : MemoDAO

    companion object {
        var INSTANCE : MemoDatabase? = null

        fun getInstance(context: Context) : MemoDatabase? {
            if(INSTANCE == null){
                synchronized(MemoDatabase::class){

                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MemoDatabase::class.java,"memo.db")
                        .fallbackToDestructiveMigration()
                            //db를 한번 생성하고나서 중간에 추가,수정을 할 경우에 모든 데이터를 새로이 시작하는 경우

                        .build()
                }
            }
            return INSTANCE
        }
    }
}