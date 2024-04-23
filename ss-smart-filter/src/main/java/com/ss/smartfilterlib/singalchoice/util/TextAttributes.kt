package com.ss.smartfilterlib.singalchoice.util

import android.graphics.Color

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class TextAttributes(
    var textSize: Float = 12f,
    val textColor: Int = Color.BLACK // Default text color is black
)

data class PaddingAttributes(
    val paddingStart: Int = 0,
    val paddingTop: Int = 0,
    val paddingEnd: Int = 0,
    val paddingBottom: Int = 0
)