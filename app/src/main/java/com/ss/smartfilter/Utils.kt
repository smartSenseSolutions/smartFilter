package com.ss.smartfilter

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singlechoice.radiogroup.data.mRadioGroupData
import com.ss.smartfilterlib.singlechoice.util.ChipType
import com.ss.smartfilterlib.singlechoice.util.Orientation
import com.ss.smartfilterlib.singlechoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singlechoice.util.SingleGroupType
import com.ss.smartfilterlib.singlechoice.util.SingleSelectionParams

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = ChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.singleline_rb_selector,
        textSelector = R.color.single_text_color_selector
    )



fun addRadioGroupMultiRow(rootView: ViewGroup,radioGroupCallback: (RadioGroupData) -> Unit) =
    SingleSelectionParams(
        rootView = rootView,
        singleGroupType = SingleGroupType.RADIO_GROUP,
        singleGroupSubType = SingleGroupSubType.MULTI_LINE,
        chipType = ChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.multiline_selector,
        textSelector = R.color.multiline_text_selector
    )

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}






