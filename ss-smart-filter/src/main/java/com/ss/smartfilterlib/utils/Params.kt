package com.ss.smartfilterlib.utils

import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.SingleChipSelectionParams
import com.ss.smartfilterlib.data.SingleSelectionMultiRawParams
import com.ss.smartfilterlib.data.SingleSelectionParams


sealed class Params {

    data class SingleSelection(val data: SingleSelectionParams) : Params()
    data class SingleSelectionMultiRaw(val data: SingleSelectionMultiRawParams) : Params()

    data class MultiSelection(val data: MultiSelectionParams) : Params()
    data class SingleChipSelection(val data: SingleChipSelectionParams) : Params()
}




