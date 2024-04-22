package com.ss.smartfilterlib.singalchoice.radiogroup


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
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.Orientation


/**
 * created by Mala Ruparel ON 17/04/24
 */
class SingleLineRadioGroup : LinearLayout {


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

    constructor(context: Context) : super(context) {
        setupView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineRadioGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SingleLineRadioGroup_rg_sl_Orientation, Orientation.VERTICAL)
            textSelectorColor = typedArray.getColorStateList(R.styleable.SingleLineRadioGroup_rg_sl_TextSelectorColor)
            radioButtonDrawable =typedArray.getDrawable(R.styleable.SingleLineRadioGroup_rg_sl_Background)

            textAttributes = TextAttributes(
                textSize = typedArray.getDimensionPixelSize(R.styleable.SingleLineRadioGroup_rg_sl_TextSize, 0),
                textColor = typedArray.getColor(R.styleable.SingleLineRadioGroup_rg_sl_TextColor, Color.BLACK),
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
        setupView()
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
        radioGroup.removeAllViews()
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
            onCheckedChangeListener?.singleLineCallBack(data, radioGroup, rb, checkedId)

        }
    }

    private fun setData(
        radioButton: RadioButton,
        data: RadioGroupData
    ) {
        radioButton.text = data.name
    }

    private var defaultPadding = resources.getDimension(R.dimen._10).toInt()
    data class TextAttributes(
        val textSize: Int,
        val textColor: Int,
    )
    data class PaddingAttributes(
        val paddingStart: Int,
        val paddingTop: Int,
        val paddingEnd: Int,
        val paddingBottom: Int
    )

    private fun setSelector(view: RadioButton) {
        view.setTextColor(textSelectorColor)
        view.buttonDrawable = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
    }

    private fun setPaddingToView(view: View) {
        view.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding)
    }
}