package com.ss.smartfilterlib.callback
import com.ss.smartfilterlib.data.RadioGroupData


/**
 * created by Mala Ruparel ON 19/04/24
 */


fun interface RadioGroupCallback{
    fun  onSingleSelection(radioGroupData: RadioGroupData)
}
fun interface ChipGroupCallback{
    fun onMultiChipCheckedChanged( checkedChipIds: List<Int>)
}

