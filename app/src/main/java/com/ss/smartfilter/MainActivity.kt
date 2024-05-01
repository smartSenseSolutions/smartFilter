package com.ss.smartfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.data.RadioGroupData


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SmartFilter.addChipGroupMultiSelection {
            addMultiSelectionChipGroup(binding.root) { radioGroupData ->
                showMessage(radioGroupData) } }
    }

    private fun showMessage(data: RadioGroupData) {
        showToast(data.name, this)
    }
    private fun showMessage(data: List<Int>) {
       showToast("Checked IDs: ${data.joinToString(", ")}", this)
    }
}