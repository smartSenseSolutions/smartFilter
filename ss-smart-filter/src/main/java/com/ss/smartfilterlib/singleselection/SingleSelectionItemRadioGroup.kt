package com.ss.smartfilterlib.singleselection


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.HorizontalScrollView
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.Data
import com.ss.smartfilterlib.databinding.RowItemBinding
import com.ss.smartfilterlib.utils.BaseLinearLayout

/**
 * created by Mala Ruparel ON 19/04/24
 */
class SingleSelectionItemRadioGroup(context: Context, attrs: AttributeSet? =null,defStyle: Int = 0) :  BaseLinearLayout(context, attrs, defStyle) {


    init {       
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowItemRadioGroup)
        with(typedArray) {
            try {
                viewTextSelector =getColorStateList(R.styleable.RowItemRadioGroup_rg_ri_text_selector) ?: setDefaultTextColor()
                viewBgSelector = getDrawable(R.styleable.RowItemRadioGroup_rg_ri_background) ?: setDefaultDrawable()
                smartOrientation = getInt(R.styleable.RowItemRadioGroup_rg_ri_orientation, HORIZONTAL)
                dataFromXml = getResourceId(R.styleable.RowItemRadioGroup_rg_ri_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes(){
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


    fun configureRadioButton(mData: ArrayList<Data>,orientation: Int,bgSelector: Int,textSelector: Int,checkedChangedListener: ( Data) -> Unit) {
        updateValue(orientation, bgSelector, textSelector, checkedChangedListener)
        setOrientation()
        setItems(mData)      
    }
    private fun setItems(mData: ArrayList<Data>) {
        mData.forEach { data ->
            addRadioButtonView(
                data
            )
        }
    }
    private fun updateValue(orientation: Int,checkSelector: Int, primaryTextColor: Int,onCheckedChangeListener: ((Data) -> Unit)?) {
        this.smartOrientation = orientation
        this.viewBgSelector = checkSelector.let { ContextCompat.getDrawable(context, it) }
        this.viewTextSelector = primaryTextColor.let { ContextCompat.getColorStateList(context, it) }
        this.singleCheckedChangeListener = onCheckedChangeListener
    }
    private fun addRadioButtonView(data: Data) {
            val binding = RowItemBinding.inflate(LayoutInflater.from(context), this, false)
            binding.apply {
                tvName.text = data.name
                tvDes.text = data.description

            }

            applySelector(binding.rtl)
            radioGroup.addView(binding.root)
            binding.rtl.setOnClickListener {
                data.let { mData -> singleCheckedChangeListener?.invoke(mData) }
            }

    }

    private fun applySelector(rtl: RelativeLayout) {
        rtl.background = viewBgSelector
    }
    private fun setDefaultDrawable() : Drawable?{
        return  ContextCompat.getDrawable(context,R.drawable.row_item_selector,)
    }
    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }

}
