package com.ss.smartfilter.utils

import com.ss.smartfilter.R


/**
 * created by Mala Ruparel ON 23/04/24
 */
data class TextAttributes(
    var textSize: Float = 18f,
    val fontFamily: Int = R.font.pop_medium,
    val textStyle: Int = 0,
    val textAlignment: Int = 0,
    val textAllCaps: Boolean = true,
    val textMaxLines: Int = 1,
    val textEllipsize: Int = 0
)
data class PaddingAttributes(
    val paddingStart: Int = 30,
    val paddingTop: Int = 30,
    val paddingEnd: Int = 30,
    val paddingBottom: Int = 30
)