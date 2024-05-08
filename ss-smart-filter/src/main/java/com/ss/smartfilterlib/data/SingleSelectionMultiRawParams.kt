package com.ss.smartfilterlib.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 08/05/24
 */
data class SingleSelectionMultiRawParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: (RadioGroupData) -> Unit,
    @DrawableRes val bgSelector: Int = com.ss.smartfilterlib.R.drawable.multiline_default,
    @ColorRes val textSelector: Int = com.ss.smartfilterlib.R.color.black

)