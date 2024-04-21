package com.ss.smartfilterlib.singalchoice.radiogroup

import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * created by Mala Ruparel ON 21/04/24
 */
interface RadioGroupCallback {
    fun singleLineCallBack(radioGroupData: RadioGroupData,radioGroup: RadioGroup,radioButton: RadioButton,checkId : Int)
    fun multiLineCallBack(position: Int, text: RadioGroupData)
    fun onRowLineCallBackSelected(radioGroupData: RadioGroupData)
}