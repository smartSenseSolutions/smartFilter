package com.ss.smartfilterlib.utils

import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.SingleSelectionParams


sealed class Params {

    data class SingleSelection(val data: SingleSelectionParams) : Params()

    data class MultiSelection(val data: MultiSelectionParams) : Params()


}




