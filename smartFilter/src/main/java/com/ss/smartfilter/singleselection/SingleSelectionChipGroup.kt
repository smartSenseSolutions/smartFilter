package com.ss.smartfilter.singleselection



import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.utils.BaseLinearLayout
import com.ss.smartfilter.utils.Orientation
import com.ss.smartfilter.utils.SingleChipType
import com.ss.smartfilter.R


/**
 * created by Mala Ruparel ON 24/04/24
 */
class SingleSelectionChipGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseLinearLayout(context, attrs, defStyle) {


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()

    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineChipGroup, 0, 0)
        with(typedArray) {
            try {
                smartOrientation = getInt(R.styleable.SingleLineChipGroup_cg_sl_orientation,Orientation.VERTICAL)
                chipBgSelector = getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_background) ?: setDefaultDrawable()
                viewTextSelector =getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_text_selector) ?: setDefaultTextColor()
                dataFromXml = getResourceId(R.styleable.SingleLineChipGroup_cg_sl_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }

    override fun initializeView() {
        containerScrollView = ScrollView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        containerHorizontalScrollView = HorizontalScrollView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }

        chipGroup = ChipGroup(context)
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            setOrientation()
            setItems(resources.getStringArray(dataFromXml).map { Data(name = it) }, SingleChipType.ENTRY_CHIP)
        }
    }
    fun configureView(chipData: List<Data>,chipType: SingleChipType,orientation: Int,bgSelector: Int, textSelector: Int, checkedChangedListener: ( Data) -> Unit,) {

        updateValue(orientation, bgSelector, textSelector, checkedChangedListener)
        setOrientation()
        setItems(chipData,chipType)
    }
    private fun updateValue(orientation: Int,bgSelector: Int, primaryTextColor: Int,onCheckedChangeListener: ((Data) -> Unit)?) {
        this.smartOrientation= orientation
        this.chipBgSelector = ContextCompat.getColorStateList(context, bgSelector)
        this.viewTextSelector = primaryTextColor.let { ContextCompat.getColorStateList(context, it) }
        this.singleCheckedChangeListener = onCheckedChangeListener
    }
    private fun setItems(mData: List<Data>, chipType: SingleChipType) {
        mData.forEach { data ->
            val chip = createChip(chipType)
            with(chip) {
                text = setData(data)
                applyTextAttributes(this)
                applyPaddingAttributes(this)
                generateViewWithId(this, data)
                applyChipAttributes(this)
                setChipEvents(chip)
            }

            chipGroup.addView(chip)
        }
    }


    private fun createChip(chipType: SingleChipType): Chip {
        return when (chipType) {
            SingleChipType.ENTRY_CHIP -> creatEntryChip()
            SingleChipType.CHOICE_CHIP -> createInputChip()
            SingleChipType.ACTION_CHIP -> createActionChip()
            SingleChipType.NONE -> throw IllegalArgumentException("Invalid chip type")
        }
    }

    private fun creatEntryChip(): Chip {
        return Chip(context, null, R.style.EntryChipStyle).apply {
            isClickable = true
            isCheckedIconVisible=false
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
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

                containerScrollView.addView(chipGroup)
            }

            HORIZONTAL -> {
                if (containerScrollView.parent != null) {
                    removeView(containerScrollView)
                }
                if (containerHorizontalScrollView.parent == null) {
                    addView(containerHorizontalScrollView)
                }

                containerHorizontalScrollView.addView(chipGroup)
            }

        }
    }


    private fun createInputChip(): Chip {
        return Chip(context, null, R.style.FilterChipStyle).apply {
            chipStrokeWidth = 2f
            isCloseIconVisible = true
            isChipIconVisible=false
            isCheckedIconVisible=false
        }
    }

    private fun createActionChip(): Chip {
        return Chip(context).apply {
            isChipIconVisible=true
            isCheckedIconVisible=true
            isCloseIconVisible=true
            chipStrokeWidth = 2f
        }
    }

    private fun applyChipAttributes(chip: Chip) {
        chip.apply {
            chipBackgroundColor = chipBgSelector
            setTextColor(viewTextSelector)
            isClickable = true
            isCheckable = true
        }
    }

    private fun setChipEvents(chip: Chip) {
        chip.setOnCheckedChangeListener { _, _ ->
            singleCheckedChangeListener?.invoke(chip.tag as Data)
        }
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
            singleCheckedChangeListener?.invoke(chip.tag as Data)
        }
    }
    private fun setDefaultDrawable(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.chip_bg_selector)
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.chip_text_selector)
    }
    private fun generateViewWithId(radioButton: Chip, data: Data)  {
        radioButton.id = View.generateViewId()
        radioButton.tag = data
    }
    fun setonCheckedChangeListener(onCheckedChangeListener: (Data) -> Unit) {
        this.singleCheckedChangeListener = onCheckedChangeListener
    }
}

