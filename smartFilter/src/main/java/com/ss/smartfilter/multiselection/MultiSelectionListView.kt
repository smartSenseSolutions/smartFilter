package com.ss.smartfilter.multiselection

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ss.smartfilter.adapter.MultiSelectionListAdapter
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.singleselection.SmartOrientation
import com.ss.smartfilter.utils.BaseRecycleView
import com.ss.smartfilter.R


/**
 * MultiSelectionListView is a custom view that allows multiple selection from a list of options.
 * The options are presented in a RecyclerView.
 *
 * @property smartOrientation The orientation of the RecyclerView, can be either vertical or horizontal.
 * @property viewTextSelector The ColorStateList for the text color of the list items.
 * @property checkSelector The resource ID for the drawable to use when an item is checked.
 * @property dataFromXml The resource ID of the string array containing the list items.
 * @property onMultiSelectionClicked The listener for checked change events of the list items.
 *
 * @constructor Creates a new MultiSelectionListView with the given context, attrs, and defStyle.
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
                viewBgSelector = getDrawable(R.styleable.SingleSelectionView_ss_checked_selector) ?: setDefaultDrawable()
                dataFromXml =  getResourceId(R.styleable.SingleSelectionView_ss_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            resources.getStringArray(dataFromXml).forEach {
                mList.add(Data(name = it))
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
        this.viewBgSelector =  checkSelector.let { ContextCompat.getDrawable(context, it) }
        this.viewTextSelector = primaryTextColor.let { ContextCompat.getColorStateList(context, it) }
    }
    private fun setItems(items: List<Data>) {
        adapter = MultiSelectionListAdapter(viewBgSelector,viewTextSelector,selectedItemsPositions,onMultiSelectionClicked).apply { data = items }
    }
    private fun setDefaultDrawable() : Drawable?{
        return  ContextCompat.getDrawable(context,R.drawable.multiline_bg_selector,)
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.multiline_text_selector)
    }
    fun setOnMultiSelection(callback: (List<Int>) -> Unit) {
        onMultiSelectionClicked = callback
    }
}
