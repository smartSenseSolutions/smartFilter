package com.ss.smartfilter.data

import com.ss.smartfilter.R


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

