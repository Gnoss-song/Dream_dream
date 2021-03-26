package com.basic.intent.request

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val RESULT_CODE_TEXT = 100
const val RESULT_CODE_IMAGE = 200

class MainActivity : AppCompatActivity() {

    private var image: ImageView? = null
    private var textRequest: Button? = null
    private var imageRequest: Button? = null
    private var textMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textMessage = findViewById(R.id.textMessage)
        textRequest = findViewById(R.id.textRequest)
        imageRequest = findViewById(R.id.imageRequest)
        image = findViewById(R.id.image)
        textRequest?.setOnClickListener {
            val intent = Intent(this@MainActivity, ResponseActivityResult::class.java)
            startActivityForResult(intent, RESULT_CODE_TEXT)
        }
        imageRequest?.setOnClickListener {
            val intent = Intent(this@MainActivity, ResponseActivityResult::class.java)
            startActivityForResult(intent, RESULT_CODE_IMAGE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) //반드시 호출해야 함
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                RESULT_CODE_TEXT -> {
                    textMessage?.text = data?.getStringExtra("response_message")
                }
                RESULT_CODE_IMAGE -> {
                    val imageIDValue = data?.getIntExtra("response_image", -1)
                    image?.setImageResource(imageIDValue as Int)
                }
                else -> Toast.makeText(this, "응답이 없네요!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
