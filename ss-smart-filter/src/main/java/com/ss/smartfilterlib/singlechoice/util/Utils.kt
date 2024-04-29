package com.ss.smartfilterlib.singlechoice.util

/**
 * created by Mala Ruparel ON 19/04/24
 */


enum class SingleGroupSubType {
    SINGLE_LINE,
    MULTI_LINE,
    ROW_ITEM
}

enum class SingleGroupType {
    RADIO_GROUP,
    CHIPGROUP,
    NONE
}

object Orientation {
    const val VERTICAL = 1
    const val HORIZONTAL = 0
    const val NONE = 2
}
enum class ChipType {
    ASSIST_CHIP,
    FILTER_CHIP,
    INPUT_CHIP,
    SUGGESTION_CHIP,
    NONE
}
