package com.ss.smartfilterlib.singlechoice.util

import RadioGroupCallback
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupType: SingleGroupType,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: ChipType,
    val orientation: Int,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: RadioGroupCallback,
    @DrawableRes val bgSelector: Int = R.drawable.multiline_default,
    @ColorRes val textSelector: Int = android.R.color.black
)


