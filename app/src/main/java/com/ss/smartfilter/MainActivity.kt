package com.ss.smartfilter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import com.ss.smartfilter.screens.FilterScreen
import com.ss.smartfilter.theme.smartFilterTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            smartFilterTheme() {
                Surface {
                    FilterScreen()
                }
            }
        }
    }

}