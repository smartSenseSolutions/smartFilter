import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.chip.Chip
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 19/04/24
 */
interface BaseEventListener : RadioGroupCallback , ChipClickListener{

}
interface RadioGroupCallback{

    fun onSingleLineSelected(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int)
    fun onRowItemSelected(radioGroupData: RadioGroupData)
}
interface ChipClickListener  {
    fun onChipClick(chip: Chip,isChecked: Boolean)
    fun onChipCloseIconClick(chip: Chip)
    fun onChipCheckedChanged(compoundButton: CompoundButton, chip: Chip?, isChecked: Boolean)
}
