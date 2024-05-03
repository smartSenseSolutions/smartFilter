package com.ss.smartfilterlib.singlechoice.util

import android.graphics.Color
import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class TextAttributes(
    var textSize: Float = 18f,
    val fontFamily: Int = R.font.pop_medium,
    val textColor: Int = Color.CYAN,
    val textStyle: Int = 0,
    val textAlignment: Int = 0,
    val textAllCaps: Boolean = true,
    val textMaxLines: Int = 1,
    val textEllipsize: Int = 0
)

data class PaddingAttributes(
    var paddingStart: Int = 16,
    val paddingTop: Int = 16,
    val paddingEnd: Int = 16,
    val paddingBottom: Int = 16
)