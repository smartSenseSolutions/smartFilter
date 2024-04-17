package com.ss.smartfilterlib.singalchoice.radiogroup


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.SelectionMode


/**
 * created by Mala Ruparel ON 17/04/24
 */
class CombineRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var orientation: Int = ORIENTATION_VERTICAL
    var radioGroup: RadioGroup
    private var backgroundSelector: Int = android.R.drawable.btn_default
    private var textSelector: Int = android.R.drawable.btn_radio
    private var selectionMode: SelectionMode = SelectionMode.SINGLE
    var optionsArray: Int = 0
    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SSRadioGroup)
            orientation = typedArray.getInt(R.styleable.SSRadioGroup_scrollOrientation, ORIENTATION_HORIZONTAL)

          /*  val textColor = typedArray.getColor(R.styleable.SSRadioGroup_textColor, 0)
            val backgroundColor = typedArray.getColor(R.styleable.SSRadioGroup_backgroundColor, 0)
            val textSelector = typedArray.getResourceId(R.styleable.SSRadioGroup_textSelector, 0)
            val backgroundSelector = typedArray.getResourceId(R.styleable.SSRadioGroup_bgSelector, 0)*/


            val buttonText = typedArray.getString(R.styleable.SSRadioGroup_radioButtonText)

            val backgroundDrawable =  typedArray.getDrawable(R.styleable.SSRadioGroup_radioButtonBackground)
            val textSelectorDrawable =  typedArray.getDrawable(R.styleable.SSRadioGroup_radioButtonTextSelector)
            val textSize =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonTextSize, -1)
            val textColor =  typedArray.getColor(R.styleable.SSRadioGroup_radioButtonTextColor, Color.BLACK)
            val textSelectorColor = typedArray.getColor(R.styleable.SSRadioGroup_radioButtonTextSelectorColor, Color.BLACK)

            val marginStart =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginStart, 0)
            val marginTop =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginTop, 0)
            val marginEnd =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginEnd, 0)
            val marginBottom =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonMarginBottom, 0)

            val paddingStart =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingStart, 0)
            val paddingTop =  typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingTop, 0)
            val paddingEnd =   typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingEnd, 0)
            val paddingBottom = typedArray.getDimensionPixelSize(R.styleable.SSRadioGroup_radioButtonPaddingBottom, 0)

            optionsArray = typedArray.getResourceId(R.styleable.SSRadioGroup_radioButtonOptions,0)
            typedArray.recycle()

        }



        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

       // setBackgroundColor(backgroundColor)

        if (orientation == ORIENTATION_VERTICAL) {
            val scrollView = ScrollView(context)
            scrollView.layoutParams = layoutParams

            radioGroup = RadioGroup(context)
            radioGroup.orientation = RadioGroup.VERTICAL
            scrollView.addView(radioGroup)
            addView(scrollView)
        } else {
            val horizontalScrollView = HorizontalScrollView(context)
            horizontalScrollView.layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
            radioGroup = RadioGroup(context)
            radioGroup.orientation = RadioGroup.HORIZONTAL
            horizontalScrollView.addView(radioGroup)
            addView(horizontalScrollView)
        }
        if (optionsArray != 0) {
            var options = getResources().getStringArray(optionsArray);
            addRadioButtons(options)

        }

    }


    fun addRadioButtons(options: Array<String>) {
        options.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it
            radioGroup.addView(radioButton)
        }
    }
    fun addRadioButton(
        text: String?, backgroundDrawable: Drawable?, textSelectorDrawable: Drawable?,
        textSize: Int, textColor: Int, textSelectorColor: Int,
        marginStart: Int, marginTop: Int, marginEnd: Int, marginBottom: Int,
        paddingStart: Int, paddingTop: Int, paddingEnd: Int, paddingBottom: Int
    ) {
        val radioButton = RadioButton(context)
        radioButton.text = text
        radioButton.background = backgroundDrawable
        radioButton.buttonDrawable = null // remove default radio button icon
        radioButton.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
        radioButton.textSize = textSize.toFloat()
        radioButton.setTextColor(textColor)

        radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        radioButton.highlightColor = textSelectorColor
        radioGroup.addView(radioButton)
       /* val params = radioButton.layoutParams as LayoutParams
        params.setMargins(marginStart, marginTop, marginEnd, marginBottom)
        radioButton.setLayoutParams(params)*/
    }
    companion object {
        const val ORIENTATION_VERTICAL = 0
        const val ORIENTATION_HORIZONTAL = 1
    }
}