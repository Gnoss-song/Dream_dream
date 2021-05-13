package kr.co.ssong.lbs

import android.app.Application

class LBSMapApplication: Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: LBSMapApplication? = null
        fun applicationContext(): LBSMapApplication{
            return instance as LBSMapApplication
        }
    }

}