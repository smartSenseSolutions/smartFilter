package com.ss.smartfilterlib.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 08/05/24
 */
data class SingleChipSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: (RadioGroupData) -> Unit,
    @ColorRes val bgSelector: Int = android.R.color.darker_gray,
    @ColorRes val textSelector: Int = android.R.color.black

)