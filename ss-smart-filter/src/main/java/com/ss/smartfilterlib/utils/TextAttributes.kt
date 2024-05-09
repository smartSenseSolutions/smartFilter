package com.ss.smartfilterlib.utils

import android.graphics.Color
import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 23/04/24
 */
data class TextAttributes(
    var textSize: Float = 12f,
    val fontFamily: Int = R.font.pop_medium,
    val textColor: Int = Color.CYAN,
    val textStyle: Int = 0,
    val textAlignment: Int = 0,
    val textAllCaps: Boolean = true,
    val textMaxLines: Int = 1,
    val textEllipsize: Int = 0
)
data class PaddingAttributes(
    val paddingStart: Int = 20,
    val paddingTop: Int = 20,
    val paddingEnd: Int = 20,
    val paddingBottom: Int = 20
)