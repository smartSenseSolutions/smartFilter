package com.ss.smartfilter.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ss.smartfilter.utils.MultiChipType
import com.ss.smartfilter.utils.Orientation


/**
 * created by Mala Ruparel ON 08/05/24
 */
data class MultiSelectionParams(
    val rootView: ViewGroup,
    val chipType: MultiChipType = MultiChipType.NONE,
    val orientation: Int = Orientation.HORIZONTAL,
    val mData: ArrayList<Data>,
    val onItemsSelected: (List<Int>) -> Unit,
    @DrawableRes val bgSelector: Int = android.R.color.darker_gray,
    @ColorRes val textSelector: Int = android.R.color.black
)