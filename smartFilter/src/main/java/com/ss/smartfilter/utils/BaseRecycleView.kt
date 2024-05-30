package com.ss.smartfilter.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.singleselection.SingleSelectionMultiLineRadioButton

typealias SmartOrientation = com.ss.smartfilter.utils.Orientation

/**
 * created by Mala Ruparel ON 08/05/24
 */
abstract class BaseRecycleView : RecyclerView {

    protected var viewTextSelector: ColorStateList? = null
    protected var viewBgSelector: Drawable? = null
    protected var mList: ArrayList<Data> = ArrayList()
    protected var mAdapter: SingleSelectionMultiLineRadioButton.MultiLineRadioButtonAdapter? = null
    protected var spanCount: Int = 0
    protected var spacing: Int = 0
    protected var includeEdge: Boolean = false
    protected var smartOrientation: Int = SmartOrientation.VERTICAL
    protected var paddingAttributes: PaddingAttributes = PaddingAttributes()
    protected var textAttributes: TextAttributes = TextAttributes()
    protected var onMultiSelectionClicked: ((List<Int>) -> Unit)? = null
    protected var onSingleSelectionClicked: ((Data) -> Unit)? = null
    protected var dataFromXml: Int = 0
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
    protected fun setData(data: Data) = data.name
}