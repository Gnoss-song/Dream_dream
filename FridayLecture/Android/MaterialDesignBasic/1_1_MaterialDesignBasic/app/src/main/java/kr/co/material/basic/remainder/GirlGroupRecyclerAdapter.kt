package kr.co.material.basic.remainder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.material.basic.R
import kr.co.material.basic.common.GirlGroupRandomInit.getGirlGenerationName
import kr.co.material.basic.common.GirlGroupRandomInit.getTwiceName
import java.util.ArrayList

class GirlGroupRecyclerAdapter(private var currentGroupList: List<Int>, private var groupName: String) :
        RecyclerView.Adapter<GirlGroupViewHolder>() {

    fun setGroupName(groupName: String) {
        this.groupName = groupName
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.girls_group_recycler_item, parent, false)
        return GirlGroupViewHolder(view)
    }
    override fun onBindViewHolder(holder: GirlGroupViewHolder, position: Int) {
        val imageKey = currentGroupList[position]
        if (groupName.equals("twice", ignoreCase = true)) {
            holder.memberTV.text = getTwiceName(key = imageKey)
        } else {
            getGirlGenerationName(imageKey).also { holder.memberTV.text = it }
        }
        holder.memberIV.setImageResource(imageKey)
    }

    override fun getItemCount(): Int {
        return currentGroupList.size
    }

    fun applyUpdateGirlGroupList(newGroupList: List<Int>) {
        val diffCallback = DiffUtilGirlGroupCallback(currentGroupList, newGroupList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.convertOldPositionToNew(0)
        currentGroupList = newGroupList
        diffResult.dispatchUpdatesTo(this)
    }
}