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
class SSSmartRadioGroup @JvmOverloads constructor(
    mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {

    private lateinit var radioGroup: RadioGroup
    private var backgroundDrawable: Drawable? = null
    private var orientation: Int = ORIENTATION_VERTICAL
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
    private var DataArray: Int = 0

    private var onCheckedChangeListener: OnCheckedChangeCallback? = null


    init {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SSRadioGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SSRadioGroup_radioButtonOrientation,ORIENTATION_VERTICAL)
            textSize = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonTextSize, -1)
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

            DataArray = typedArray.getResourceId(R.styleable.SSRadioGroup_radioGrouplistitem, 0)
        } finally {
            typedArray.recycle()
        }
        setupView()
    }

    private fun setupView() {
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)

        if (orientation == ORIENTATION_VERTICAL) {
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

        setData()
    }

    private fun setData() {
        if (DataArray != 0) {
            var options = resources.getStringArray(DataArray);
            options.forEach { text ->
                addRadioButton(
                    text, backgroundDrawable, textSize, textColor,
                    textSelectorColor, marginStart, marginTop, marginEnd, marginBottom,
                    paddingStart, paddingTop, paddingEnd, paddingBottom
                )
            }
        }
    }


    fun addRadioButton(
        text: String?, backgroundDrawable: Drawable?,
        textSize: Int, textColor: Int, textSelectorColor: ColorStateList?,
        marginStart: Int, marginTop: Int, marginEnd: Int, marginBottom: Int,
        paddingStart: Int, paddingTop: Int, paddingEnd: Int, paddingBottom: Int
    ) {
        val radioButton = RadioButton(context)
        radioButton.text = text
        radioButton.buttonDrawable = backgroundDrawable
        radioButton.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
        radioButton.textSize = textSize.toFloat()
        radioButton.setTextColor(textSelectorColor)
        //radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        radioButton.setCompoundDrawablePadding(4);

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(marginStart, marginTop, marginEnd, marginBottom)
        radioButton.layoutParams = params
        radioGroup.addView(radioButton)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            radioButton?.let {
                onCheckedChangeListener?.onCheckedChanged(it)
                Log.d("RadioButton", "${it.text} is checked")
            }
        }


    }


    companion object {
        const val ORIENTATION_VERTICAL = 0
        const val ORIENTATION_HORIZONTAL = 1
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeCallback) {
        this.onCheckedChangeListener = onCheckedChangeListener
    }

    fun interface OnCheckedChangeCallback {
        fun onCheckedChanged(radioButton: RadioButton)
    }
}