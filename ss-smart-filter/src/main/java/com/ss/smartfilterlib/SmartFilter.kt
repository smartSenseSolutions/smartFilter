package com.ss.smartfilterlib


import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singalchoice.util.SingleGroupType
import com.ss.smartfilterlib.singalchoice.util.SingleSelectionParams


object SmartFilter {
    fun addSingleSelection(params: SingleSelectionParams) {
        when (params.singleGroupType) {

            SingleGroupType.RADIO_GROUP -> {

                when (params.singleGroupSubType) {

                    SingleGroupSubType.SINGLE_LINE -> {

                        val singleLineRadioGroup =   SingleLineRadioGroup(params.rootView.context)
                        singleLineRadioGroup .setData(
                                params.mData,
                                params.orientation,
                                params.bgSelector,
                                params.textSelector,
                                params.callbacks
                            )

                        params.rootView.addView(singleLineRadioGroup)
                    }

                    SingleGroupSubType.MULTI_LINE -> {

                        val multiLineRadioGroup = MultiLineRadioGroup( params.rootView.context)
                        multiLineRadioGroup.setData(
                            params.mData,
                            params.bgSelector,
                            params.textSelector,
                            params.callbacks
                        )

                        params.rootView.addView(multiLineRadioGroup)
                    }

                    SingleGroupSubType.ROW_ITEM -> {

                        val rowItemRadioGroup = RowItemRadioGroup( params.rootView.context)
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

            SingleGroupType.CHIPGROUP -> {
                TODO()
            }

            SingleGroupType.NONE -> TODO()
        }
    }
}


