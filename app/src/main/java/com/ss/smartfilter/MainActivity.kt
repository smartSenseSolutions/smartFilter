package com.ss.smartfilter

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.singalchoice.util.Orientation
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.SingleSelectionType
import com.ss.smartfilterlib.singalchoice.data.mRadioGroupData

class MainActivity : ComponentActivity(), RadioGroupCallback
     {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SmartFilter.addSingleSelection(
            rootView = binding.root,
            selectionType = SingleSelectionType.ROWITEM,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = this,
            bgSelector = com.ss.smartfilterlib.R.drawable.singleline_selector,
            textSelector = com.ss.smartfilterlib.R.color.single_text_color_selector,
            )

    }



    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRowLineCallBackSelected(radioGroupData: RadioGroupData) {
        showToast(radioGroupData.name)
    }

    override fun multiLineCallBack(position: Int, text: RadioGroupData) {
        showToast(text.name)
    }

    override fun singleLineCallBack(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int) {
        showToast(radioGroupData.name)
    }


}




