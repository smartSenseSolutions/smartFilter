package com.ss.smartfilter

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singlechoice.radiogroup.data.mRadioGroupData
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType
import com.ss.smartfilterlib.utils.MultiSelectionParams
import com.ss.smartfilterlib.utils.SingleChipSelectionParams
import com.ss.smartfilterlib.utils.SingleSelectionMultiRawParams
import com.ss.smartfilterlib.utils.SingleSelectionParams

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.singleline_rb_selector,
        textSelector = R.color.single_text_color_selector
    )
fun addRadioGroupSingleLineHorizontal(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.singleline_rb_selector,
        textSelector = R.color.single_text_color_selector
    )
fun addRadioMultiRow(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionMultiRawParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.MULTI_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.multiline_text_selector
    )
fun addRadioGroupRowItemVertical(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )
fun addRadioGroupRowItemHorizontal(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )

fun addSingleSelectionChipGroup(rootView: ViewGroup, radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleChipSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
        chipType = SingleChipType.CHOICE_CHIP,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.color.chip_bg_selector,
        textSelector = R.color.chip_text_selector
    )
fun addMultiSelectionChipGroup(rootView: ViewGroup, radioGroupCallback: (List<Int>) -> Unit) =
    MultiSelectionParams(
        rootView = rootView,
        chipType = MultiChipType.FILTER_CHIP,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.color.chip_bg_selector,
        textSelector = R.color.chip_text_selector
    )
fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}






