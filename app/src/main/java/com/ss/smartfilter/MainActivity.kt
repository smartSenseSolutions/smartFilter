package com.ss.smartfilter

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.ss.smartfilter.databinding.ActivityMainBinding

//https://stackoverflow.com/questions/44798354/android-nestedscrollview-how-to-make-it-horizontal
class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val optionsArray = resources.getStringArray(com.ss.smartfilterlib.R.array.ss_array)
        binding.horizentalRadioGroup.addRadioButtons(optionsArray)



    }


}




