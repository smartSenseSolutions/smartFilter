package com.ss.smartfilterlib.utils

/**
 * created by Mala Ruparel ON 19/04/24
 */


enum class SingleGroupSubType {
    SINGLE_LINE,
    MULTI_LINE,
    ROW_ITEM,
    CHIP_GROUP,
    NONE
}
enum class MultiGroupSubType {
    CHIP_GROUP,
    NONE
}
object Orientation {
    const val VERTICAL = 1
    const val HORIZONTAL = 0
    const val NONE = 2
}
enum class MultiChipType {
    ENTRY_CHIP,
    FILTER_CHIP, //input
    CHOICE_CHIP,
    ACTION_CHIP,
    NONE
}
enum class SingleChipType {
    ENTRY_CHIP,
    CHOICE_CHIP,
    ACTION_CHIP,
    NONE
}
