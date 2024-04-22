package com.ss.smartfilterlib.singalchoice.radiogroup

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.databinding.RowItemBinding
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RowItemRadioGroup(context: Context, attrs: AttributeSet? =null) : LinearLayout(context, attrs) {

    private var itemTextSize: Float = 0f
    private var itemTextColor: Int = 0
    private var radioGroupData: ArrayList<RadioGroupData>? = null
    private var listener: RadioGroupCallback? = null
    private lateinit var radioGroup: RadioGroup
    private var orientation: Int = VERTICAL
    private var radioButtonDrawable: Drawable? = null
    private var textSelectorColor: ColorStateList? = null

    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowItemRadioGroup)
        try {
        itemTextSize = typedArray.getDimension(R.styleable.RowItemRadioGroup_rg_ri_TextSize, resources.getDimension(R.dimen._12))
        itemTextColor = typedArray.getColor(R.styleable.RowItemRadioGroup_rg_ri_Textcolor, ContextCompat.getColor(context, R.color.black))
        textSelectorColor = typedArray.getColorStateList(R.styleable.RowItemRadioGroup_rg_ri_TextSelector)
        radioButtonDrawable = typedArray.getDrawable(R.styleable.RowItemRadioGroup_rg_ri_Background)
        orientation = typedArray.getInt(R.styleable.RowItemRadioGroup_rg_ri_Orientation, HORIZONTAL)
            } finally {
             typedArray.recycle()
         }
    }
    private fun setupView() {

        containerScrollView = ScrollView(context)
        containerHorizontalScrollView = HorizontalScrollView(context)

        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        containerScrollView.layoutParams = layoutParams
        containerHorizontalScrollView.layoutParams = layoutParams

        radioGroup = RadioGroup(context)
        populateItems()

    }

    fun setData(radioGroupData: ArrayList<RadioGroupData>,orientation: Int, bgSelector: Int, textSelector: Int, callback: RadioGroupCallback)
    {
        this.orientation = orientation
        this.radioGroupData = radioGroupData
        this.radioButtonDrawable = ContextCompat.getDrawable(context, bgSelector)
        this.textSelectorColor = ContextCompat.getColorStateList(context, textSelector)
        this.listener = callback
        setupView()
        setupRadioGroup()

    }
    private fun populateItems() {
        radioGroupData?.let {
            for (radioGroupData in it) {

                val binding = RowItemBinding.inflate(LayoutInflater.from(context), this, false)

                binding.tvName.text = radioGroupData.name
                binding.tvDes.text = radioGroupData.description
                // binding.img.drawable=radioGroupData.image
                // rowItem.background=radioButtonDrawable?.constantState?.newDrawable()?.mutate()
                binding.rtl.setOnClickListener {
                    listener?.onRowLineCallBackSelected(radioGroupData)
                }
                radioGroup.addView(binding.root)
            }

        }
    }

    private fun setupRadioGroup() {

        if (this.orientation == VERTICAL) {
            if (containerHorizontalScrollView.parent != null) {
                removeView(containerHorizontalScrollView)
            }
            if (containerScrollView.parent == null) {
                addView(containerScrollView)
            }
            radioGroup.orientation = VERTICAL
            containerScrollView.addView(radioGroup)
        } else {
            if (containerScrollView.parent != null) {
                removeView(containerScrollView)
            }
            if (containerHorizontalScrollView.parent == null) {
                addView(containerHorizontalScrollView)
            }
            radioGroup.orientation = HORIZONTAL
            containerHorizontalScrollView.addView(radioGroup)
        }
    }

}
