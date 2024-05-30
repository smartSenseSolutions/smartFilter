package com.ss.smartfilterdemo.data

import com.ss.smartfilterdemo.R
import com.ss.smartfilter.data.Data


fun mData(): ArrayList<Data> {
    return arrayListOf(
        Data(
            id = 1,
            name = "KitKat",
            description = "Smart Filter Description 1",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 2,
            name = "Lollipop",
            description = "Smart Filter Description 2",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 3,
            name = "Marshmallow",
            description = "Smart Filter Description 3",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 4,
            name = "Nougat",
            description = "Smart Filter Description 4",
            image = R.drawable.ic_documents
        ),
        Data(
            id = 5,
            name = "Oreo",
            description = "Smart Filter Description 5",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 6,
            name = "Pie",
            description = "Smart Filter Description 6",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 7,
            name = "Quince Tart",
            description = "Smart Filter Description 7",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 8,
            name = "Red Velvet Cake",
            description = "Smart Filter  8",
            image = R.drawable.ic_ac
        ),
        Data(
            id = 9,
            name = "Snow Cone ",
            description = "Smart Filter Description 9",
            image = R.drawable.ic_ac
        ),

        )
}

fun mBusType(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Ac", image = R.drawable.ic_ac),
        Data(id = 2, name = "Non Ac", image = R.drawable.ic_time),
        Data(id = 3, name = "Sleeper", image = R.drawable.ic_alert),
        Data(id = 4, name = "Seater", image = R.drawable.ic_cyclone),
        Data(id = 5, name = "Semi Sleeper", image = R.drawable.ic_smile),

        )
}

fun mSeatType(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Single Seat", image = R.drawable.ic_ac),
        Data(id = 1, name = "Double Seat", image = R.drawable.ic_smile)
    )
}

fun mOperatorType(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "5"),
        Data(id = 2, name = "4.8 & Above"),
        Data(id = 3, name = "3.5 & Above"),
        Data(id = 4, name = "3 & Above"),
    )
}

fun mBusOperatorType(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Bluecity Bus"),
        Data(id = 2, name = "Western Bus"),
        Data(id = 3, name = "Volvo Bus"),
        Data(id = 4, name = "VelloCity Bus"),
    )
}

fun mTime(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Night", image = R.drawable.ic_ac),
        Data(id = 2, name = "Morning", image = R.drawable.ic_time),
        Data(id = 3, name = "Afternoon", image = R.drawable.ic_alert),
        Data(id = 4, name = "Evening", image = R.drawable.ic_cyclone)
    )
}

fun mAmenties(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Live Tracking"),
        Data(id = 2, name = "Wifi"),
        Data(id = 3, name = "Water Bottle"),
        Data(id = 4, name = "Blanket")
    )
}

fun mDeal(): ArrayList<Data> {
    return arrayListOf(
        Data(id = 1, name = "Extra 10% Discount"),
        Data(id = 2, name = "Exculsive Offer - 5% Off"),

        )
}