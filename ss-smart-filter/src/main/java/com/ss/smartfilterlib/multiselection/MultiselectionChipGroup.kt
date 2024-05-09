package com.ss.smartfilterlib.multiselection

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.utils.MultiChipType
import com.ss.smartfilterlib.utils.Orientation

/**
 * created by Mala Ruparel ON 25/04/24
 */
class MultiselectionChipGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :LinearLayout(context, attrs, defStyle) {

    private var chipBGColor: ColorStateList? = null
    private var chipTextColor: ColorStateList? = null
    private var orientation: Int = Orientation.VERTICAL
    private lateinit var chipGroup: ChipGroup
    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView
    private var checkedChangedListener: ((List<Int>) -> Unit)? = null
    private var checkedChipIds: ArrayList<Int> = arrayListOf()

    init {
        initAttrs(attrs)
        setupView()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SingleLineChipGroup, 0, 0)
        try {
            orientation = typedArray.getInt(R.styleable.SingleLineChipGroup_cg_sl_orientation, Orientation.VERTICAL)
            chipBGColor = typedArray.getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_background)
            chipTextColor = typedArray.getColorStateList(R.styleable.SingleLineChipGroup_cg_sl_textselector)

        } finally {
            typedArray.recycle()
        }
    }

    private fun setupView() {
        containerScrollView = ScrollView(context)
        containerHorizontalScrollView = HorizontalScrollView(context)

        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        containerScrollView.layoutParams = layoutParams
        containerHorizontalScrollView.layoutParams = layoutParams

        chipGroup = ChipGroup(context)
    }

    fun setData(chipData: List<RadioGroupData>,chipType: MultiChipType,orientation: Int,bgSelector: Int,textSelector: Int, checkedChangedListener: ( List<Int>) -> Unit ) {
        var chipIds = chipData.map { it.id }

        chipGroup.removeAllViews()
        this.checkedChangedListener = checkedChangedListener
        this.orientation = orientation
        this.chipBGColor = ContextCompat.getColorStateList(context, bgSelector)
        this.chipTextColor = ContextCompat.getColorStateList(context, textSelector)
        chipGroup.isSingleLine = orientation == Orientation.HORIZONTAL
        chipGroup.isSingleSelection = false
        chipGroup.isSelectionRequired = true
        setupRadioGroup()
        chipData.forEachIndexed { index, data ->
            val chip = createChip(chipType)
            chip.text = data.name

            if (chipIds.size > index) {
                chip.id = chipIds[index]
            }
            setChipEvents(chip, data)
            setChipAttributes(chip)
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

    private fun setupRadioGroup() {
        if (this.orientation == Orientation.VERTICAL) {
            if (containerHorizontalScrollView.parent != null) {
                removeView(containerHorizontalScrollView)
            }
            if (containerScrollView.parent == null) {
                addView(containerScrollView)
            }
            containerScrollView.addView(chipGroup)
        } else {
            if (containerScrollView.parent != null) {
                removeView(containerScrollView)
            }
            if (containerHorizontalScrollView.parent == null) {
                addView(containerHorizontalScrollView)
            }
            containerHorizontalScrollView.addView(chipGroup)
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

    private fun setChipAttributes(chip: Chip) {
        chip.apply {
            chipBackgroundColor = chipBGColor
            setTextColor(chipTextColor)
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

            checkedChangedListener?.invoke( checkedChipIds.toList())

        }

    }

}