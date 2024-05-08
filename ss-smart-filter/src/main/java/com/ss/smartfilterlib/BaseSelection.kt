package com.ss.smartfilterlib

import RadioGroupCallback
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singlechoice.util.Constant
import com.ss.smartfilterlib.singlechoice.util.PaddingAttributes
import com.ss.smartfilterlib.singlechoice.util.TextAttributes

/**
 * created by Mala Ruparel ON 08/05/24
 */
abstract class BaseClass<T> : RecyclerView {


    protected var primaryTextColor: Int = Constant.PRIMARY_TEXT_COLOR
    protected var orientation: Int = SmartOrientation.VERTICAL
    protected var checkSelector: Int = 0
    protected var paddingAttributes: PaddingAttributes = PaddingAttributes()
    protected var textAttributes: TextAttributes = TextAttributes()
    protected var onMultiSelectionClicked: ((List<RadioGroupData>) -> Unit)? = null
    protected var onSingleSelectionClicked: RadioGroupCallback? = null
    protected var dataFromXml: Int = 0
    protected var data: List<T> = emptyList()
    protected val selectedItemsPositions = mutableListOf<Int>()
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // Common methods
    protected abstract fun initAttributes(attrs: AttributeSet?)
    protected abstract fun initializeView()

    protected fun applyTextAttributes(textView: CheckedTextView) {
        textAttributes.let { attributes ->
            textView.apply {
                textSize = attributes.textSize
                typeface = Typeface.create(Typeface.DEFAULT, attributes.fontFamily)
            }
        }
    }
    protected fun applyPaddingAttributes(textView: CheckedTextView) {
        paddingAttributes.let { attributes ->
            textView.setPadding(
                attributes.paddingStart,
                attributes.paddingTop,
                attributes.paddingEnd,
                attributes.paddingBottom
            )
        }
    }
    protected fun setData(data: RadioGroupData) = data.name
}