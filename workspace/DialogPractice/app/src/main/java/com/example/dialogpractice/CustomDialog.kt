package com.example.dialogpractice

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

/*
  대화상자는 반드시 액티비티에 속해야한다.
*/

class CustomDialog(private val message: String,private val owner: MainActivity)
    : Dialog(owner){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //대화상자에 타이틀을 사용하지 않음
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_layout)
        val subjectTV = findViewById<TextView>(R.id.dialogMessage)
        subjectTV.text = message
        val okBtn = findViewById<TextView>(R.id.okBtn)
        okBtn.setOnClickListener{
            owner.dialogCallback(true)
            dismiss()
        }
        val cancelBtn = findViewById<TextView>(R.id.cancelBtn)
            owner.dialogCallback(false)
            dismiss()
    }
}