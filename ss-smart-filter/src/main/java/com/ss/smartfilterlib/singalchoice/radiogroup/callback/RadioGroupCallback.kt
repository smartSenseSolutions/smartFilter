import android.widget.RadioButton
import android.widget.RadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 19/04/24
 */
interface RadioGroupCallback {

    fun onSingleLineSelected(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int)


    fun onMultiLineSelected(position: Int, radioGroupData: RadioGroupData)


    fun onRowItemSelected(radioGroupData: RadioGroupData)
}
