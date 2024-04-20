package com.ss.smartfilterlib.singalchoice.radiogroup

import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RadioGroupData(
    val name: String = "Smart Filter",
    val image: Int = R.drawable.add_reaction

)

fun getNames(data: List<RadioGroupData>): Array<String> {
    return data.map { it.name }.toTypedArray()
}

fun getImages(data: List<RadioGroupData>): IntArray {
    return data.map { it.image }.toIntArray()
}
