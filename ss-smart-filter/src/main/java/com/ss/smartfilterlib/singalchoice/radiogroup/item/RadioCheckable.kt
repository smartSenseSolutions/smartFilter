package com.ss.smartfilterlib.singalchoice.radiogroup.item

import android.view.View
import android.widget.Checkable

/**
 * created by Mala Ruparel ON 17/04/24
 */
interface RadioCheckable : Checkable {
    fun addOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener)
    fun removeOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener)

    interface OnCheckedChangeListener {
        fun onCheckedChanged(buttonView: View, isChecked: Boolean)
    }
}