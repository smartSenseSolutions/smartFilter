package com.ss.smartfilterlib.singalchoice.util

import RadioGroupCallback
import android.view.ViewGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType= SingleGroupSubType.NONE,
    val chipType: ChipType = ChipType.NONE,
    val orientation: Int = Orientation.VERTICAL,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: RadioGroupCallback,
    val bgSelector: Int = DEFAULT_BG_SELECTOR,
    val textSelector: Int = DEFAULT_TEXT_SELECTOR
){
    companion object {
        const val DEFAULT_BG_SELECTOR = 0
        const val DEFAULT_TEXT_SELECTOR = 0
    }
}
