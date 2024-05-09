package com.ss.smartfilterlib.utils

import androidx.recyclerview.widget.DiffUtil
import com.ss.smartfilterlib.data.RadioGroupData

/**
 * created by Mala Ruparel ON 22/04/24
 */
 class SingleChangeDiffUtil(private val oldList: ArrayList<RadioGroupData>, private val newList: ArrayList<RadioGroupData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }
    override fun getNewListSize(): Int {
        return newList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }
}
