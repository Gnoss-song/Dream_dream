package com.basic.intent.request

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ResponseActivityResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)
        val responseEdit = findViewById<EditText>(R.id.edit_response_text)
        val taraImage: ImageView = findViewById(R.id.tara_image)
        val textResponse = findViewById<Button>(R.id.textResponse)

        textResponse.setOnClickListener {
            var responseMessage = responseEdit.text.toString()
            val intent = Intent()
            if (responseMessage.isNullOrBlank()) {
                responseMessage = " 에디터에 문자가 입력되지 않았네요!"
                intent.putExtra("response_message", responseMessage)
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra("response_message", responseMessage)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
        taraImage.setOnClickListener {
            val intent = Intent()
            intent.putExtra("response_image", R.drawable.tara_jee_yeon)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
