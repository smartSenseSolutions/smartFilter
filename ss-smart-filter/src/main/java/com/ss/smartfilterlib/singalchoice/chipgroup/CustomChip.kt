package com.ss.smartfilterlib.singalchoice.chipgroup

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.singalchoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.ChipType

/**
 * created by Mala Ruparel ON 23/04/24
 */
class CustomChipGroup : ChipGroup {

    private var chipBackgroundColor: ColorStateList? = null
    private var chipTextColor: ColorStateList? = null
    private var closeIconColor: ColorStateList? = null
    private var closeIconDrawable: Drawable? = null

    constructor(context: Context) : super(context) {
        setupView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
        setupView()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ChipGroup, 0, 0)
        try {
            chipBackgroundColor = typedArray.getColorStateList(R.styleable.ChipGroup_cg_sl_Background)
            chipTextColor = typedArray.getColorStateList(R.styleable.ChipGroup_cg_sl_Textcolor)
            closeIconColor = typedArray.getColorStateList(R.styleable.ChipGroup_cg_sl_close_icon_color)
            closeIconDrawable = typedArray.getDrawable(R.styleable.ChipGroup_cg_sl_close_icon_drawable)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setupView() {
        isSingleLine = true
    }

    fun addChips(chipData: List<RadioGroupData>, chipType: ChipType) {
        chipData.forEach { data ->
            val chip = createChip(chipType)
            chip.text = data.name
            setChipAttributes(chip)
           // chip.setOnClickListener { callback(data) }
            addView(chip)
        }
    }

    private fun createChip(chipType: ChipType): Chip {
        return when (chipType) {
            ChipType.ASSIST_CHIP -> createAssistChip()
            ChipType.FILTER_CHIP -> createFilterChip()
            ChipType.INPUT_CHIP -> createInputChip()
            ChipType.SUGGESTION_CHIP -> createSuggestionChip()
        }
    }

    private fun createAssistChip(): Chip {
        return Chip(context).apply {
            chipIcon = context.getDrawable(R.drawable.ic_free)
            chipIconTint = ColorStateList.valueOf(context.getColor(R.color.purple_500))
        }
    }

    private fun createFilterChip(): Chip {
        return Chip(context).apply {
            isCloseIconVisible = true
            closeIcon = context.getDrawable(R.drawable.ic_close)
            closeIconTint = closeIconColor
        }
    }

    private fun createInputChip(): Chip {
        return Chip(context).apply {
            chipBackgroundColor = chipBackgroundColor
            chipStrokeColor = chipTextColor
            chipStrokeWidth = 2f
        }
    }

    private fun createSuggestionChip(): Chip {
        return Chip(context).apply {
            isClickable = true
            isCheckable = true
            chipBackgroundColor = chipBackgroundColor
            checkedIcon = context.getDrawable(R.drawable.ic_check)
            chipStrokeColor = chipTextColor
            chipStrokeWidth = 2f
        }
    }

    private fun setChipAttributes(chip: Chip) {
        chipBackgroundColor?.let { chip.chipBackgroundColor = it }
        chipTextColor?.let { chip.setTextColor(it) }
    }
}