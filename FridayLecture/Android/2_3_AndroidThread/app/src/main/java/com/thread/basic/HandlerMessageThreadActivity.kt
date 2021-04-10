@file:Suppress("DEPRECATION")

package com.thread.basic


import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val PROGRESS_DIALOG_ONE = 1
@Suppress("DEPRECATION")
@SuppressWarnings("ALL")
class HandlerMessageThreadActivity : AppCompatActivity() {
    private var messageThread: MessageHandlerThread? = null
    private var progressDialog: ProgressDialog? = null
    private var backGroundMessage: EditText? = null

    val mainHandler: Handler = object : Handler() {
        override fun handleMessage(message: Message) {
            val increment: Int = message.data.getInt("increment")
            progressDialog!!.progress = increment
            if (increment >= 20) {
                dismissDialog(PROGRESS_DIALOG_ONE)
                messageThread!!.setThreadEndFlag(true)
            } else {
                backGroundMessage!!.setText(backGroundMessage!!.text.toString() + increment + ":")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_message_using_layout)
        backGroundMessage = findViewById(R.id.back_ground_message)
        val btnMessageThread: Button = findViewById(R.id.btn_message_thread)
        btnMessageThread.setOnClickListener {
            showDialog(PROGRESS_DIALOG_ONE, null)
        }
    }
    override fun onCreateDialog(id: Int, bundle: Bundle?): Dialog? {
        return when (id) {
            PROGRESS_DIALOG_ONE -> {
                progressDialog = ProgressDialog(
                    this@HandlerMessageThreadActivity
                )
                progressDialog!!.max = 20
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                progressDialog!!.setMessage("로딩중~ 잠시만 기다리시기 바랍니다.")
                messageThread = MessageHandlerThread()
                messageThread!!.start()
                progressDialog
            }
            else -> null
        }
    }

    private inner class MessageHandlerThread : Thread() {
        private var threadFlag = false
        override fun run() {
            var increment = 0
            while (!threadFlag) {
                try {
                    sleep(500)
                    val messageThread: Message = mainHandler.obtainMessage()
                    val bundle = Bundle()
                    bundle.putInt("increment", increment++)
                    messageThread.setData(bundle)
                    mainHandler.sendMessage(messageThread)
                } catch (e: InterruptedException) {
                    //인터럽트가 걸리면 종료 됨
                    Log.e("BACK_THREAD_TAG", " 쓰레드 인터럽트 종료 됨!")
                }
            }
        }

        //종료하기 위함
        fun setThreadEndFlag(threadFlag: Boolean) {
            this.threadFlag = threadFlag
        }
    }
}
