package com.ss.smartfilterlib.singalchoice.radiogroup


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import com.ss.smartfilterlib.R


/**
 * created by Mala Ruparel ON 17/04/24
 */
class SingleLineRadioGroup : LinearLayout {

    private lateinit var radioGroup: RadioGroup
    private var backgroundDrawable: Drawable? = null
    private var orientation: Int = VERTICAL
    private var textSize: Int = -1
    private var textColor: Int = Color.BLACK
    private var textSelectorColor: ColorStateList? = null
    private var marginStart: Int = 0
    private var marginTop: Int = 0
    private var marginEnd: Int = 0
    private var marginBottom: Int = 0
    private var paddingStart: Int = 0
    private var paddingTop: Int = 0
    private var paddingEnd: Int = 0
    private var paddingBottom: Int = 0
    private var buttonText: Int = 0
    private var onCheckedChangeListener: OnCheckedChangeCallback? = null

    constructor(context: Context) : super(context) {
        setupView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SSRadioGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SSRadioGroup_radioButtonOrientation,VERTICAL)
            textSize = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonTextSize, 0)
            textColor = typedArray.getColor(R.styleable.SSRadioGroup_radioButtonTextColor, Color.BLACK)
            textSelectorColor = typedArray.getColorStateList(R.styleable.SSRadioGroup_radioButtonTextSelectorColor)
            backgroundDrawable = typedArray.getDrawable(R.styleable.SSRadioGroup_radioButtonBackground)

            marginStart = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginStart, 0)
            marginTop = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginTop, 0)
            marginEnd = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginEnd, 0)
            marginBottom = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginBottom,0)

            paddingStart = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingStart,0)
            paddingTop = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingTop, 0)
            paddingEnd = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingEnd, 0)
            paddingBottom = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingBottom,0)

            buttonText = typedArray.getResourceId(R.styleable.SSRadioGroup_radioGrouplistitem, 0)
        } finally {
            typedArray.recycle()
        }
        setupView()
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
    }

    fun setData(mData: ArrayList<RadioGroupData>, orientation: Int = VERTICAL) {
        this.orientation = orientation
        mData.forEach { data ->
            addRadioButton(
                data.name, backgroundDrawable, textSize, textColor,
                textSelectorColor, marginStart, marginTop, marginEnd, marginBottom,
                paddingStart, paddingTop, paddingEnd, paddingBottom
            )
        }
    }

    fun addRadioButton(
        buttenText: String?, backgroundDrawable: Drawable?,
        textSize: Int, textColor: Int, textSelectorColor: ColorStateList?,
        marginStart: Int, marginTop: Int, marginEnd: Int, marginBottom: Int,
        paddingStart: Int, paddingTop: Int, paddingEnd: Int, paddingBottom: Int
    ) {
        val radioButton = RadioButton(context)
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(marginStart, marginTop, marginEnd, marginBottom)
        radioButton.apply {
            text = buttenText
            //buttonDrawable = backgroundDrawable
            setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
            this.textSize = textSize.toFloat()
            setTextColor(textSelectorColor)
            setCompoundDrawablePadding(4)
            layoutParams = params
        }

        radioGroup.addView(radioButton)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            radioButton?.let {
                onCheckedChangeListener?.onCheckedChanged(it)
                Log.d("RadioButton", "${it.text} is checked")
            }
        }
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeCallback) {
        this.onCheckedChangeListener = onCheckedChangeListener
    }

    fun interface OnCheckedChangeCallback {
        fun onCheckedChanged(radioButton: RadioButton)
    }
}