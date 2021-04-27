package kr.co.softcampus.playground

/**
 * @author Gnoss
 * @email silmxmail@naver.com
 * @created 2021-04-27
 * @desc
 */
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.softcampus.playground.databinding.ItemViewBinding

// 이해를 돕기위해 불피요 한 추상화는 쓰지 않습니다.
class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: ItemModel? = ItemModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        items?.items?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            holder.bind(items?.items?.get(position))
        }
    }


    companion object {
        class ItemHolder(var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root),
            CompoundButton.OnCheckedChangeListener {
            var item: ItemModel.ItemEntity? = null

            init {
                binding.checkBox.setOnCheckedChangeListener(this)
            }

            fun bind(item: ItemModel.ItemEntity?) {
                item?.let {
                    this.item = item
                    binding.checkBox.isChecked = it.isChecked
                    binding.textTitle.text = it.title
                    binding.textContents.text = it.contents
                }
            }


            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) { //체크 리스너 등록
                item?.let {
                    it.isChecked = it.isChecked.not()
                    Log.d("checkState", "${it.isChecked}")
                }
            }
        }
    }
}