package com.ss.smartfilterlib.utils

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.R
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData


/**
 * created by Mala Ruparel ON 23/04/24
 */

data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: (RadioGroupData) -> Unit,
    @DrawableRes val bgSelector: Int = R.drawable.abc_btn_radio_material,
    @ColorRes val textSelector: Int = android.R.color.black

)
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