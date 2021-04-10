package kr.co.material.basic.remainder

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kr.co.material.basic.R

class PreferenceSettingsTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_settings_two)
        supportFragmentManager.beginTransaction().replace(R.id.settings, SettingsFragment()).commit()
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.main_preferences, rootKey)
        }

        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            if (preference.key != null && preference.key == "dialog") {
                val builder = context?.let { AlertDialog.Builder(it) }
                builder?.let{
                    it.setTitle("대화상자")
                    it.setMessage("대화내용 메세지")
                    it.setPositiveButton("OK") { _ , _  -> }
                    it.show()
                    return true
                }

            }
            return super.onPreferenceTreeClick(preference)
        }
    }
}