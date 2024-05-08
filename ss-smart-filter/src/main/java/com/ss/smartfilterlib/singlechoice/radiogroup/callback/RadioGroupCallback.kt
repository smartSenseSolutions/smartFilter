import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 19/04/24
 */
fun interface RadioGroupCallback {
    fun onSingleSelection(radioGroupData: RadioGroupData)

}

interface MultiChoiceCallback : (List<RadioGroupData>) -> Unit {
    fun onMultiSelection(selectedItems: List<RadioGroupData>)

}


