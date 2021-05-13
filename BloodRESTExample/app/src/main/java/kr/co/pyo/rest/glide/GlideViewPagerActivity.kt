package kr.co.pyo.rest.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kr.co.pyo.rest.R
import kr.co.pyo.rest.okhttp_retrofit.RetrofitOkHttpManager
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlideViewPagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_with_fragment)
        viewPager = findViewById(R.id.pager)
    }

    override fun onResume() {
        super.onResume()
        val rest = RetrofitOkHttpManager.bloodRESTService
        val call: Call<ResponseBody> = rest.selectGirlImages()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val pagerAdapter = GirlsFragmentStateAdapter(
                        this@GlideViewPagerActivity
                    )
                    try {
                        val jsonData = response.body()!!.string()
                        val jsonArray = JSONArray(jsonData)
                        for (item in 0 until jsonArray.length()) {
                            val info = jsonArray.getJSONObject(item)
                            val name = info.getString("name")
                            val image = info.getString("image")
                            pagerAdapter.appendFragment(
                                    FragmentForViewPager.newInstance(
                                    name,
                                    image
                                )
                            )
                        }
                        viewPager.adapter = pagerAdapter
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
        })
    }
}