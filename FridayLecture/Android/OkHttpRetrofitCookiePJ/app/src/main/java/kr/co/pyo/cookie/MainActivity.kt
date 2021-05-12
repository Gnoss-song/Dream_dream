package kr.co.pyo.cookie

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var message: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message = findViewById(R.id.message)

        val createBtn = findViewById<Button>(R.id.create_cookie)
        createBtn.setOnClickListener {
            val service = OkHttpRetrofitCookieManager.cookieService
            val call = service.createCookieService()
            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    runOnUiThread {
                        message?.text = "서버에서 쿠키 생성"
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e("TAG", t.toString())
                }
            })
        }
        val sendBtn = findViewById<Button>(R.id.sender_cookie)
        sendBtn.setOnClickListener {
            val service = OkHttpRetrofitCookieManager.cookieService
            val call = service.sendCookieService()
            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    runOnUiThread {
                        message?.text = "서버로 쿠키 보내기"
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
            })
        }
    }
}