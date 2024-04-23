package com.ss.smartfilterlib


import android.view.ViewGroup
import com.ss.smartfilterlib.singalchoice.chipgroup.CustomChipGroup
import com.ss.smartfilterlib.singalchoice.chipgroup.SingleChipgroup
import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.ChipType
import com.ss.smartfilterlib.singalchoice.util.SingleGroupSubType
import com.ss.smartfilterlib.singalchoice.util.SingleGroupType


object SmartFilter {
    fun addSingleSelection(
        rootView: ViewGroup,
        singleGroupType: SingleGroupType,
        singleGroupSubType: SingleGroupSubType,
        chipGroupSubType: ChipType,
        orientation: Int,
        mData: ArrayList<RadioGroupData>,
        callbacks: RadioGroupCallback,
        bgSelector: Int,
        textSelector: Int
    ) {
        when (singleGroupType) {

            SingleGroupType.RADIO_GROUP -> {

                when (singleGroupSubType) {

                    SingleGroupSubType.SINGLE_LINE -> {

                        val singleLineRadioGroup = SingleLineRadioGroup(rootView.context)
                        singleLineRadioGroup.setData(
                            mData,
                            orientation,
                            bgSelector,
                            textSelector,
                            callbacks
                        )

                        rootView.addView(singleLineRadioGroup)
                    }

                    SingleGroupSubType.MULTI_LINE -> {

                        val multiLineRadioGroup = MultiLineRadioGroup(rootView.context)
                        multiLineRadioGroup.setData(
                            mData,
                            bgSelector,
                            textSelector,
                            callbacks
                        )

                        rootView.addView(multiLineRadioGroup)
                    }

                    SingleGroupSubType.ROW_ITEM -> {

                        val rowItemRadioGroup = RowItemRadioGroup(rootView.context)
                        rowItemRadioGroup.setData(
                            mData,
                            orientation,
                            bgSelector,
                            textSelector,
                            callbacks
                        )

                        rootView.addView(rowItemRadioGroup)
                    }


                }

            }

            SingleGroupType.CHIPGROUP -> {
                when (singleGroupSubType) {

                    SingleGroupSubType.SINGLE_LINE -> {

                        val singleChipgroup = SingleChipgroup(rootView.context)
                        singleChipgroup.setData(mData,ChipType.INPUT_CHIP)
                        rootView.addView(singleChipgroup)
                    }

                    SingleGroupSubType.MULTI_LINE -> {}



                    SingleGroupSubType.ROW_ITEM -> {}
                }
            }
        }
    }
}


