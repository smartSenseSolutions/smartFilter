package com.ss.smartfilterlib.singalchoice.util

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
}

object Orientation {
    const val VERTICAL = 1
    const val HORIZONTAL = 0
}
enum class ChipType {
    ASSIST_CHIP,
    FILTER_CHIP,
    INPUT_CHIP,
    SUGGESTION_CHIP
}
