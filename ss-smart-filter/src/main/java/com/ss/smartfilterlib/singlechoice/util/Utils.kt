package com.ss.smartfilterlib.singlechoice.util

/**
 * created by Mala Ruparel ON 19/04/24
 */


enum class SingleGroupSubType {
    SINGLE_LINE,
    MULTI_LINE,
    ROW_ITEM,
    NONE
}
object Orientation {
    const val VERTICAL = 1
    const val HORIZONTAL = 0
    const val NONE = 2
}
enum class ChipType {
    ENTRY_CHIP,
    FILTER_CHIP, //input
    CHOICE_CHIP,
    ACTION_CHIP,
    NONE
}
