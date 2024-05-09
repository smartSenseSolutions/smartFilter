package com.ss.smartfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.utils.Params
import com.ss.smartfilterlib.utils.toast


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        SmartFilter.addRadioRawItemSingleSelection {
            addRadioGroupRowItemVertical(binding.root) { radioGroupData ->
                toast("name: ${radioGroupData.name} ")  } }

    }

}