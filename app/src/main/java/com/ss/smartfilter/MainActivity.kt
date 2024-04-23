package com.ss.smartfilter

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.singalchoice.radiogroup.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData


class MainActivity : ComponentActivity(), RadioGroupCallback {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SmartFilter.addSingleSelection(
            addRadioGroupVerticalRow(binding.root, this)
        )

    }
    override fun onRowItemCallBack(radioGroupData: RadioGroupData) {
        showToast(radioGroupData.name,this)
    }

    override fun multiLineCallBack(position: Int, text: RadioGroupData) {
        showToast(text.name,this)
    }

    override fun singleLineCallBack(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int) {
        showToast(radioGroupData.name,this)
    }
}




