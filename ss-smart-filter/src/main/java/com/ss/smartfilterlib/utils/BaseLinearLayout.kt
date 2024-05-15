package com.ss.smartfilterlib.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.ScrollView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilterlib.data.Data

/**
 * created by Mala Ruparel ON 10/05/24
 */
abstract class BaseLinearLayout : LinearLayout {

    protected var viewTextSelector: ColorStateList? = null
    protected var chipBgSelector: ColorStateList? = null
    protected var viewBgSelector: Drawable? = null
    protected var smartOrientation: Int = Orientation.VERTICAL

    protected var dataFromXml: Int = 0
    protected var singleCheckedChangeListener: ((Data) -> Unit)? = null
    protected var multiCheckedChangeListener: ((List<Int>) -> Unit)? = null
    protected var checkedChipIds: ArrayList<Int> = arrayListOf()

    protected lateinit var radioGroup: RadioGroup
    protected lateinit var chipGroup: ChipGroup

    protected lateinit var containerScrollView: ScrollView
    protected lateinit var containerHorizontalScrollView: HorizontalScrollView

    protected var paddingAttributes: PaddingAttributes = PaddingAttributes()
    protected var textAttributes: TextAttributes = TextAttributes()



    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected abstract fun initAttributes(attrs: AttributeSet?)
    protected abstract fun initializeView()

    protected fun applyTextAttributes(chipText: Chip) {
        textAttributes.let { attributes ->
            chipText.apply {
                textSize = attributes.textSize
                typeface = Typeface.create(Typeface.DEFAULT, attributes.fontFamily)
            }
        }
    }

    protected fun applyPaddingAttributes(chip: Chip) {
        paddingAttributes.let { attributes ->
            chip.setPadding(
                attributes.paddingStart,
                attributes.paddingTop,
                attributes.paddingEnd,
                attributes.paddingBottom
            )
        }
    }
    protected fun setData(data: Data) = data.name
}