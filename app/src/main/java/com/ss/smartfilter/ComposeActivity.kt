/*
package com.ss.smartfilter

*/
/**
 * created by Mala Ruparel ON 20/04/24
 *//*



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ss.smartfilterlib.com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.singalchoice.radiogroup.MultiLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.SingleLineRadioGroup
import com.ss.smartfilterlib.singalchoice.radiogroup.item.RowItemRadioGroup


class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

MyComposable()

        }
    }
}

@Composable
fun MyComposable() {
    Column {


         com.ss.smartfilterlib.SmartFilter.addSingleSelection(selectionMode = SelectionMode.SINGLE, selectionType = SingleSelectionType.ROWITEM, orientation = Orientation.VERTICAL,mData = mRadioGroupData())

    }
}

@Preview
@Composable
fun PreviewMyComposable() {
    MyComposable()
}*/
