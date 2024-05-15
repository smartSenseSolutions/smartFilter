package com.ss.smartfilterlib.multiselection

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.adapter.MultiSelectionListAdapter
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.singleselection.SmartOrientation
import com.ss.smartfilterlib.utils.BaseRecycleView


/**
 * created by Mala Ruparel ON 02/05/24
 */
class MultiSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) :  BaseRecycleView(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }



    override fun initializeView() {
        layoutManager = when (smartOrientation) {
            SmartOrientation.VERTICAL -> LinearLayoutManager(context, VERTICAL, false)
            else -> LinearLayoutManager(context, HORIZONTAL, false)
        }
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectionView)
        with(typedArray) {
            try {
                viewTextSelector = getColorStateList(R.styleable.SingleSelectionView_ss_text_selector) ?: setDefaultTextColor()
                smartOrientation = getInt(R.styleable.SingleSelectionView_ss_orientation, VERTICAL)
                checkSelector = getResourceId(R.styleable.SingleSelectionView_ss_checked_selector,R.drawable.ic_check_selector)
                dataFromXml =  getResourceId(R.styleable.SingleSelectionView_ss_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            mData.forEach {
                val data = Data(name = it)
                mList.add(data)
            }
            setItems(mList)
        }

    }

    fun configureView(data: ArrayList<Data>, orientation: Int, checkSelector: Int, primaryTextColor: Int, onCheckedChangeListener: ((List<Int>) -> Unit)?) {

        updateValue(orientation, checkSelector, primaryTextColor, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }

    private fun updateValue(orientation: Int,checkSelector: Int, primaryTextColor: Int,onCheckedChangeListener: ((List<Int>) -> Unit)?) {
        this.onMultiSelectionClicked = onCheckedChangeListener
        this.smartOrientation = orientation
        this.checkSelector = checkSelector
        this.viewTextSelector = primaryTextColor.let { ContextCompat.getColorStateList(context, it) }
    }
    private fun setItems(items: List<Data>) {
        adapter = MultiSelectionListAdapter(checkSelector,viewTextSelector,selectedItemsPositions,onMultiSelectionClicked).apply { data = items }
    }


    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }
    fun setOnMultiSelection(callback: (List<Int>) -> Unit) {
        onMultiSelectionClicked = callback
    }
}
