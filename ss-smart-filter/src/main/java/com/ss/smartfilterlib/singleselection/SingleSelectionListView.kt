package com.ss.smartfilterlib.singleselection

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.adapter.SingleSelectionListAdapter
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.utils.BaseRecycleView

typealias SmartOrientation = com.ss.smartfilterlib.utils.Orientation
/**
 * SingleSelectionListView is a custom view that allows single selection from a list of options.
 * The options are presented in a RecyclerView.
 *
 * @property smartOrientation The orientation of the RecyclerView, can be either vertical or horizontal.
 * @property viewTextSelector The ColorStateList for the text color of the list items.
 * @property checkSelector The resource ID for the drawable to use when an item is checked.
 * @property dataFromXml The resource ID of the string array containing the list items.
 * @property onSingleSelectionClicked The listener for click events of the list items.
 *
 * @constructor Creates a new SingleSelectionListView with the given context, attrs, and defStyle.
 */
class SingleSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) : BaseRecycleView(context, attrs, defStyle){


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
                viewTextSelector =getColorStateList(R.styleable.SingleSelectionView_ss_text_selector) ?: setDefaultTextColor()
                smartOrientation = getInt(R.styleable.SingleSelectionView_ss_orientation, RecyclerView.VERTICAL)
                checkSelector = getResourceId(R.styleable.SingleSelectionView_ss_checked_selector,R.drawable.ic_check_selector)
                dataFromXml = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            initializeView()
            resources.getStringArray(dataFromXml).forEach {
                mList.add(Data(name = it))
            }
            setItems(mList)
        }
    }

    fun configureView(data: ArrayList<Data>, orientation: Int, checkSelector: Int, textSelector: Int, onCheckedChangeListener: ((Data) -> Unit)?){
        updateValue(orientation, checkSelector, textSelector, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }
    private fun updateValue(orientation: Int,checkSelector: Int,textSelector: Int,checkedChangedListener: ((Data) -> Unit)?) {
        this.onSingleSelectionClicked = checkedChangedListener
        this.smartOrientation = orientation
        this.checkSelector = checkSelector
        this.viewTextSelector =textSelector.let { ContextCompat.getColorStateList(context, it) }
    }

    private fun setItems(items: List<Data>) {
        adapter = SingleSelectionListAdapter(checkSelector,viewTextSelector,onSingleSelectionClicked).apply { data = items }
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }
    fun setOnSingleSelection(onItemSelected: (Data) -> Unit) {
        onSingleSelectionClicked = onItemSelected
    }
}
