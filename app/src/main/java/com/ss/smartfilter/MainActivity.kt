package com.ss.smartfilter

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.Orientation
import com.ss.smartfilterlib.singalchoice.radiogroup.RadioGroupData
import com.ss.smartfilterlib.singalchoice.radiogroup.RowItemRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SelectionMode
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleSelectionType
import com.ss.smartfilterlib.singalchoice.radiogroup.mRadioGroupData


class MainActivity : ComponentActivity(), SingleLineRadioGroup.OnCheckedChangeCallback,RowItemRadioGroup.OnItemSelectedListener ,MultiLineRadioGroup.CallbackChoseListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       // val singleLineRadioGroup = SingleLineRadioGroup(this)
        //singleLineRadioGroup.setData(mRadioGroupData(),Orientation.VERTICAL)

       // val smartFilter = SmartFilter(this)
      // smartFilter.addSingleSelection(selectionMode = SelectionMode.SINGLE, selectionType = SingleSelectionType.ROWITEM, orientation = Orientation.VERTICAL,mData = mRadioGroupData())



        binding.RgVerticle.setData( mRadioGroupData(),orientation = 1)
        binding.RgHorizental.setData( mRadioGroupData(),orientation = 0)
        binding.RGMultiline.setUsers( mRadioGroupData())
        binding.RGRowItem.setUsers(mRadioGroupData())

        binding.RgVerticle.setOnCheckedChangeListener(this)
        binding.RgHorizental.setOnCheckedChangeListener(this)




    }

    override fun onCheckedChanged(radioButton: RadioButton) {
       showToast(radioButton.text.toString())
    }
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(radioGroupData: RadioGroupData) {
        showToast(radioGroupData.name)
    }

    override fun onChose(position: Int, text: RadioGroupData) {
        showToast(text.name)
    }

}




