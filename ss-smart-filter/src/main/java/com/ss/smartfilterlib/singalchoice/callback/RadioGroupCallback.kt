package com.ss.smartfilterlib.singalchoice.callback
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData


/**
 * created by Mala Ruparel ON 19/04/24
 */


fun interface RadioGroupCallback{
    fun  onSingleSelection(radioGroupData: RadioGroupData)
}
fun interface ChipGroupCallback{
    fun onMultiChipCheckedChanged( checkedChipIds: List<Int>)
}
