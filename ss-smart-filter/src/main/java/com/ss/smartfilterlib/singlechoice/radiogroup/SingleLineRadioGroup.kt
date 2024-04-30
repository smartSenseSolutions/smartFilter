package com.ss.smartfilterlib.singlechoice.radiogroup



import RadioGroupCallback
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
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
import com.ss.smartfilterlib.singlechoice.util.Orientation
import com.ss.smartfilterlib.singlechoice.util.PaddingAttributes
import com.ss.smartfilterlib.singlechoice.util.TextAttributes
import com.ss.smartfilterlib.data.RadioGroupData


/**
 * created by Mala Ruparel ON 17/04/24
 */
class SingleLineRadioGroup @JvmOverloads constructor(context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {


    private var textSelectorColor: ColorStateList? = null
    private var radioButtonDrawable: Drawable? = null
    private var orientation: Int = Orientation.VERTICAL

    private var textAttributes: TextAttributes = TextAttributes()
    private var paddingAttributes: PaddingAttributes = PaddingAttributes()

    private var dataFromXml: Int = 0
    private var onCheckedChangeListener: RadioGroupCallback? = null

    private lateinit var radioGroup: RadioGroup
    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView

    init {
        initAttrs(attrs)
        setupView()
        setDataFromAttrs()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineRadioGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SingleLineRadioGroup_rg_sl_Orientation, Orientation.VERTICAL)
            textSelectorColor = typedArray.getColorStateList(R.styleable.SingleLineRadioGroup_rg_sl_TextSelectorColor)
            radioButtonDrawable = typedArray.getDrawable(R.styleable.SingleLineRadioGroup_rg_sl_Background)


            dataFromXml = typedArray.getResourceId(R.styleable.SingleLineRadioGroup_rg_sl_Listitem, 0)

        } finally {
            typedArray.recycle()
        }
    }


    private fun setDataFromAttrs() {
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            mData.forEach {
                val data = RadioGroupData(name = it)
                addRadioButtonView(data)
            }
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
    }


    fun configureRadioButton(
        mData: ArrayList<RadioGroupData>,
        orientation: Int,
        bgSelector: Int,
        textSelector: Int,
        callbacks: RadioGroupCallback
    ) {
        this.orientation = orientation
        this.radioButtonDrawable = bgSelector.let { ContextCompat.getDrawable(context, it) }
        this.textSelectorColor = textSelector.let { ContextCompat.getColorStateList(context, it) }
        this.onCheckedChangeListener = callbacks
        setupRadioGroup()
        mData.forEach { data ->
            addRadioButtonView(
                data
            )
        }
    }

    private fun addRadioButtonView(data: RadioGroupData) {
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
            val checkedData = checkedRadioButton.tag as RadioGroupData?
            checkedData?.let { onCheckedChangeListener?.onSingleSelection(it) }
        }
    }

    private fun setData(data: RadioGroupData) = data.name


    private var defaultPadding = resources.getDimension(com.intuit.sdp.R.dimen._8sdp).toInt()


    private fun applySelector(radioButton: RadioButton) {
        if(textSelectorColor == null){
            textSelectorColor= setDefaultTextColor()
        }
        if(radioButtonDrawable == null){
            radioButtonDrawable=setDefaultDrawable()
        }
        radioButton.setTextColor(textSelectorColor)
        radioButton.buttonDrawable = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
    }


    private fun generateViewWithId(radioButton: RadioButton, data: RadioGroupData)  {
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

}