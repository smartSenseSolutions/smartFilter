package com.ss.smartfilter

import android.view.ViewGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.data.MultiSelectionParams
import com.ss.smartfilterlib.data.SingleSelectionParams
import com.ss.smartfilterlib.data.mAmenties
import com.ss.smartfilterlib.data.mBusOperatorType
import com.ss.smartfilterlib.data.mBusType
import com.ss.smartfilterlib.data.mData
import com.ss.smartfilterlib.data.mDeal
import com.ss.smartfilterlib.data.mOperatorType
import com.ss.smartfilterlib.data.mSeatType
import com.ss.smartfilterlib.data.mTime
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
            bgSelector = R.drawable.rb_selector,
            textSelector = R.color.text_color_selector
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
            bgSelector = R.drawable.rb_selector,
            textSelector = R.color.text_color_selector
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
        textSelector = R.color.text_color_selector

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
        textSelector = R.color.text_color_selector

    )
)

fun addSingleSelectionChipGroupVertical(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.CHIP_GROUP,
            chipType = SingleChipType.ACTION_CHIP,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.color.chip_bg_selector,
            textSelector = R.color.chip_text_selector
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
            chipType = MultiChipType.ACTION_CHIP,
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
            textSelector = R.color.text_color_selector
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
        textSelector = R.color.text_color_selector
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
            bgSelector = R.drawable.ic_check_selector,
            textSelector = R.color.text_color_selector
        )
    )
fun addMultiSelectionListBus(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = MultiChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mBusType(),
        onItemsSelected = onItemsSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addMultiSelectionListSeat(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = MultiChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mSeatType(),
        onItemsSelected = onItemsSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addRadioGroupSingleOperator(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mOperatorType(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.star_selector,
        textSelector = R.color.text_color_selector
    )
)
    fun addRadioGroupSingleBusOperator(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mBusOperatorType(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.check_selector,
            textSelector = R.color.text_color_selector
        )
)
fun addMultiSelectionChipGroupAmenties(rootView: ViewGroup, onItemsSelected: (List<Int>) -> Unit) = Params.MultiSelection(
    MultiSelectionParams(
        rootView = rootView,
        chipType = MultiChipType.ENTRY_CHIP,
        orientation = Orientation.VERTICAL,
        mData = mAmenties(),
        onItemsSelected = onItemsSelected,
        bgSelector = R.color.chip_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)

fun addRadioGroupSingleLineHorizontalDeal(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mDeal(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.rb_selector,
        textSelector = R.color.text_color_selector
    )
)
fun addRadioMultiRowDeparture(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.MULTI_LINE,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mTime(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.multiline_text_selector
    )
)
