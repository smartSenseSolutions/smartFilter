package com.ss.smartfilterlib.data

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.R
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 08/05/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType,
    val chipType: SingleChipType,
    val orientation: Int,
    val mData: ArrayList<Data>,
    val onItemSelected: (Data) -> Unit,
    @DrawableRes val bgSelector: Int =android.R.color.darker_gray,
    @ColorRes val textSelector: Int = android.R.color.black

)
/*
@DrawableRes val bgSelector: Int = com.ss.smartfilterlib.R.drawable.multiline_default,
@ColorRes val textSelector: Int = com.ss.smartfilterlib.R.color.black*/
/*
@ColorRes val bgSelector: Int = android.R.color.darker_gray,
@ColorRes val textSelector: Int = android.R.color.black*/
