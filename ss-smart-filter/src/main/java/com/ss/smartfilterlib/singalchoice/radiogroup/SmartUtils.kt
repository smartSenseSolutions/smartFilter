package com.ss.smartfilterlib.singalchoice.radiogroup

import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */

enum class Orientation {
    HORIZONTAL,
    VERTICAL,
}

enum class SelectionMode {
    SINGLE,
    MULTI,
    RANGE
}

enum class SingleSelectionType {
    SINGLELINE,
    MULTIROW,
    ROWITEM
}

fun mRadioGroupData(): ArrayList<RadioGroupData> {
    return arrayListOf(
        RadioGroupData("User 1", R.drawable.add_reaction),
        RadioGroupData("User 2", R.drawable.add_reaction),
        RadioGroupData("User 3", R.drawable.add_reaction),
        RadioGroupData("User 4", R.drawable.add_reaction),
        RadioGroupData("User 5", R.drawable.add_reaction),
        RadioGroupData("User 6", R.drawable.add_reaction),
        RadioGroupData("User 7", R.drawable.add_reaction),
    )
}
