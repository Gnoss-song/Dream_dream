package com.basic.service

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.*
import kotlin.collections.HashMap

//올레오 버전부터는 각 통지에 채널과 채널의 이름을 안드로이드 통지 시스템에 알려줘야 한다
const val CHANNEL_ID = "service_channel_id"
const val CHANNEL_NAME = "service_channel_name"

class NewsSimpleService : Service() {

    //인텐트로 넘어온 엑스트라 값.
    //초기 뉴스의 종류만 선택 함
    var extraValue = 0

    //서비스에서 동작하는 백그라운드 쓰레드
    private lateinit var subThread: BackGroundSubThread

    /*
     * startService() 후에 처음 호출되는 서비스 콜백 메소드
     *  back ground thread에서도 일시적으로 UI를 업데이트 할 수 있다
     */
    var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()
        handler.post {
            Toast.makeText(
                applicationContext, "Service 콜백 메소드 onCreate() 실행! ",
                Toast.LENGTH_SHORT
            ).show()
        }
        displayNotification()
    }

    private fun displayNotification() {
        //통지를 사용자가 터치하면 돌아갈 컴포넌트를 설정
        val notifyIntent = Intent(this, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            //채널 생성
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setSmallIcon(R.mipmap.sym_def_app_icon)
            .setContentTitle("서비스가 실행중입니다")
            .setContentText("지금은 서비스 포그라운드 실행중 입니다")
            .setContentIntent(pIntent)
        //통지바에 해당 Noti를 전송한다
        startForeground(1234, builder.build())

        subThread = BackGroundSubThread("BACK_THREAD_1")

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        extraValue = intent!!.getIntExtra("newsSubject", 0)
        if (!subThread.isAlive) {
            subThread.start()
        }
        if (flags == START_FLAG_RETRY) {
            //정상종료가 아닌 경우 반드시 실행되어야 하는 인텐트를 여기서 다시 진행 함
        }
        /*if (intent == null) {
            START_STICKY 모드로 비정상 종료시 처리해야 하는 업무
        }*/
        handler.post {
            Toast.makeText(
                applicationContext, "Service 콜백 메소드 onStartCommand() 실행! ",
                Toast.LENGTH_SHORT
            ).show()
        }
        //서비스의 비정상적 종료시에 null 값을 가진 인텐트가 인자로 넘어가면서 서비스가 재실행 된다
        return START_STICKY
    }

    /*
     *  stopService 호출시  콜백 됨
     */
    override fun onDestroy() {
        super.onDestroy()
        if (subThread.isAlive) { //백그라운드 Thread 가 살아 있다면
            subThread.interrupt()
        }
        stopSelf()

    }

    /*
     *  백그라운드 쓰레드는 서비스의 라이프 사이클을 벗어남
     */
    inner class BackGroundSubThread(threadName: String) : Thread(threadName) {
        private val newsMap: HashMap<String, String> = HashMap()
        private val random: Random = Random(System.currentTimeMillis())
        private var newsMessage: String? = null

        init {
            newsMap["정치"] = "한반도 극적 통일 이루어져~~~"
            newsMap["경제"] = "통일한국 GNP 10만 달러 이룩"
            newsMap["사회"] = "한국 모든 대학 무상교육 실시"
            newsMap["연예"] = "소녀시대 태연 아카데미 여우 주연상 수상"
        }

        override fun run() {
            while (!isInterrupted) {
                newsMessage = when (extraValue) {
                    0 -> {
                        newsMap["정치"]
                    }
                    1 -> {
                        newsMap["경제"]
                    }
                    2 -> {
                        newsMap["사회"]
                    }
                    else -> {
                        newsMap["연예"]
                    }
                }
                try {
                    handler.post {
                        Toast.makeText(applicationContext, newsMessage, Toast.LENGTH_SHORT).show()
                    }
                    extraValue = random.nextInt(4)
                    sleep(3000L)
                } catch (ie: InterruptedException) {
                    currentThread().interrupt()
                }
            }
        }
    }
}
