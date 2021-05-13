package kr.co.ssong.lbs

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val IS_LOCATION = "is_location"

class MapoPreferenceManager {
    companion object {
        private lateinit var manager: MapoPreferenceManager
        private lateinit var sp: SharedPreferences
        private lateinit var spEditor: SharedPreferences.Editor

        @SuppressLint("CommitPrefEdits")
        fun getInstance(): MapoPreferenceManager {
            if (this::manager.isInitialized) {
                return manager
            } else {
                sp = PreferenceManager.getDefaultSharedPreferences(LBSMapApplication.applicationContext())
                spEditor = sp.edit()
                manager = MapoPreferenceManager()
            }
            return manager
        }
    }

    /**
     * 본 앱의 퍼미션 체크 여부
     */
    var isPermission : Boolean
        get() = sp.getBoolean(IS_LOCATION, false)
        set(permissionCheck) {
            with(spEditor){
                putBoolean(IS_LOCATION, permissionCheck).apply()
            }
        }
}