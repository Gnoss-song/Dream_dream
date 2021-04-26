package kr.co.softcampus.playground2

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-04-26
 * @desc
 */
class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        coroutine()
    }

    fun coroutine() {

        CoroutineScope(Dispatchers.Default).launch {

            val html = CoroutineScope(Dispatchers.Default).async {

                gethtml

            }.await()



            val mTextMain = findViewById<TextView>(R.id.mTextMain)
            mTextMain.text = html
            //main Thread

        }

        fun getHtml() : String{
            //1. 클라이언트 만들기
            val client = OkHttpClient.Builder().build()
            //2. 요청
            val req = Request.Builder().url("http://www.google.com").build()
            //3. 응답
            client.newCall(req).execute().use {
                //동기방식 : execute() , 비동기 : enqueue()
                response -> return if(response.body != null){
                    response.body!!.string()
                }
                else{
                    "body null"
                }
            }
        }
        fun getHtmlStr() : String{

        }


    }
}