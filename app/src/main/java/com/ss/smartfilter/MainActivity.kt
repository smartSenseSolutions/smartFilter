package com.ss.smartfilter

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.singalchoice.radiogroup.SSSmartRadioGroup


class MainActivity : ComponentActivity() , SSSmartRadioGroup.OnCheckedChangeCallback {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.RgHorizental.setOnCheckedChangeListener(this)
        binding.RgHorizental.setOnCheckedChangeListener(this)
       // val listData = resources.getStringArray(com.ss.smartfilterlib.R.array.ss_array)

    }

    override fun onCheckedChanged(radioButton: RadioButton) {
        Toast.makeText(this, radioButton.text, Toast.LENGTH_SHORT).show()
    }


}




