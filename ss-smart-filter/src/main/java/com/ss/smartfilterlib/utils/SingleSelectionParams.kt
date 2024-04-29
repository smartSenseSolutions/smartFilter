package com.ss.smartfilterlib.utils

import BaseEventListener
import android.view.ViewGroup
import com.ss.smartfilterlib.data.RadioGroupData

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class SingleSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType = SingleGroupSubType.NONE,
    val chipType: SingleChipType = SingleChipType.NONE,
    val orientation: Int = Orientation.VERTICAL,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: BaseEventListener,
    val bgSelector: Int = DEFAULT_BG_SELECTOR,
    val textSelector: Int = DEFAULT_TEXT_SELECTOR
){
    companion object {
        const val DEFAULT_BG_SELECTOR = 0
        const val DEFAULT_TEXT_SELECTOR = 0
    }
}
data class MultiSelectionParams(
    val rootView: ViewGroup,
    val singleGroupSubType: SingleGroupSubType = SingleGroupSubType.NONE,
    val chipType: MultiChipType = MultiChipType.NONE,
    val orientation: Int = Orientation.VERTICAL,
    val mData: ArrayList<RadioGroupData>,
    val callbacks: BaseEventListener,
    val bgSelector: Int = DEFAULT_BG_SELECTOR,
    val textSelector: Int = DEFAULT_TEXT_SELECTOR
){
    companion object {
        const val DEFAULT_BG_SELECTOR = 0
        const val DEFAULT_TEXT_SELECTOR = 0
    }
}