package com.ss.smartfilterlib.singlechoice.radiogroup

import RadioGroupCallback
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.ss.smartfilterlib.singlechoice.util.PaddingAttributes
import com.ss.smartfilterlib.singlechoice.util.TextAttributes
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RowItemRadioGroup(context: Context, attrs: AttributeSet? =null) : LinearLayout(context, attrs) {

    private var textAttributes: TextAttributes? = null
    private var paddingAttributes: PaddingAttributes? = null

    private var itemTextSize: Float = 0f
    private var itemTextColor: Int = 0
    private var radioGroupData: ArrayList<RadioGroupData>? = null
    private var listener: RadioGroupCallback? = null
    private lateinit var radioGroup: RadioGroup
    private var orientation: Int = VERTICAL
    private var radioButtonDrawable: Drawable? = null
    private var textSelectorColor: ColorStateList? = ContextCompat.getColorStateList(context, R.color.black)

    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView

    init {
        initAttrs(attrs)
        setupView()
    }
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowItemRadioGroup)
        try {
            textAttributes = TextAttributes(
                textSize = typedArray.getFloat(R.styleable.RowItemRadioGroup_rg_ri_TextSize,12f),
                textColor = typedArray.getColor(R.styleable.RowItemRadioGroup_rg_ri_Textcolor, Color.BLACK),
            )


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


    }

    fun setData(radioGroupData: ArrayList<RadioGroupData>, orientation: Int, bgSelector: Int?, textSelector: Int?, callback: RadioGroupCallback)
    {
        this.orientation = orientation
        this.radioGroupData = radioGroupData
        this.radioButtonDrawable = bgSelector?.let { ContextCompat.getDrawable(context, it) }
         ///   ?: ContextCompat.getDrawable(context, R.drawable.default_radio_button_drawable)
        this.textSelectorColor = textSelector?.let { ContextCompat.getColorStateList(context, it) }
            ?: ContextCompat.getColorStateList(context, R.color.black)
        this.listener = callback

        setupRadioGroup()
        addDynamicRadioButton()

    }
    private fun addDynamicRadioButton() {
        radioGroupData?.let {
            for (item in it) {
                val binding = RowItemBinding.inflate(LayoutInflater.from(context), this, false)
                binding.tvName.text = item.name
                binding.tvDes.text = item.description
                binding.rtl.setOnClickListener {
                    listener?.onSingleSelection(item)
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