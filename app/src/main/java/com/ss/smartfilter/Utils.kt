package com.ss.smartfilter

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singalchoice.radiogroup.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.radiogroup.data.getNames
import com.ss.smartfilterlib.singalchoice.radiogroup.data.mRadioGroupData
import com.ss.smartfilterlib.singalchoice.util.ChipType
import com.ss.smartfilterlib.singalchoice.util.Orientation
import com.ss.smartfilterlib.singalchoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singalchoice.util.SingleGroupType
import com.ss.smartfilterlib.singalchoice.util.SingleSelectionParams

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVerticle(rooView: ViewGroup, context: Context)
    = SingleSelectionParams(
        rootView = rooView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = ChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = context as RadioGroupCallback,
        bgSelector = R.drawable.singleline_selector,
        textSelector = R.color.single_text_color_selector)

fun addRadioGroupSingleLineHorizontal(rooView: ViewGroup, context: Context) =
     SingleSelectionParams(
        rootView = rooView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = ChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = context as RadioGroupCallback,
        bgSelector = R.drawable.singleline_selector,
        textSelector = R.color.single_text_color_selector)


fun addRadioGroupMultiline(rooView: ViewGroup, context: Context) =
     SingleSelectionParams(
        rootView = rooView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.MULTI_LINE,
        chipType = ChipType.NONE,
        orientation = Orientation.NONE,
        mData =mRadioGroupData(),
        callbacks = context as RadioGroupCallback,
        bgSelector =R.drawable.multiline_selector,
        textSelector =R.color.multiline_text_selector)

fun addRadioGroupHorizontalRow(rooView: ViewGroup, context: Context) =
     SingleSelectionParams(
        rootView = rooView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = ChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = context as RadioGroupCallback,
        bgSelector = R.drawable.multiline_selector,
        textSelector =R.color.multiline_text_selector)

fun addRadioGroupVerticalRow(rooView: ViewGroup, context: Context)=
     SingleSelectionParams(
        rootView = rooView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = ChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = context as RadioGroupCallback,
        bgSelector = R.drawable.multiline_selector,
        textSelector = R.color.multiline_text_selector)

fun showToast(message: String,context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

