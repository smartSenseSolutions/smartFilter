package com.ss.smartfilterlib.utils

import androidx.recyclerview.widget.DiffUtil
import com.ss.smartfilterlib.data.Data

/**
 * created by Mala Ruparel ON 22/04/24
 */
 class SingleChangeDiffUtil(private val oldList: ArrayList<Data>, private val newList: ArrayList<Data>
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
