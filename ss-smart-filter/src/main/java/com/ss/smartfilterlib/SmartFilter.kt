package com.ss.smartfilterlib

import android.content.Context
import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.Orientation
import com.ss.smartfilterlib.singalchoice.radiogroup.RadioGroupData
import com.ss.smartfilterlib.singalchoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SelectionMode
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleSelectionType

class SmartFilter(var context: Context) {
    private val multilineRadioGroup = MultiLineRadioGroup(context)
    val singleLineRadioGroup = SingleLineRadioGroup(context)
    private val rowItemRadioGroup = RowItemRadioGroup(context)

    fun addSingleSelection(
        selectionMode: SelectionMode,
        selectionType: SingleSelectionType,
        mData: ArrayList<RadioGroupData>,
        orientation: Int
    ) {
        when (selectionMode) {
            SelectionMode.SINGLE -> {
                when (selectionType) {
                    SingleSelectionType.SINGLELINE -> {
                        singleLineRadioGroup.setData(mData, orientation)
                    }
                    SingleSelectionType.MULTIROW -> {
                        multilineRadioGroup.setData(mData)
                    }
                    SingleSelectionType.ROWITEM -> {
                        rowItemRadioGroup.setUsers(mData)
                    }
                }

            }
            else -> {  }
        }
    }
}
