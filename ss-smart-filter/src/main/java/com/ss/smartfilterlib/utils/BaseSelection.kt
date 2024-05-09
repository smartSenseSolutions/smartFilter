package com.ss.smartfilterlib.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.data.RadioGroupData
typealias SmartOrientation = com.ss.smartfilterlib.utils.Orientation

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
    protected var onSingleSelectionClicked: ((RadioGroupData) -> Unit)? = null
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