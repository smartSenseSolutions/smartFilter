package com.ss.smartfilterlib.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 08/05/24
 */
data class MultiSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType = SingleGroupSubType.NONE,
    val chipType: MultiChipType = MultiChipType.NONE,
    val orientation: Int = Orientation.HORIZONTAL,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: (List<Int>) -> Unit,
    @ColorRes val bgSelector: Int = android.R.color.darker_gray,
    @ColorRes val textSelector: Int = android.R.color.black
)