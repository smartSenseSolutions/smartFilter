package com.ss.smartfilterlib.singleselection



import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.utils.BaseLinearLayout
import com.ss.smartfilterlib.utils.Orientation


/**
 * created by Mala Ruparel ON 17/04/24
 */
class SingleSelectionRadioGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseLinearLayout(context, attrs, defStyle) {

    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineRadioGroup, 0, 0)
        with(typedArray) {
            try {
                smartOrientation = getInt(R.styleable.SingleLineRadioGroup_rg_sl_orientation, Orientation.VERTICAL)
                viewTextSelector = getColorStateList(R.styleable.SingleLineRadioGroup_rg_sl_text_color) ?: setDefaultTextColor()
                viewBgSelector = getDrawable(R.styleable.SingleLineRadioGroup_rg_sl_background) ?: setDefaultDrawable()
                dataFromXml = getResourceId(R.styleable.SingleLineRadioGroup_rg_sl_list_item, 0)

            } finally {
                typedArray.recycle()
            }
        }

    }


    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            setOrientation()
            mData.forEach {
                val data = Data(name = it)
                addRadioButtonView(data)
            }
        }
    }
    override fun initializeView() {
        containerScrollView = ScrollView(context)
        containerHorizontalScrollView = HorizontalScrollView(context)

        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        containerScrollView.layoutParams = layoutParams
        containerHorizontalScrollView.layoutParams = layoutParams
        radioGroup = RadioGroup(context)

    }

    private fun setOrientation() {
        when (smartOrientation) {
            VERTICAL -> {
                if (containerHorizontalScrollView.parent != null) {
                    removeView(containerHorizontalScrollView)
                }
                if (containerScrollView.parent == null) {
                    addView(containerScrollView)
                }
                radioGroup.orientation = VERTICAL
                containerScrollView.addView(radioGroup)

            }

            HORIZONTAL -> {
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
    }


    fun configureRadioButton(mData: ArrayList<Data>, orientation: Int, bgSelector: Int, textSelector: Int, checkedChangedListener: (Data) -> Unit) {
        updateValue(orientation, bgSelector, textSelector, checkedChangedListener)
        setOrientation()
        setItems(mData)
    }
    private fun updateValue(orientation: Int,checkSelector: Int, primaryTextColor: Int,onCheckedChangeListener: ((Data) -> Unit)?) {
        this.smartOrientation = orientation
        this.viewBgSelector = checkSelector.let { ContextCompat.getDrawable(context, it) }
        this.viewTextSelector = primaryTextColor.let { ContextCompat.getColorStateList(context, it) }
        this.singleCheckedChangeListener = onCheckedChangeListener
    }
    private fun setItems(mData: List<Data>) {
        mData.forEach { data ->
            addRadioButtonView(
                data
            )
        }
    }
    private fun addRadioButtonView(data: Data) {
        val radioButton = RadioButton(context)

        radioButton.apply {
            text = setData(data)
            applyTextAttributes(this)
            applyPaddingAttributes(this)
            applySelector(this)
        }

        generateViewWithId(radioButton, data)
        radioGroup.addView(radioButton)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val checkedRadioButton: RadioButton = findViewById(checkedId)
            val checkedData = checkedRadioButton.tag as Data?
            checkedData?.let { singleCheckedChangeListener?.invoke(it) }
        }
    }


    private fun applySelector(radioButton: RadioButton) {
        viewTextSelector?.let { radioButton.setTextColor(it) }
        viewBgSelector?.let { radioButton.buttonDrawable = it.constantState?.newDrawable()?.mutate() }
    }


    private fun generateViewWithId(radioButton: RadioButton, data: Data)  {
        radioButton.id = View.generateViewId()
        radioButton.tag = data
    }

    private fun setDefaultDrawable() : Drawable?{
       return  ContextCompat.getDrawable(context,androidx.appcompat.R.drawable.abc_btn_radio_material,)
    }
    private fun setDefaultTextColor(): ColorStateList? {
     return ContextCompat.getColorStateList(context, R.color.black)
    }

    private fun applyTextAttributes(radioButton: RadioButton) {
        textAttributes.let { attributes ->
            radioButton.apply {
                textSize = attributes.textSize
                typeface = Typeface.create(Typeface.DEFAULT, attributes.fontFamily)
            }
        }
    }

    private fun applyPaddingAttributes(radioButton: RadioButton) {
        paddingAttributes.let { attributes ->
            radioButton.setPadding(
                attributes.paddingStart,
                attributes.paddingTop,
                attributes.paddingEnd,
                attributes.paddingBottom
            )
        }
    }

    fun onCheckedChangeListener(onCheckedChangeListener: (Data) -> Unit) {
        this.singleCheckedChangeListener = onCheckedChangeListener
    }
}