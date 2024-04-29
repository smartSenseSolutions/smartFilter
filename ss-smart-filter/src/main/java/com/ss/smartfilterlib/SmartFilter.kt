package com.ss.smartfilterlib


import com.ss.smartfilterlib.singlechoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singlechoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singlechoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singlechoice.util.SingleSelectionParams


object SmartFilter {

    fun addSingleSelection( createParams: () -> SingleSelectionParams) {
        val params = createParams()
                when (params.singleGroupSubType) {
                    SingleGroupSubType.SINGLE_LINE -> {
                        val singleLineRadioGroup = SingleLineRadioGroup(params.rootView.context)
                        singleLineRadioGroup.setData(
                            params.mData,
                            params.orientation,
                            params.bgSelector,
                            params.textSelector,
                            params.callbacks
                        )
                        params.rootView.addView(singleLineRadioGroup)
                    }
                    SingleGroupSubType.MULTI_LINE -> {
                        val multiLineRadioGroup = MultiLineRadioGroup(params.rootView.context)
                        multiLineRadioGroup.setData(
                            params.mData,
                            params.bgSelector,
                            params.textSelector,
                            params.callbacks
                        )
                        params.rootView.addView(multiLineRadioGroup)
                    }
                    SingleGroupSubType.ROW_ITEM -> {
                        val rowItemRadioGroup = RowItemRadioGroup(params.rootView.context)
                        rowItemRadioGroup.setData(
                            params.mData,
                            params.orientation,
                            params.bgSelector,
                            params.textSelector,
                            params.callbacks
                        )
                        params.rootView.addView(rowItemRadioGroup)
                    }
                }
            }


}

