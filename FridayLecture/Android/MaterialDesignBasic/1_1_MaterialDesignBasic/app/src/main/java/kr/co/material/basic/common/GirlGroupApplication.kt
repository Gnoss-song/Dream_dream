package kr.co.material.basic.common

import android.app.Application
import android.content.Context

class GirlGroupApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object {
        lateinit var appInstance: GirlGroupApplication
        fun getGirlGroupContext() : GirlGroupApplication = appInstance
    }
}