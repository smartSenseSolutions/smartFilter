package com.ss.smartfilterdemo

import android.view.ViewGroup
import com.ss.smartfilterdemo.data.mAmenties
import com.ss.smartfilterdemo.data.mBusOperatorType
import com.ss.smartfilterdemo.data.mBusType
import com.ss.smartfilterdemo.data.mData
import com.ss.smartfilterdemo.data.mDeal
import com.ss.smartfilterdemo.data.mOperatorType
import com.ss.smartfilterdemo.data.mSeatType
import com.ss.smartfilterdemo.data.mTime
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.data.MultiSelectionParams
import com.ss.smartfilter.data.SingleSelectionParams
import com.ss.smartfilter.utils.MultiChipType
import com.ss.smartfilter.utils.Orientation
import com.ss.smartfilter.utils.Params
import com.ss.smartfilter.utils.SingleChipType
import com.ss.smartfilter.R


/**
 * created by Mala Ruparel ON 23/04/24
 */
fun addRadioGroupSingleLineVertical(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.rb_selector,
            textSelector = R.color.text_selector
        )
    )

fun addRadioGroupSingleLineHorizontal(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            chipType = SingleChipType.NONE,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.rb_selector,
            textSelector = R.color.text_selector
        )
    )

fun addRadioButtonMultiRow(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
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
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mData(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.text_selector

    )
)
fun addRadioGroupRowItemVertical(rootView: ViewGroup,onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mData(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.row_item_selector,
        textSelector = R.color.text_selector

    )
)

fun addSingleSelectionChipGroupVertical(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
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
            chipType = SingleChipType.NONE,
            orientation = Orientation.HORIZONTAL,
            mData = mData(),
            onItemSelected = onItemselected,
            bgSelector = R.drawable.multiline_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addSingleSelectionListVertical(rootView: ViewGroup, onItemselected: (Data) -> Unit) =  Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mData(),
        onItemSelected = onItemselected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addMultiSelectionListVertical(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
            rootView = rootView,
            chipType = MultiChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mData(),
            onItemsSelected = onItemsSelected,
            bgSelector = R.drawable.multiline_bg_selector,
            textSelector = R.color.chip_text_selector
        )
    )
fun addMultiSelectionListBus(rootView: ViewGroup, onItemsSelected:  (List<Int>) -> Unit) =  Params.MultiSelection(
    MultiSelectionParams(
        rootView = rootView,
        chipType = MultiChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mBusType(),
        onItemsSelected = onItemsSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addSingleSelectionListSeat(rootView: ViewGroup, onItemsSelected:  (Data) -> Unit) =  Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        chipType = SingleChipType.NONE,
        orientation = Orientation.HORIZONTAL,
        mData = mSeatType(),
        onItemSelected = onItemsSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.chip_text_selector
    )
)
fun addRadioGroupSingleOperator(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mOperatorType(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.star_selector,
        textSelector = R.color.text_selector
    )
)
    fun addRadioGroupSingleBusOperator(rootView: ViewGroup,onItemSelected: (Data) -> Unit) =  Params.SingleSelection(
        SingleSelectionParams(
            rootView = rootView,
            chipType = SingleChipType.NONE,
            orientation = Orientation.VERTICAL,
            mData = mBusOperatorType(),
            onItemSelected = onItemSelected,
            bgSelector = R.drawable.check_selector,
            textSelector = R.color.text_selector
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
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mDeal(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.rb_selector,
        textSelector = R.color.text_selector
    )
)
fun addRadioMultiRowDeparture(rootView: ViewGroup, onItemSelected: (Data) -> Unit) = Params.SingleSelection(
    SingleSelectionParams(
        rootView = rootView,
        chipType = SingleChipType.NONE,
        orientation = Orientation.VERTICAL,
        mData = mTime(),
        onItemSelected = onItemSelected,
        bgSelector = R.drawable.multiline_bg_selector,
        textSelector = R.color.multiline_text_selector
    )
)
