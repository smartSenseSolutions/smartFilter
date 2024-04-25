package com.ss.smartfilter


import BaseEventListener
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import com.google.android.material.chip.Chip
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter

import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData

class MainActivity : ComponentActivity(), BaseEventListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SmartFilter.addRadioGroupSingleSelection(addRadioGroupSingleLineVertical(binding.root, this))
    }

    override fun onRowItemSelected(radioGroupData: RadioGroupData) {
        showToast(radioGroupData.name, this)
    }

    override fun onChipClick(chip: Chip, isChecked: Boolean) {
        showToast(chip.text.toString(), this)
    }

    override fun onChipCloseIconClick(chip: Chip) {
        showToast(chip.text.toString(), this)
    }

    override fun onChipCheckedChanged(
        compoundButton: CompoundButton,
        chip: Chip?,
        isChecked: Boolean
    ) {
        showToast(compoundButton.text.toString(), this)
    }

    override fun onSingleLineSelected(
        radioGroupData: RadioGroupData,
        radioGroup: RadioGroup,
        radioButton: RadioButton,
        checkId: Int
    ) {
        showToast(radioGroupData.name, this)
    }
}