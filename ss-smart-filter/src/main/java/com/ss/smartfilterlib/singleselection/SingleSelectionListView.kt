package com.ss.smartfilterlib.singleselection

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.adapter.SingleSelectionListAdapter
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.utils.BaseRecycleView

typealias SmartOrientation = com.ss.smartfilterlib.utils.Orientation
/**
 * created by Mala Ruparel ON 02/05/24
 */
class SingleSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) : BaseRecycleView<RadioGroupData>(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()

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
                checkSelector = getResourceId(R.styleable.SingleSelectionView_ss_check_drawable_selector,R.drawable.ic_check_selector)
                dataFromXml = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }


    fun configureView(data: ArrayList<RadioGroupData>, orientation: Int, checkSelector: Int, textSelector: Int, onCheckedChangeListener: ((RadioGroupData) -> Unit)?){
        updateValue(orientation, checkSelector, textSelector, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }
    private fun updateValue(orientation: Int,checkSelector: Int,textSelector: Int,checkedChangedListener: ((RadioGroupData) -> Unit)?) {
        this.onSingleSelectionClicked = checkedChangedListener
        this.smartOrientation = orientation
        this.checkSelector = checkSelector
        this.viewTextSelector =textSelector.let { ContextCompat.getColorStateList(context, it) }
    }

    private fun setItems(items: List<RadioGroupData>) {
        adapter = SingleSelectionListAdapter(checkSelector,primaryTextColor,onSingleSelectionClicked).apply { data = items }
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }
    fun setOnSingleSelection(callback: (RadioGroupData) -> Unit) {
        onSingleSelectionClicked = callback
    }
}
