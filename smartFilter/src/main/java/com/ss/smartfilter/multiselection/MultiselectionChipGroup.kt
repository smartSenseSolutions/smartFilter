package com.ss.smartfilter.multiselection

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
import com.ss.smartfilter.utils.MultiChipType
import com.ss.smartfilter.utils.Orientation
import com.ss.smartfilter.R

/**
 * created by Mala Ruparel ON 25/04/24
 */
class MultiselectionChipGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseLinearLayout(context, attrs, defStyle) {

    init {
        initAttributes(attrs = attrs)
        initializeView()
        populateDataFromAttributes()
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineChipGroup, 0, 0)
        with(typedArray) {
            try {
                smartOrientation = getInt(R.styleable.SingleLineChipGroup_cg_sl_orientation,Orientation.VERTICAL)
                chipBgSelector = getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_background) ?: setDefaultDrawable()
                viewTextSelector = getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_text_selector) ?: setDefaultTextColor()
                dataFromXml = getResourceId(R.styleable.SingleLineChipGroup_cg_sl_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            setOrientation()
            setItems(resources.getStringArray(dataFromXml).map { Data(name = it) }, MultiChipType.ENTRY_CHIP)
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

    fun configureView(chipData: List<Data>,chipType: MultiChipType,orientation: Int,bgSelector: Int,textSelector: Int, checkedChangedListener: ( List<Int>) -> Unit ) {

        updateValue(orientation, bgSelector, textSelector, checkedChangedListener)
        setOrientation()
        setItems(chipData,chipType)
    }

    private fun updateValue(orientation: Int,bgSelector: Int, textSelector: Int,onCheckedChangeListener: ((List<Int>) -> Unit)?) {
        this.smartOrientation = orientation
     this.multiCheckedChangeListener = onCheckedChangeListener
     this.chipBgSelector = ContextCompat.getColorStateList(context, bgSelector)
     this.viewTextSelector = ContextCompat.getColorStateList(context, textSelector)

    }
    private fun setItems(mData: List<Data>, chipType: MultiChipType) {
        val chipIds = mData.map { it.id }
        mData.forEachIndexed {index, data ->
            val chip = createChip(chipType)
            with(chip) {
                text = setData(data)
                if (chipIds.size > index) {
                    chip.id = chipIds[index]
                }
                applyTextAttributes(this)
                applyPaddingAttributes(this)
                applyChipAttributes(this)
                setChipEvents(chip, data = data)
            }

            chipGroup.addView(chip)
        }

    }
    private fun createChip(chipType: MultiChipType): Chip {
        return when (chipType) {
            MultiChipType.ENTRY_CHIP -> createEntryChip()
            MultiChipType.FILTER_CHIP -> createFilterChip()
            MultiChipType.ACTION_CHIP -> createActionChip()
            MultiChipType.NONE -> throw IllegalArgumentException("Invalid chip type")

        }
    }

    private fun createEntryChip(): Chip {
        return Chip(context, null, R.style.EntryChipStyle).apply {
            isClickable = true
            isCheckedIconVisible = false
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

    private fun createFilterChip(): Chip {
        return Chip(context, null, R.style.FilterChipStyle).apply {
            isCloseIconVisible = false
            isChipIconVisible = true
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }


    private fun createActionChip(): Chip {
        return Chip(context).apply {
            isChipIconVisible = true
            isCheckedIconVisible = true
            isCloseIconVisible = true
            chipStrokeWidth = 2f
        }
    }
    private fun setDefaultDrawable(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.chip_bg_selector)
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.chip_text_selector)
    }
    private fun applyChipAttributes(chip: Chip) {
        chip.apply {
            chipBackgroundColor = chipBgSelector
            setTextColor(viewTextSelector)
            isClickable = true
            isCheckable = true
        }
    }

    private fun setChipEvents(chip: Chip, data: Data) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            data.isSelected = isChecked
            if (isChecked) {
                if (!checkedChipIds.contains(chip.id)) {
                    checkedChipIds.add(chip.id)
                }
            } else {
                checkedChipIds.remove(chip.id)
            }

            multiCheckedChangeListener?.invoke( checkedChipIds.toList())

        }
        chip.setOnCloseIconClickListener {
            chipGroup.removeView(chip)
            checkedChipIds.remove(chip.id)
            multiCheckedChangeListener?.invoke(checkedChipIds.toList())
        }

    }

}