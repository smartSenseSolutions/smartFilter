package com.ss.smartfilter.utils

import com.ss.smartfilter.data.MultiSelectionParams
import com.ss.smartfilter.data.SingleSelectionParams


sealed class Params {

    data class SingleSelection(val data: SingleSelectionParams) : Params()

    data class MultiSelection(val data: MultiSelectionParams) : Params()


}




