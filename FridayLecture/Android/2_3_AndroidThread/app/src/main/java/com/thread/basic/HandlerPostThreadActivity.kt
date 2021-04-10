package com.thread.basic

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


const val PROGRESS_DIALOG_TWO = 2

@Suppress("DEPRECATION")
@SuppressWarnings("ALL")
class HandlerPostThreadActivity : AppCompatActivity() {
    private var postThread: PostHandlerThread? = null
    private var progressDialog: ProgressDialog? = null
    private var backGroundMessage: EditText? = null
    private val mainHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_runnable_using_layout)
        backGroundMessage = findViewById(R.id.back_ground_message)
        val btnMessageThread: Button = findViewById(R.id.btn_message_thread)
        btnMessageThread.setOnClickListener{
            showDialog(PROGRESS_DIALOG_TWO, null)
        }
    }
    override fun onCreateDialog(id: Int, bundle: Bundle?): Dialog? {
        return when (id) {
            PROGRESS_DIALOG_TWO -> {
                progressDialog = ProgressDialog(this@HandlerPostThreadActivity)
                progressDialog!!.max = 20
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                progressDialog!!.setMessage("로딩중...잠시만 기다리시기 바랍니다.")
                postThread = PostHandlerThread()
                postThread!!.start()
                progressDialog
            }
            else -> null
        }
    }

    private inner class PostHandlerThread : Thread() {
        private var threadFlag = false
        private var increment = 0
        override fun run() {
            while (!threadFlag) {
                try {
                    sleep(500)
                    mainHandler.post {
                        if (increment >= 20) {
                            dismissDialog(PROGRESS_DIALOG_ONE)
                            postThread!!.interrupt()
                        } else {
                            backGroundMessage!!.setText(
                                backGroundMessage!!.text.toString() +
                                        increment + ":"
                            )
                        }
                        progressDialog!!.progress = increment++
                    }
                } catch (e: InterruptedException) {
                    //인터럽트가 걸리면 종료 됨
                    Log.e("BACK_THREAD_TAG", " 쓰레드 인터럽트 종료 됨!")
                    threadFlag = true
                }
            }
        }
    }
}
