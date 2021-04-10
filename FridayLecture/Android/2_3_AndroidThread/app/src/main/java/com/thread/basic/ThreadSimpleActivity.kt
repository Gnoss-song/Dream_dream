package com.thread.basic

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ThreadSimpleActivity : AppCompatActivity() {

    private var backThreadValue1: Long = 0
    private var backThreadValue2: Long = 0
    private var mainThreadValue: Long = 0

    //백그라운드에서 돌아 갈 쓰레드 선언
    private var backGroundThread1: SimpleThreadOne? = null
    private var backGroundThread2: SimpleThreadImpl? = null
    private lateinit var thread: Thread

    private lateinit var mainEdit: EditText
    private lateinit var backEdit1: EditText
    private lateinit var backEdit2: EditText

    val mainHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(message: Message) {
            //val bundle = message.data
            when (message.what) {
                1 -> backEdit1.text =
                    Editable.Factory.getInstance().newEditable((++backThreadValue1).toString())
                else -> backEdit2.text =
                    Editable.Factory.getInstance().newEditable((++backThreadValue2).toString())
            }
        }
    }
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainInflater = LayoutInflater.from(this)
        val layoutView: View = mainInflater.inflate(R.layout.thread_simple_1_layout, null)
        setContentView(layoutView)
        val btnThreadResult: Button = layoutView.findViewById(R.id.btn_thread_result)
        val uiThreadIncrementBtn: Button = layoutView.findViewById(R.id.btn_ui_thread_increment)
        //쓰레드 On/Off
        val threadToggle: ToggleButton = layoutView.findViewById(R.id.btn_thread_toggle)
        //textOn : 메세지 표시
        threadToggle.isChecked = true
        mainEdit = layoutView.findViewById(R.id.main_edit)
        backEdit1 = layoutView.findViewById(R.id.back_edit_1)
        backEdit2 = layoutView.findViewById(R.id.back_edit_2)


        //백그라운드 쓰레드를 On/Off 함
        threadToggle.setOnClickListener {
            if (!threadToggle.isChecked) {
                /*
                 *  Thread를 생성 시킴(1초/3초)
                 */
                backGroundThread1 = SimpleThreadOne("BACK_GROUND_THREAD_1", 1000L)
                backGroundThread1!!.start()

                backGroundThread2 = SimpleThreadImpl(3000L)
                thread = Thread(backGroundThread2)
                thread.start()

                (findViewById<TextView>(R.id.textView1)).text = " 쓰레드 스타트 됨! "
                threadToggle.isChecked = false
            } else {
                mainEdit.setText("")
                backEdit1.text =
                    Editable.Factory.getInstance().newEditable(backGroundThread1!!.name + " 종료됨!")
                backEdit2.text = Editable.Factory.getInstance().newEditable(thread.name + " 종료됨!")
                backGroundThreadFinish()
                (findViewById<TextView>(R.id.textView1)).text = "백 그라운드 쓰레드 종료 됨!"
                threadToggle.isChecked = true
                Toast.makeText(
                    this@ThreadSimpleActivity,
                    "Back Ground Thread가 종료 됩니다 ", Toast.LENGTH_SHORT
                ).show()
            }
        }

        //터치 할 때 마다 Main-Thread에서 1씩 증가 함
        uiThreadIncrementBtn.setOnClickListener {
            ++mainThreadValue
            Toast.makeText(
                this@ThreadSimpleActivity,
                "Main_UI 에서는 클릭시 마다 1씩 증가 합니다", Toast.LENGTH_SHORT
            ).show()
        }

        //Main 과 Back Ground 에서 각각 증가된 값을 출력 함
        btnThreadResult.setOnClickListener {
            mainEdit.text =
                Editable.Factory.getInstance()
                    .newEditable("""${Thread.currentThread().name} 클릭횟수 => $mainThreadValue""")
            if (backGroundThread1 != null && thread != null &&
                backGroundThread1!!.isAlive && thread.isAlive
            ) {
                backEdit1.text = Editable.Factory.getInstance().newEditable(
                    backGroundThread1!!.name + " 증가값  => "
                            + backThreadValue1
                )
                backEdit2.text = Editable.Factory.getInstance().newEditable(
                    thread.name + " 증가값  => "
                            + backThreadValue2
                )
            } else {
                Toast.makeText(
                    applicationContext,
                    "백그라운드 쓰레드가 활성화 되지 않았네요", Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    //Back Key 또는 Activity가 전경으로  갈 때 동작
    override fun onStop() {
        super.onStop()
        backGroundThreadFinish()
    }

    /*
     *  백 그라운드 쓰레드를 종료 시킴
     */
    private fun backGroundThreadFinish() {
        if (backGroundThread1 != null) {
            backGroundThread1!!.interrupt()
            backGroundThread1 = null
            backThreadValue1 = 0
        }
        if (backGroundThread2 != null) {
            thread.interrupt()
            backGroundThread2 = null
            backThreadValue2 = 0
        }
    }

    inner class SimpleThreadOne(threadName: String?, delayTime: Long) : Thread(threadName) {
        private var delayTime = delayTime
        private var threadFinishFlag = false
        override fun run() {
            while (!threadFinishFlag) {
                try {
                    sleep(delayTime)
                   /* val message = mainHandler.obtainMessage()
                    val bundle = Bundle()
                    bundle.putInt("delayItem", 1)
                    message.data = bundle*/
                    //mainHandler.sendEmptyMessage(3)
                    handler.post{
                        backEdit2.text =
                            Editable.Factory.getInstance().newEditable((++backThreadValue2).toString())
                    }
                } catch (ie: InterruptedException) {
                    Log.e("InterruptedTag", "$name Thread 인터럽트 발생!")
                    threadFinishFlag = true
                }
            }
        }
    }

    inner class SimpleThreadImpl(val delayTime: Long) : Runnable {
        private var threadFinishFlag = false
        override fun run() {
            while (!threadFinishFlag) {
                try {
                    Thread.sleep(delayTime)
                    mainHandler.sendEmptyMessage(1)
                } catch (ie: InterruptedException) {
                    Log.e(
                        "InterruptedTag",
                        "${Thread.currentThread().name} Thread 인터럽트 발생!"
                    )
                    threadFinishFlag = true
                }
            }
        }
    }

/* private inner class SimpleThreadTwo(threadName: String?, delayTime: Long) : Thread(threadName) {
     private var delayTime = delayTime
     private var threadFinishFlag = false
     override fun run() {
         while (!threadFinishFlag) {
             try {
                 sleep(delayTime)
                 ++backThreadValue2
             } catch (ie: InterruptedException) {
                 Log.e("InterruptedTag", "$name Thread 인터럽트 발생!")
                 threadFinishFlag = true
             }
         }
     }
 }*/
}
