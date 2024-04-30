import android.widget.CompoundButton
import com.google.android.material.chip.Chip


/**
 * created by Mala Ruparel ON 19/04/24
 */
interface BaseEventListener : RadioGroupCallback , ChipClickListener{

}

interface ChipClickListener  {
    fun onChipClick(chip: Chip,isChecked: Boolean)
    fun onChipCloseIconClick(chip: Chip)
    fun onChipCheckedChanged(compoundButton: CompoundButton, chip: Chip?, isChecked: Boolean)
}
