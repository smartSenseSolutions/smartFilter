package com.ss.smartfilterlib.singalchoice.radiogroup

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RowItemRadioGroup(context: Context, attrs: AttributeSet? =null) : LinearLayout(context, attrs) {

    private var itemTextSize: Float = 0f
    private var itemTextColor: Int = 0
    private var radioGroupData: ArrayList<RadioGroupData>? = null
    private var listener: OnItemSelectedListener? = null
    private lateinit var radioGroup: RadioGroup
    private var orientation: Int = VERTICAL
    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowItemRadioGroup)
        itemTextSize = typedArray.getDimension(R.styleable.RowItemRadioGroup_row_text_size, resources.getDimension(R.dimen._12))
        itemTextColor = typedArray.getColor(R.styleable.RowItemRadioGroup_row_text_color, ContextCompat.getColor(context, R.color.black))

        orientation = typedArray.getInt(R.styleable.RowItemRadioGroup_row_orientation, HORIZONTAL)
        typedArray.recycle()
    }
    private fun setupView() {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        if (orientation == VERTICAL) {
            val scrollView = ScrollView(context)
            scrollView.layoutParams = layoutParams
            radioGroup = RadioGroup(context)
            radioGroup.orientation = RadioGroup.VERTICAL
            scrollView.addView(radioGroup)
            addView(scrollView)
        } else {
            val horizontalScrollView = HorizontalScrollView(context)
            horizontalScrollView.layoutParams = layoutParams
            radioGroup = RadioGroup(context)
            radioGroup.orientation = RadioGroup.HORIZONTAL
            horizontalScrollView.addView(radioGroup)
            addView(horizontalScrollView)
        }

        populateItems()
    }
    fun setUsers(radioGroupData: ArrayList<RadioGroupData>?) {
        this.radioGroupData = radioGroupData
        setupView()
    }

    private fun populateItems() {
        radioGroupData?.let {
            for (radioGroupData in it) {
                val rowItem = CustomRowItem(context)
                rowItem.setText(radioGroupData.name)
                rowItem.setTextSize(itemTextSize)
                rowItem.setTextColor(itemTextColor)
                rowItem.setImage(radioGroupData.image)
                rowItem.setOnClickListener {
                    listener?.onItemSelected(radioGroupData)
                }
                radioGroup.addView(rowItem)
            }

        }
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    interface OnItemSelectedListener {
        fun onItemSelected(radioGroupData: RadioGroupData)
    }

}
