package com.ss.smartfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter

import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            SmartFilter.addSingleSelection {
                addRadioGroupSingleLineVertical(binding.root) { radioGroupData ->
                    onSingleLineSelected(radioGroupData)
                }
            }
    }


     private fun onSingleLineSelected( radioGroupData: RadioGroupData) {
        showToast(radioGroupData.name, this)
    }
}