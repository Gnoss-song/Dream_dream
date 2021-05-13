package kr.co.ssong.practice04

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
        val recyclerView =findViewById<RecyclerView>(R.id.recyclerview)




        // GET방식 //
        BoxOfficeService.invoke()
            .getDailyBoxOffice("f18a699fff048166f68223bead920212","20210510").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recyclerView.adapter = MovieAdapter(it.boxOfficeResult.dailyBoxOfficeList)
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

    inner class MovieAdapter(private val list: List<DailyBoxOfficeResponse.BoxOfficeResult.DailyBoxOffice>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
        inner class ViewHolder (itemView :View) : RecyclerView.ViewHolder(itemView) {
            val moviename= itemView.findViewById<TextView>(R.id.Movicename)
            val openDt = itemView.findViewById<TextView>(R.id.openDt)

            fun setview(item : DailyBoxOfficeResponse.BoxOfficeResult.DailyBoxOffice){
                moviename.text = item.movieNm
                openDt.text = item.openDt
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setview(list[position])
        }
        override fun getItemCount(): Int{
            return list.size
        }
    }
}