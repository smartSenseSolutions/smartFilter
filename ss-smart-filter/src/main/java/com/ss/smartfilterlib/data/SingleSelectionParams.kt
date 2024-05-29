package com.ss.smartfilterlib.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ss.smartfilterlib.R

import com.ss.smartfilterlib.utils.SingleChipType


/**
 * created by Mala Ruparel ON 08/05/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<Data>,
    val onItemSelected: (Data) -> Unit,
    @DrawableRes val bgSelector: Int = R.color.chip_bg_selector,
    @ColorRes val textSelector: Int = R.color.chip_text_selector

)
