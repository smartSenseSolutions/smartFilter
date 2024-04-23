package com.ss.smartfilterlib.singalchoice.util

import android.view.ViewGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData

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
    val bgSelector: Int,
    val textSelector: Int
)