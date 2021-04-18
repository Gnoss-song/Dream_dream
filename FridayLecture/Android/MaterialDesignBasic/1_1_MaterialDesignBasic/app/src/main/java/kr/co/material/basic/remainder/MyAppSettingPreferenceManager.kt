package kr.co.material.basic.remainder

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import kr.co.material.basic.common.GirlGroupApplication

class MyAppSettingPreferenceManager {
    companion object {
        private lateinit  var manager: MyAppSettingPreferenceManager
        private lateinit var sp: SharedPreferences
        private lateinit var spEditor: SharedPreferences.Editor

        @SuppressLint("CommitPrefEdits")
        fun getInstance(context: Context): MyAppSettingPreferenceManager {

            if (this::manager.isInitialized) {
                return manager
            } else {
                sp = PreferenceManager.getDefaultSharedPreferences(context)
                spEditor = sp.edit()
                manager = MyAppSettingPreferenceManager()
            }

            return manager
        }

        private const val AUTO_LOGIN = "auto_login"
        private const val PUSH_NOTICE = "push_notice"
        private const val VOLUME = "volume"
        private const val RANK = "rank"
    }
    var isAutoLogin: Boolean
        get() = sp.getBoolean(AUTO_LOGIN, false)
        set(autoLogin) {
            with(spEditor){
                putBoolean(AUTO_LOGIN, autoLogin)
                apply()
            }
        }
    var isPushNotice: Boolean
        get() = sp.getBoolean(PUSH_NOTICE, false)
        set(pushNotice) {
            with(spEditor){
                putBoolean(PUSH_NOTICE, pushNotice)
                apply()
            }
        }
    var volume: Int
        get() = sp.getInt(VOLUME, 5)
        set(progress) {
            with(spEditor){
                putInt(VOLUME, progress)
                apply()
            }
        }
    var rank: String = sp.getString(RANK, "이사").toString()
        set(rank) {
            with(spEditor){
                putString(RANK, rank)
                apply()
            }
        }
}