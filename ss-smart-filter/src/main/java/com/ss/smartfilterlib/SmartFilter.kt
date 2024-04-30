package com.ss.smartfilterlib

import com.ss.smartfilterlib.multiselection.chipgroup.MultiselectionChipGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singlechoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singlechoice.util.SingleSelectionParams
import com.ss.smartfilterlib.utils.MultiSelectionParams


object SmartFilter {
    fun addSingleSelection( params: () -> SingleSelectionParams) {
                    val param = params()
                    when (param.singleGroupSubType) {
                     SingleGroupSubType.SINGLE_LINE -> {
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
                    SingleGroupSubType.MULTI_LINE -> {
                        val multiLineRadioGroup = MultiLineRadioGroup(param.rootView.context)
                        multiLineRadioGroup.configureRadioButton(
                            param.mData,
                            param.bgSelector,
                            param.textSelector,
                            param.callbacks
                        )
                        param.rootView.addView(multiLineRadioGroup)
                    }
                    SingleGroupSubType.ROW_ITEM -> {
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

                        SingleGroupSubType.NONE -> TODO()
                    }
            }


    }
    fun addChipGroupMultiSelection(params: MultiSelectionParams) {
        val multiselectionChipGroup = MultiselectionChipGroup(params.rootView.context)
        multiselectionChipGroup.setData(
            params.mData,
            params.chipType,
            params.orientation,
            params.bgSelector,
            params.textSelector,
            params.callbacks
        )
        params.rootView.addView(multiselectionChipGroup)

    }


