package kr.co.material.basic.remainder

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import kr.co.material.basic.R

class PreferenceSettingsOneActivity : AppCompatActivity() {
    private lateinit var autoLogin: SwitchCompat
    private lateinit var pushNotice: CheckedTextView
    private lateinit var volumeBar: AppCompatSeekBar
    private lateinit var volumeSwitcher: TextSwitcher
    private lateinit var rankSpinner: AppCompatSpinner
    private lateinit var preferenceManager: MyAppSettingPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_settings_one)

        autoLogin = findViewById(R.id.autoLogin)
        pushNotice = findViewById(R.id.pushNotice)
        volumeSwitcher = findViewById(R.id.volumeSwitcher)
        volumeBar = findViewById(R.id.seekBar)
        rankSpinner = findViewById(R.id.rankSpinner)
        preferenceManager = MyAppSettingPreferenceManager.getInstance(this)
        /**
         * 현재 앱 어디에서나 사용 가능
         */
        autoLogin.setOnCheckedChangeListener { _, isCheck ->
            preferenceManager.isAutoLogin = isCheck
        }
        pushNotice.setOnClickListener {
            preferenceManager.isPushNotice = !pushNotice.isChecked
        }
        pushNotice.setOnClickListener{
            preferenceManager.isPushNotice = !pushNotice.isChecked
            pushNotice.toggle()
        }
        pushNotice.setOnClickListener{
            preferenceManager.isPushNotice = !pushNotice.isChecked
            pushNotice.toggle()
        }
        volumeSwitcher.setFactory {
            val switchTV = TextView(this@PreferenceSettingsOneActivity)
            switchTV.run {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                textSize = 17f
            }
            switchTV
        }
        volumeBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            private var currentProgress = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentProgress = progress
                volumeSwitcher.setText(String.format("볼륨 %d", progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                preferenceManager.volume = currentProgress
            }
        })
        rankSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedRank = parent.selectedItem as String
                preferenceManager.rank = selectedRank
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        autoLogin.isChecked = preferenceManager.isAutoLogin
        pushNotice.isChecked = preferenceManager.isPushNotice
        rankSpinner.run { setSelection((adapter as ArrayAdapter<String>).getPosition(preferenceManager.rank)) }
        val volumeProgress = preferenceManager.volume
        volumeSwitcher.setText(String.format("볼륨 %d", volumeProgress))
        volumeBar.progress = volumeProgress

    }
}

