package kr.co.material.basic.remainder

import androidx.recyclerview.widget.DiffUtil

class DiffUtilGirlGroupCallback(
        private val oldGirlGroupList: List<Int>,
        private val newGirlGroupList: List<Int>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldGirlGroupList.size

    override fun getNewListSize() = newGirlGroupList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = false

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false
}