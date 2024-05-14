package com.ss.smartfilter

import android.view.ViewGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.SingleSelectionParams
import com.ss.smartfilterlib.data.mData
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation
import com.ss.smartfilterlib.utils.Params
import com.ss.smartfilterlib.utils.SingleChipType
import com.ss.smartfilterlib.utils.SingleGroupSubType

/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.singleline_rb_selector,
            textSelector = R.color.single_text_color_selector
        )
    )

fun addRadioGroupSingleLineHorizontal(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.singleline_rb_selector,
            textSelector = R.color.single_text_color_selector
        )
    )

fun addRadioMultiRow(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.MULTI_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.multiline_bg_selector,
            textSelector = R.color.multiline_text_selector
        )
    )


fun addRadioGroupRowItemHorizontal(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mData(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )
)
fun addRadioGroupRowItemVertical(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.ROW_ITEM,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mData(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.single_text_color_selector

    )
)

fun addSingleSelectionChipGroupVertical(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
            chipType = SingleChipType.ENTRY_CHIP,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.multiline_text_selector
        )
    )
fun addSingleSelectionChipGroupHorizontal(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
            chipType = SingleChipType.ENTRY_CHIP,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addMultiSelectionChipGroupVertical(rootView: ViewGroup, onItemsSelected: (List<Int>) -> Unit) = Params.MultiSelection(
        MultiSelectionParams(
            rootView = rootView,
            chipType = MultiChipType.FILTER_CHIP,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemsSelected = onItemsSelected,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )


fun addMultiSelectionChipGroupHorizontal(rootView: ViewGroup, onItemsSelected: (List<Int>) -> Unit) =Params.MultiSelection(
        MultiSelectionParams(
            rootView = rootView,
            chipType = MultiChipType.FILTER_CHIP,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemsSelected = onItemsSelected,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addSingleSelectionListHorizontal(rootView: ViewGroup, onItemselected: (Data) -> Unit) =  Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemSelected = onItemselected,
            bgSelector = R.drawable.ic_check_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addSingleSelectionListVertical(rootView: ViewGroup, onItemselected: (Data) -> Unit) =  Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mData(),
        onItemSelected = onItemselected,
        bgSelector = R.drawable.ic_check_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addMultiSelectionListVertical(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = MultiChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemsSelected = onItemsSelected,
            bgSelector =  R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addMultiSelectionListHorizontal(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = MultiChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mData(),
        onItemsSelected = onItemsSelected,
        bgSelector = R.color.chip_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
