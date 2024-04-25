package com.ss.smartfilter

import BaseEventListener
import RadioGroupCallback
import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singalchoice.radiogroup.data.mRadioGroupData
import com.ss.smartfilterlib.singalchoice.util.ChipType
import com.ss.smartfilterlib.singalchoice.util.Orientation
import com.ss.smartfilterlib.singalchoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singalchoice.util.SingleSelectionParams

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(rootView: ViewGroup, callback: BaseEventListener) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = callback,
        bgSelector = R.drawable.singleline_selector,
        textSelector = R.color.single_text_color_selector
    )


fun addRadioGroupMultiline(rootView: ViewGroup, callback: BaseEventListener) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.MULTI_LINE,
        mData = mRadioGroupData(),
        callbacks = callback,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.multiline_text_selector
    )

fun addRadioGroupHorizontalRow(rootView: ViewGroup,callback: BaseEventListener) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = callback,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.multiline_text_selector
    )


fun addChipGroup(rootView: ViewGroup, callback: BaseEventListener) =
    SingleSelectionParams(
        rootView = rootView,
        chipType = ChipType.ACTION_CHIP,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = callback,
        bgSelector = R.color.chip_bg_default,
        textSelector = R.color.chip_text_default
    )
fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}






