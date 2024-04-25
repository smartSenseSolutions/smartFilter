package com.ss.smartfilterlib.singalchoice.radiogroup


import RadioGroupCallback
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singalchoice.util.PaddingAttributes
import com.ss.smartfilterlib.singalchoice.util.TextAttributes
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.Orientation



/**
 * created by Mala Ruparel ON 17/04/24
 */
class SingleLineRadioGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {


    private var textSelectorColor: ColorStateList? = null
    private var radioButtonDrawable: Drawable? = null
    private var orientation: Int = Orientation.VERTICAL

    private var textAttributes: TextAttributes? = null
    private var paddingAttributes: PaddingAttributes? = null

    private var dataFromXml: Int = 0
    private var onCheckedChangeListener: RadioGroupCallback? = null

    private lateinit var radioGroup: RadioGroup
    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView
    private var defaultPadding = resources.getDimension(R.dimen._10).toInt()
    init {
        initAttrs(attrs)
        setupView()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineRadioGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SingleLineRadioGroup_rg_sl_Orientation, Orientation.VERTICAL)
            textSelectorColor = typedArray.getColorStateList(R.styleable.SingleLineRadioGroup_rg_sl_TextSelectorColor)
            radioButtonDrawable =typedArray.getDrawable(R.styleable.SingleLineRadioGroup_rg_sl_Background)

            textAttributes = TextAttributes(
                textSize = typedArray.getDimension(R.styleable.SingleLineRadioGroup_rg_sl_TextSize, 0f),
                textColor = typedArray.getColor(R.styleable.SingleLineRadioGroup_rg_sl_TextColor, Color.BLUE),
            )

            paddingAttributes = PaddingAttributes(
                paddingStart = typedArray.getDimensionPixelSize(R.styleable.SingleLineRadioGroup_rg_sl_PaddingStart, defaultPadding),
                paddingTop = typedArray.getDimensionPixelSize(R.styleable.SingleLineRadioGroup_rg_sl_PaddingTop, defaultPadding),
                paddingEnd = typedArray.getDimensionPixelSize(R.styleable.SingleLineRadioGroup_rg_sl_PaddingEnd, defaultPadding),
                paddingBottom = typedArray.getDimensionPixelSize(R.styleable.SingleLineRadioGroup_rg_sl_PaddingBottom, defaultPadding)
            )

            dataFromXml = typedArray.getResourceId(R.styleable.SingleLineRadioGroup_rg_sl_Listitem, 0)

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
        /*val optionsArray = resources.getStringArray(dataFromXml)
        if(optionsArray.isNotEmpty()){
            optionsArray.forEach { data ->
               *//* addDynamicRadioButton(
                    data
                )*//*
            }
        }
*/
    }

    fun setData(
        mData: ArrayList<RadioGroupData>,
        orientation: Int,
        bgSelector: Int,
        textSelector: Int,
        callbacks: RadioGroupCallback
    ) {
        this.orientation = orientation
        this.radioButtonDrawable = ContextCompat.getDrawable(context, bgSelector)
        this.textSelectorColor = ContextCompat.getColorStateList(context, textSelector)
        this.onCheckedChangeListener = callbacks

        setupRadioGroup()

        mData.forEach { data ->
            addDynamicRadioButton(
                data
            )
        }
    }

    private fun addDynamicRadioButton(data: RadioGroupData) {
        val radioButton = RadioButton(context)
        setData(radioButton, data)
        setSelector(radioButton)
        setPaddingToView(radioButton)
        radioButton.id = View.generateViewId()
        radioGroup.addView(radioButton)
        radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            val rb = radioGroup.findViewById<RadioButton>(checkedId)
            onCheckedChangeListener?.onSingleLineSelected(data, radioGroup, rb, checkedId)

        }
    }

    private fun setData(
        radioButton: RadioButton,
        data: RadioGroupData
    ) {
        radioButton.text = data.name

    }




    private fun setSelector(view: RadioButton) {
        view.setTextColor(textSelectorColor)
        view.buttonDrawable = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
    }

    private fun setPaddingToView(view: View) {

        paddingAttributes?.let { paddingAttrs ->
            view.setPadding(
                paddingAttrs.paddingStart,
                paddingAttrs.paddingTop,
                paddingAttrs.paddingEnd,
                paddingAttrs.paddingBottom
            )
        }

    }
}