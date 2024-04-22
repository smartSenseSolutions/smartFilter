package com.ss.smartfilterlib


import android.view.ViewGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.util.SingleSelectionType


object SmartFilter {
    fun addSingleSelection(rootView: ViewGroup, selectionType: SingleSelectionType, orientation: Int, mData: ArrayList<RadioGroupData>, callbacks: RadioGroupCallback, bgSelector: Int, textSelector: Int)
    {
        when (selectionType) {

            SingleSelectionType.SINGLELINE -> {

                val singleLineRadioGroup = SingleLineRadioGroup(rootView.context)
                singleLineRadioGroup.setData(
                    mData,
                    orientation,
                    bgSelector,
                    textSelector,
                    callbacks)

                rootView.addView(singleLineRadioGroup)
            }

            SingleSelectionType.MULTILINE -> {

                val multiLineRadioGroup = MultiLineRadioGroup(rootView.context)
                multiLineRadioGroup.setData(
                    mData,
                    bgSelector,
                    textSelector,
                    callbacks)

                rootView.addView(multiLineRadioGroup)
            }

            SingleSelectionType.ROWITEM -> {

                val rowItemRadioGroup = RowItemRadioGroup(rootView.context)
                rowItemRadioGroup.setData(
                    mData,
                    orientation,
                    bgSelector,
                    textSelector,
                    callbacks)

                rootView.addView(rowItemRadioGroup)
            }
        }

    }
}
