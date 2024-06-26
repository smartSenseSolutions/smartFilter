package com.ss.smartfilter

import com.ss.smartfilter.multiselection.MultiSelectionListView
import com.ss.smartfilter.multiselection.MultiselectionChipGroup
import com.ss.smartfilter.singleselection.SingleSelectionChipGroup
import com.ss.smartfilter.singleselection.SingleSelectionItemRadioGroup
import com.ss.smartfilter.singleselection.SingleSelectionListView
import com.ss.smartfilter.singleselection.SingleSelectionMultiLineRadioButton
import com.ss.smartfilter.singleselection.SingleSelectionRadioGroup
import com.ss.smartfilter.utils.Params


object SmartFilter {

    fun addRadioGroupSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val singleLineRadioGroup = SingleSelectionRadioGroup(param.rootView.context)
        singleLineRadioGroup.configureRadioButton(
            mData = param.mData,
            orientation = param.orientation,
            bgSelector = param.bgSelector,
            textSelector = param.textSelector,
            checkedChangedListener = param.onItemSelected
        )
        param.rootView.addView(singleLineRadioGroup)

    }

    fun addRadioMultiRawSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val multiLineRadioGroup = SingleSelectionMultiLineRadioButton(param.rootView.context)
        multiLineRadioGroup.configureRadioButton(
            mData = param.mData,
            spanCount = 2,
            bgSelector = param.bgSelector,
            textSelector = param.textSelector,
            checkedChangedListener = param.onItemSelected
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
            param.onItemSelected
        )
        param.rootView.addView(rowItemRadioGroup)

    }

    fun addChipGroupSingleSelection(singleSelectionParams: () -> Params.SingleSelection) {
        val param = singleSelectionParams().data
        val singleSelectionChipGroup = SingleSelectionChipGroup(param.rootView.context)
        singleSelectionChipGroup.configureView(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.onItemSelected
        )
        param.rootView.addView(singleSelectionChipGroup)

    }

    fun addChipGroupMultiSelection(multiselection: () -> Params.MultiSelection) {
        val param = multiselection().data
        val multiSelectionChipGroup = MultiselectionChipGroup(param.rootView.context)
        multiSelectionChipGroup.configureView(
            param.mData,
            param.chipType,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.onItemsSelected
        )
        param.rootView.addView(multiSelectionChipGroup)

    }

    fun addListViewSingleSelection(singleSelectionListView: () -> Params.SingleSelection) {
        val param = singleSelectionListView().data
        val singleSelection = SingleSelectionListView(param.rootView.context)
        singleSelection.configureView(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.onItemSelected
        )
        param.rootView.addView(singleSelection)

    }

    fun addListViewSingleSelectionSeat(singleSelectionListView: () -> Params.SingleSelection) {
        val param = singleSelectionListView().data
        val singleSelectionListView = SingleSelectionListView(param.rootView.context)
        singleSelectionListView.configureView(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.onItemSelected
        )
        param.rootView.addView(singleSelectionListView)

    }

    fun addListViewMultiSelection(singleSelectionParams: () -> Params.MultiSelection) {
        val param = singleSelectionParams().data
        val multiSelectionListView = MultiSelectionListView(param.rootView.context)
        multiSelectionListView.configureView(
            param.mData,
            param.orientation,
            param.bgSelector,
            param.textSelector,
            param.onItemsSelected
        )
        param.rootView.addView(multiSelectionListView)

    }
}




