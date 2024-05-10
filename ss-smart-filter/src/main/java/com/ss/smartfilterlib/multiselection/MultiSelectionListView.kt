package com.ss.smartfilterlib.multiselection

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.utils.BaseRecycleView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.adapter.MultiSelectionListAdapter
import com.ss.smartfilterlib.singleselection.SmartOrientation
import com.ss.smartfilterlib.data.RadioGroupData



/**
 * created by Mala Ruparel ON 02/05/24
 */
class MultiSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) :  BaseRecycleView<RadioGroupData>(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }

    private fun populateDataFromAttributes() {
        val mData = resources.getStringArray(dataFromXml);
        val data = mData.map { RadioGroupData(name = it) } as ArrayList<RadioGroupData>
        configureView(
            data = data,
            orientation = smartOrientation,
            checkSelector = checkSelector,
            primaryTextColor = primaryTextColor,
            onCheckedChangeListener = onMultiSelectionClicked
        )

    }

    override fun initializeView() {
        layoutManager = when (smartOrientation) {
            SmartOrientation.VERTICAL -> LinearLayoutManager(context, VERTICAL, false)
            else -> LinearLayoutManager(context, HORIZONTAL, false)
        }
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectionView)
        try {
            primaryTextColor = typedArray.getColor(R.styleable.SingleSelectionView_ss_text_selector, primaryTextColor)
            smartOrientation = typedArray.getInt(R.styleable.SingleSelectionView_ss_orientation,RecyclerView.VERTICAL)
            checkSelector = typedArray.getResourceId(
                R.styleable.SingleSelectionView_ss_check_drawable_selector,
                R.drawable.ic_check_selector
            )
            dataFromXml = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_list_item, 0)
        } finally {
            typedArray.recycle()
        }
    }

    private fun configureView(data: ArrayList<RadioGroupData>, orientation: Int, checkSelector: Int, primaryTextColor: Int, onCheckedChangeListener: ((List<RadioGroupData>) -> Unit)?) {

        updateValue(orientation, checkSelector, primaryTextColor, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }

    private fun updateValue(orientation: Int,checkSelector: Int, primaryTextColor: Int,onCheckedChangeListener: ((List<RadioGroupData>) -> Unit)?) {
        this.onMultiSelectionClicked = onCheckedChangeListener
        this.smartOrientation = orientation
        this.checkSelector = checkSelector
        this.primaryTextColor = primaryTextColor
    }
    private fun setItems(items: List<RadioGroupData>) {
        adapter = MultiSelectionListAdapter(checkSelector,viewTextSelector,selectedItemsPositions,onMultiSelectionClicked).apply { data = items }
    }



    fun setOnMultiSelection(callback: (List<RadioGroupData>) -> Unit) {
        onMultiSelectionClicked = callback
    }
}
