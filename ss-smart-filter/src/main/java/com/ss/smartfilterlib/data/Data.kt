package com.ss.smartfilterlib.data


import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */
data class Data(
    val id: Int = 0,
    val name: String,
    val description: String = "",
    var isSelected: Boolean = false,
    val image: Int = R.drawable.ic_documents
)

