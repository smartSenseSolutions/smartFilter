package com.ss.smartfilter

import android.view.ViewGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.data.SingleChipSelectionParams
import com.ss.smartfilterlib.data.SingleSelectionMultiRawParams
import com.ss.smartfilterlib.data.SingleSelectionParams
import com.ss.smartfilterlib.data.mRadioGroupData
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation
import com.ss.smartfilterlib.utils.Params
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(
    rootView: ViewGroup,
    radioGroupCallback: (RadioGroupData) -> Unit
) =
    Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.drawable.singleline_rb_selector,
            textSelector = R.color.single_text_color_selector
        )
    )

fun addRadioGroupSingleLineHorizontal(
    rootView: ViewGroup,
    radioGroupCallback: (RadioGroupData) -> Unit
) =
    Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.HORIZONTAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.drawable.singleline_rb_selector,
            textSelector = R.color.single_text_color_selector
        )
    )

fun addRadioMultiRow(rootView: ViewGroup, radioGroupCallback: (RadioGroupData) -> Unit) =
    Params.SingleSelectionMultiRaw(
        SingleSelectionMultiRawParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.MULTI_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.drawable.multiline_bg_selector,
            textSelector = R.color.multiline_text_selector
        )
    )


fun addRadioGroupRowItemHorizontal(
    rootView: ViewGroup,
    radioGroupCallback: (RadioGroupData) -> Unit
) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )
)
fun addRadioGroupRowItemVertical(
    rootView: ViewGroup,
    radioGroupCallback: (RadioGroupData) -> Unit
) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = radioGroupCallback,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )
)

fun addSingleSelectionChipGroupVertical(rootView: ViewGroup, radioGroupCallback: (RadioGroupData) -> Unit) =
    Params.SingleChipSelection(
        SingleChipSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
            chipType = SingleChipType.ENTRY_CHIP,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addSingleSelectionChipGroupHorizontal(rootView: ViewGroup, radioGroupCallback: (RadioGroupData) -> Unit) =
    Params.SingleChipSelection(
        SingleChipSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
            chipType = SingleChipType.ENTRY_CHIP,
            orientation = Orientation.HORIZONTAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addMultiSelectionChipGroupVertical(rootView: ViewGroup, radioGroupCallback: (List<Int>) -> Unit) =
    Params.MultiChipSelection(
        MultiSelectionParams(
            rootView = rootView,
            chipType = MultiChipType.FILTER_CHIP,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )


fun addMultiSelectionChipGroupHorizontal(rootView: ViewGroup, radioGroupCallback: (List<Int>) -> Unit) =
    Params.MultiChipSelection(
        MultiSelectionParams(
            rootView = rootView,
            chipType = MultiChipType.FILTER_CHIP,
            orientation = Orientation.HORIZONTAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addSingleSelectionListVertical(rootView: ViewGroup, radioGroupCallback: (RadioGroupData) -> Unit) =
    Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mRadioGroupData(),
            callbacks = radioGroupCallback,
            bgSelector = R.drawable.ic_check_selector,
            textSelector = R.color.chip_text_selector
        )
    )


