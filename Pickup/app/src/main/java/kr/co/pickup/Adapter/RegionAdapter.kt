package kr.co.pickup.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.pickup.Model.Region
import kr.co.pickup.R
import kr.co.pickup.databinding.RegionItemBinding
import kr.co.pickup.databinding.RegionItemBinding.inflate

/**
 * @author user15
 * @email silmxmail@naver.com
 * @created 2021-06-20
 * @desc
 */
class RegionAdapter(private val list : MutableList<Region>,
                    private val owner : AppCompatActivity)
    :RecyclerView.Adapter<RegionAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val region : TextView =itemView.findViewById(R.id.text_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionAdapter.ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.region_item,parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RegionAdapter.ViewHolder, position: Int) {
        val listdata = list[position]
        with(holder){
            region.text =listdata.region
        }
    }
}

