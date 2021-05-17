package kr.co.softcampus.practice01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView)

//GET방식, rxjava 라이브러리를 사용하는 방식이다.//
        TestService.invoke()
            .getTest().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recyclerView.adapter = TestAapter(it.data)
                // 요청성공 = onResponse
            },{
                // 요청실패 = onFailure
            })
/*
        val service = BoxOfficeService.invoke()
        service.getDailyBoxOffice("f18a699fff048166f68223bead920212","20210510")
            .enqueue(object : Callback<DailyBoxOfficeResponse>{
                override fun onResponse(
                    call: Call<DailyBoxOfficeResponse>,
                    response: Response<DailyBoxOfficeResponse>
                ) {// // 첫 중괄호부터 끝까지 response에 담음.
                    val list = response.body()!!.boxOfficeResult.dailyBoxOfficeList
                    runOnUiThread{
                        recyclerView.adapter = MovieAdapter(list)
                    }}
                override fun onFailure(call: Call<DailyBoxOfficeResponse>, t: Throwable) {
                }
            })*/
    }

    inner class TestAapter(private val list: List<TestResponse.Data>) : RecyclerView.Adapter<TestAapter.ViewHolder>(){
        inner class ViewHolder (itemView :View) : RecyclerView.ViewHolder(itemView) {
            val modifyDate= itemView.findViewById<TextView>(R.id.modifyDate)
            val noticeTitle = itemView.findViewById<TextView>(R.id.noticeTitle)

            fun setview(item : TestResponse.Data){
                modifyDate.text= item.modifyDate
                noticeTitle.text= item.noticeTitle
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_item,parent,false))
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setview(list[position])
        }
        override fun getItemCount(): Int{
            return list.size
        }
    }
}