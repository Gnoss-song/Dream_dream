package kr.co.mapo.intentservicepractice

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kr.co.mapo.intentservicepractice.databinding.ActivityMainBinding

const val MAPO_BR_ACTION_NAME
class MainActivity : AppCompatActivity() {
    private lateinit var newEdit : EditText
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn =
    }

    override fun onStart(){
        super.onStart()
        val theFilter = IntentFilter()
        theFilter.addAction(MAPO_BR_ACTION_NAME)
        br = NewsBroadcast
    }

    inner class NewsBroadcastReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context,intent: Intent) {
            newsEdit.text = null
            if (intent.action == MAPO_BR_ACTION_NAME) {
                val newsMessage = intent.getStringExtra("news")
                newsBuf.append("""$newsMessage ${System.lineSeparator()}""")
                newsEdit.text = Editable.Factory.getInstance().newEditable(newsBuf.toString())
            }
        }
    }
    private fun executeBtnEvent(btn: View) {
        val serviceIntent = Intent(this@MainActivity, NewsSimpleService::class.java)
        if (R.id.startBtn == btn.id) {
            serviceIntent.putExtra("newsSubject", 3)
            //android Oreo 부터는 다음과 같이 호출한다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        } else {
            stopService(serviceIntent)
        }
    }
}