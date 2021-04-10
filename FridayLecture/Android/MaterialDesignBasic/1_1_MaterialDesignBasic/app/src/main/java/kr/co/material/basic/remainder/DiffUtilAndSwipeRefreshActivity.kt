package kr.co.material.basic.remainder

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kr.co.material.basic.R
import kr.co.material.basic.common.GirlGroupRandomInit
import java.util.*

class DiffUtilAndSwipeRefreshActivity : AppCompatActivity() {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var girlsAdapter: GirlGroupRecyclerAdapter
    private val random = Random(System.currentTimeMillis())

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util_and_swipe_refresh)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        recyclerView = findViewById(R.id.diffRecyclerView)

        girlsAdapter = GirlGroupRecyclerAdapter(GirlGroupRandomInit.shuffleTwice() , "twice")

        val manager = LinearLayoutManager(this)

        with(recyclerView){
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(this@DiffUtilAndSwipeRefreshActivity, LinearLayoutManager.VERTICAL))
            adapter = girlsAdapter
        }
        swipeRefresh.setOnRefreshListener {
            Thread {
                if (random.nextBoolean()) {
                    girlsAdapter.setGroupName("generations")
                    girlsAdapter.applyUpdateGirlGroupList(GirlGroupRandomInit.shuffleGirlsGeneration() )
                } else {
                    girlsAdapter.setGroupName("twice")
                    girlsAdapter.applyUpdateGirlGroupList(GirlGroupRandomInit.shuffleTwice() )
                }
                runOnUiThread{
                    swipeRefresh.isRefreshing = false
                }
            }.start()
        }
        swipeRefresh.setColorSchemeColors(resources.getColor(R.color.colorAccent, null))
    }
}