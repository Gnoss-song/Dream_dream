package kr.co.pyo.cookie

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class AppGlobalContext : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun getContext() = context
    }
}