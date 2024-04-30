package com.ss.smartfilterlib.singlechoice.util

import RadioGroupCallback
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ss.smartfilterlib.data.RadioGroupData

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: RadioGroupCallback,
    @DrawableRes val bgSelector: Int = androidx.appcompat.R.drawable.abc_btn_radio_material,
    @ColorRes val textSelector: Int = android.R.color.black
)


