package kr.co.material.basic.remainder

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import kr.co.material.basic.R

class SubSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.sub_one_preferences, rootKey)
    }
}