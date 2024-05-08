package com.ss.smartfilterlib.singlechoice.radiogroup


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.callback.RadioGroupCallback
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.databinding.RowItemBinding
import com.ss.smartfilterlib.utils.Orientation

/**
 * created by Mala Ruparel ON 19/04/24
 */
class RowItemRadioGroup(context: Context, attrs: AttributeSet? =null) : LinearLayout(context, attrs) {

    private var textSelectorColor: ColorStateList? = null
    private var radioButtonDrawable: Drawable? = null
    private var orientation: Int = Orientation.VERTICAL
    private var onCheckedChangeListener: RadioGroupCallback? = null

    private lateinit var radioGroup: RadioGroup
    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView

    init {
        initAttrs(attrs)
        setupView()

    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowItemRadioGroup)
        try {


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


    fun configureRadioButton(
        mData: ArrayList<RadioGroupData>?,
        orientation: Int,
        bgSelector: Int,
        textSelector: Int,
        callbacks: RadioGroupCallback
    ) {
        this.orientation = orientation
        this.radioButtonDrawable = bgSelector.let { ContextCompat.getDrawable(context, it) }
        this.textSelectorColor = textSelector.let { ContextCompat.getColorStateList(context, it) }
        this.onCheckedChangeListener = callbacks
        setupRadioGroup()
        mData?.forEach { data ->
            addRadioButtonView(
                data
            )
        }
    }

    private fun addRadioButtonView(data: RadioGroupData?) {
            val binding = RowItemBinding.inflate(LayoutInflater.from(context), this, false)
            binding.apply {
                tvName.text = data?.name
                tvDes.text = data?.description
            }
            applySelector(binding.rtl)
            radioGroup.addView(binding.root)
            binding.rtl.setOnClickListener {
                data?.let { mData -> onCheckedChangeListener?.onSingleSelection(mData) }
            }

    }

    private fun applySelector(rtl: RelativeLayout) {
        rtl.background = radioButtonDrawable
    }
}
