package com.ss.smartfilterlib.multiselection

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.singleselection.BaseLinearLayout
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation

/**
 * created by Mala Ruparel ON 25/04/24
 */
class MultiselectionChipGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseLinearLayout<RadioGroupData>(context, attrs, defStyle) {

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
                chipBgSelector = getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_background)
                viewTextSelector = getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_text_selector)
                dataFromXml = getResourceId(R.styleable.SingleLineChipGroup_cg_sl_list_item, 0)
            } finally {
                typedArray.recycle()
            }
        }
    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            val data = mData.map { RadioGroupData(name = it) } as ArrayList<RadioGroupData>
            setOrientation()
            setItems(data, MultiChipType.ENTRY_CHIP)
        }
    }
    override fun initializeView() {
        containerScrollView = ScrollView(context)
        containerHorizontalScrollView = HorizontalScrollView(context)

        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        containerScrollView.layoutParams = layoutParams
        containerHorizontalScrollView.layoutParams = layoutParams

        chipGroup = ChipGroup(context)
    }

    fun configureView(chipData: List<RadioGroupData>,chipType: MultiChipType,orientation: Int,bgSelector: Int,textSelector: Int, checkedChangedListener: ( List<Int>) -> Unit ) {

        updateValue(orientation, bgSelector, textSelector, checkedChangedListener)
        setOrientation()
        setItems(chipData,chipType)
    }

    private fun updateValue(orientation: Int,bgSelector: Int, textSelector: Int,onCheckedChangeListener: ((List<Int>) -> Unit)?) {
        this.smartOrientation = orientation
     this.multiCheckedChangeListener = onCheckedChangeListener
     this.chipBgSelector = ContextCompat.getColorStateList(context, bgSelector)
     this.viewTextSelector = ContextCompat.getColorStateList(context, textSelector)
       // chipGroup.isSingleLine = orientation == smartOrientation
        chipGroup.isSingleSelection = false
        chipGroup.isSelectionRequired = true

    }
    private fun setItems(mData: List<RadioGroupData>, chipType: MultiChipType) {
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

    private fun applyChipAttributes(chip: Chip) {
        chip.apply {
            chipBackgroundColor = chipBgSelector
            setTextColor(viewTextSelector)
            isClickable = true
            isCheckable = true
        }
    }

    private fun setChipEvents(chip: Chip,data: RadioGroupData) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            data.isSelected = isChecked
            if (isChecked) {
                checkedChipIds += chip.id
            } else {
                checkedChipIds -= chip.id
            }
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

    }

}