package com.ss.smartfilterlib

import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.SingleChipSelectionParams
import com.ss.smartfilterlib.multiselection.MultiselectionChipGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionChipGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionMultiLineRadioButton
import com.ss.smartfilterlib.singleselection.SingleSelectionItemRadioGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionRadioGroup
import com.ss.smartfilterlib.utils.Params


object SmartFilter {
    fun addRadioGroupSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val singleLineRadioGroup = SingleSelectionRadioGroup(param.rootView.context)
        singleLineRadioGroup.configureRadioButton(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(singleLineRadioGroup)

    }
    fun addRadioMultiRawSingleSelection(singleSelectionMultiRawParams: () -> Params.SingleSelectionMultiRaw) {
        val param = singleSelectionMultiRawParams().data
        val multiLineRadioGroup = SingleSelectionMultiLineRadioButton(param.rootView.context)
        multiLineRadioGroup.configureRadioButton(
            param.mData,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(multiLineRadioGroup)

    }

    fun addRadioRawItemSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val rowItemRadioGroup = SingleSelectionItemRadioGroup(param.rootView.context)
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
        val singleChipgroup = SingleSelectionChipGroup(param.rootView.context)
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
    fun addChipGroupMultiSelection(multiselectionChipGroup: () -> MultiSelectionParams) {
        val param = multiselectionChipGroup()
        val multiselectionChipGroup = MultiselectionChipGroup(param.rootView.context)
        multiselectionChipGroup.setData(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(multiselectionChipGroup)

    }

}




