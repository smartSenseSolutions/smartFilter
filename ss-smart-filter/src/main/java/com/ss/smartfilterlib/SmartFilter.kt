package com.ss.smartfilterlib

import com.ss.smartfilterlib.multiselection.MultiselectionChipGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionChipGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionItemRadioGroup
import com.ss.smartfilterlib.singleselection.SingleSelectionListView
import com.ss.smartfilterlib.singleselection.SingleSelectionMultiLineRadioButton
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

    fun addChipGroupSingleSelection(singleChipSelectionParams: () -> Params.SingleChipSelection) {
        val param = singleChipSelectionParams().data
        val singleChipgroup = SingleSelectionChipGroup(param.rootView.context)
        singleChipgroup.configureView(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(singleChipgroup)

    }
    fun addChipGroupMultiSelection(multiselectionChipGroup: () -> Params.MultiChipSelection) {
        val param = multiselectionChipGroup().data
        val multiselectionChipGroup = MultiselectionChipGroup(param.rootView.context)
        multiselectionChipGroup.configureView(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(multiselectionChipGroup)

    }
    fun addListViewSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val singleSelectionListView = SingleSelectionListView(param.rootView.context)
        singleSelectionListView.configureView(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.callbacks
        )
        param.rootView.addView(singleSelectionListView)

    }
}




