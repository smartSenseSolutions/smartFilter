package com.ss.smartfilterlib.singalchoice.data

import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RadioGroupData(
    val id: Int = 0,
    val name: String = "Smart Filter",
    val description: String = "Smart Filter Description",
    val image: Int = R.drawable.ic_documents

)

fun getNames(data: List<RadioGroupData>): Array<String> {
    return data.map { it.name }.toTypedArray()
}

fun getImages(data: List<RadioGroupData>): IntArray {
    return data.map { it.image }.toIntArray()
}

fun mRadioGroupData(): ArrayList<RadioGroupData> {
    return arrayListOf(
        RadioGroupData(1, "KitKat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(2, "Lollipop", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(3, "Marshmallow", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(4, "Nougat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(5, "Oreo", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(6, "Pie", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(7, "Quince Tart", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(8, "Red Velvet Cake", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(9, "Snow Cone", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(1, "KitKat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(2, "Lollipop", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(3, "Marshmallow", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(4, "Nougat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(5, "Oreo", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(6, "Pie", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(7, "Quince Tart", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(8, "Red Velvet Cake", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(9, "Snow Cone", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(1, "KitKat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(2, "Lollipop", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(3, "Marshmallow", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(4, "Nougat", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(5, "Oreo", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(6, "Pie", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(7, "Quince Tart", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(8, "Red Velvet Cake", "Smart Filter Description", R.drawable.ic_documents),
        RadioGroupData(9, "Snow Cone", "Smart Filter Description", R.drawable.ic_documents),
    )
}
