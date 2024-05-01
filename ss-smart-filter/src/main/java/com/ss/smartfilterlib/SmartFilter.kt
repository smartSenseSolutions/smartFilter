package com.ss.smartfilterlib

import com.ss.smartfilterlib.singalchoice.chipgroup.SingleChipgroup
import com.ss.smartfilterlib.singlechoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.utils.MultiSelectionParams
import com.ss.smartfilterlib.utils.SingleChipSelectionParams
import com.ss.smartfilterlib.utils.SingleSelectionMultiRawParams
import com.ss.smartfilterlib.utils.SingleSelectionParams


object SmartFilter {
    fun addRadioGroupSingleSelection(singleSelectionParams: () -> SingleSelectionParams) {
        val param = singleSelectionParams()
        val singleLineRadioGroup = SingleLineRadioGroup(param.rootView.context)
        singleLineRadioGroup.configureRadioButton(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(singleLineRadioGroup)

    }
    fun addRadioMultiRawSingleSelection(singleSelectionMultiRawParams: () -> SingleSelectionMultiRawParams) {
        val param = singleSelectionMultiRawParams()
        val multiLineRadioGroup = MultiLineRadioGroup(param.rootView.context)
        multiLineRadioGroup.configureRadioButton(
            param.mData,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(multiLineRadioGroup)

    }

    fun addRadioRawItemSingleSelection(singleSelectionParams: () -> SingleSelectionParams) {
        val param = singleSelectionParams()
        val rowItemRadioGroup = RowItemRadioGroup(param.rootView.context)
        rowItemRadioGroup.configureRadioButton(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(rowItemRadioGroup)

    }

    fun addChipGroupSingleSelection(singleChipSelectionParams: () -> SingleChipSelectionParams) {
        val param = singleChipSelectionParams()
        val singleChipgroup = SingleChipgroup(param.rootView.context)
        singleChipgroup.setData(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(singleChipgroup)

    }

}




