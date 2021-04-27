package kr.co.softcampus.playground
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemAdapter
    var model = ItemModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    fun init(){
        binding.btnSelectAll.setOnClickListener(this)
        model.makeTestItems()
        adapter = ItemAdapter()
        adapter.items = model

        var linearLayoutManager =LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        binding.recyclerview.layoutManager=linearLayoutManager
        binding.recyclerview.adapter=adapter

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(v: View?) {
        model.toggleAllItemClick()
        adapter.notifyDataSetChanged()
    }

}