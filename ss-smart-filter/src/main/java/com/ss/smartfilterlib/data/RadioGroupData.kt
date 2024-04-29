package com.ss.smartfilterlib.data

import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */
data class RadioGroupData(
    val id: Int = 0,
    val name: String,
    val description: String,
    var isSelected : Boolean = false,
    val image: Int = R.drawable.ic_documents
)


fun getNamesFromRadioGroupDataList(data: List<RadioGroupData>): List<String> {
    return data.map { it.name }
}


fun mRadioGroupData(): ArrayList<RadioGroupData> {
    return arrayListOf(
        RadioGroupData(id=1, name="KitKat", description ="Smart Filter Description 1"  , image =  R.drawable.ic_documents),
        RadioGroupData(id=2,  name="Lollipop", description ="Smart Filter Description 2",image =  R.drawable.ic_documents),
        RadioGroupData(id=3,  name="Marshmallow", description ="Smart Filter Description 3", image = R.drawable.ic_documents),
        RadioGroupData(id=4,  name="Nougat", description ="Smart Filter Description 4", image = R.drawable.ic_documents),
        RadioGroupData(id=5,  name="Oreo", description ="Smart Filter Description 5",image =  R.drawable.ic_documents),
        RadioGroupData(id=6,  name="Pie", description ="Smart Filter Description 6", image = R.drawable.ic_documents),
        RadioGroupData(id=7,  name="Quince Tart", description ="Smart Filter Description 7",image =  R.drawable.ic_documents),
        RadioGroupData(id=8,  name="Red Velvet Cake", description ="Smart Filter  8", image = R.drawable.ic_documents),
        RadioGroupData(id=9,  name="Snow Cone ", description ="Smart Filter Description 9",image =  R.drawable.ic_documents),
        RadioGroupData(id=10,  name="KitKat ", description ="Smart Filter Description 10",image =  R.drawable.ic_documents),
        RadioGroupData(id=11,  name="Lollipop", description ="Smart Filter Description 11", image = R.drawable.ic_documents),
        RadioGroupData(id=12,  name="Marshmallow", description ="Smart Filter Description 12", image = R.drawable.ic_documents),
        RadioGroupData(id=13,  name="Nougat", description ="Smart Filter Description 13", image = R.drawable.ic_documents),
        RadioGroupData(id=14,  name="Oreo", description ="Smart Filter Description 14", image = R.drawable.ic_documents),
        RadioGroupData(id=15,  name="Pie", description ="Smart Filter Description 15",image =  R.drawable.ic_documents),
        RadioGroupData(id=16,  name="Quince Tart", description ="Smart Filter Description16", image = R.drawable.ic_documents),
        RadioGroupData(id=17,  name="Red Velvet Cake", description ="Smart Filter Description 17", image = R.drawable.ic_documents),
        RadioGroupData(id=18,  name="Snow Cone", description ="Smart Filter Description 17",image =  R.drawable.ic_documents),
        RadioGroupData(id=19,  name="KitKat", description ="Smart Filter Description 18",image =  R.drawable.ic_documents),
        RadioGroupData(id=20,  name="Lollipop", description ="Smart Filter Description 19", image = R.drawable.ic_documents),

    )
}
