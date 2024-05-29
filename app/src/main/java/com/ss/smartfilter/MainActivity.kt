package com.ss.smartfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ss.smartfilter.databinding.ActivityMainBinding
import com.ss.smartfilter.screens.FilterScreen
import com.ss.smartfilterlib.SmartFilter
import com.ss.smartfilterlib.utils.toast


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilterScreen()
        }
    }

}