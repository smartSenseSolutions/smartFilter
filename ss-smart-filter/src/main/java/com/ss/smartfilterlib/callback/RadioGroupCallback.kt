import android.widget.CompoundButton
import com.google.android.material.chip.Chip
import com.ss.smartfilterlib.data.RadioGroupData


/**
 * created by Mala Ruparel ON 19/04/24
 */
interface BaseEventListener : RadioGroupCallback , ChipClickListener{

}
fun interface RadioGroupCallback{

    fun  onSingleSelection(radioGroupData: RadioGroupData)
}
interface ChipClickListener  {
    fun onChipClick(chip: Chip,isChecked: Boolean)
    fun onChipCloseIconClick(chip: Chip)
    fun onChipCheckedChanged(compoundButton: CompoundButton, chip: Chip?, isChecked: Boolean)
    fun onMultiChipCheckedChanged( checkedChipIds: List<Int>)
}
