package com.ss.smartfilterdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import com.ss.smartfilterdemo.screens.FilterScreen
import com.ss.smartfilterdemo.theme.smartFilterTheme


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