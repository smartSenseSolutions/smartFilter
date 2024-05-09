package com.ss.smartfilterlib.singleselection



import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.callback.RadioGroupCallback
import com.ss.smartfilterlib.utils.Orientation
import com.ss.smartfilterlib.utils.SingleChipType


/**
 * created by Mala Ruparel ON 24/04/24
 */
class SingleSelectionChipGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {

    private var chipBGColor: ColorStateList? = null
    private var chipTextColor: ColorStateList? = null
    private var orientation: Int = Orientation.VERTICAL
    private lateinit var chipGroup: ChipGroup
    private lateinit var containerScrollView: ScrollView
    private lateinit var containerHorizontalScrollView: HorizontalScrollView
    private var onChipGroupClickListener: RadioGroupCallback? = null
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

    fun setData(
        chipData: List<RadioGroupData>,
        chipType: SingleChipType,
        orientation: Int,
        bgSelector: Int,
        textSelector: Int,
        callbacks: RadioGroupCallback,
    ) {
        chipGroup.removeAllViews()
        this.onChipGroupClickListener= callbacks
        this.orientation = orientation
        this.chipBGColor = ContextCompat.getColorStateList(context, bgSelector)
        this.chipTextColor = ContextCompat.getColorStateList(context, textSelector)
        chipGroup.isSingleLine = orientation == Orientation.HORIZONTAL
        chipGroup.isSingleSelection = true
        chipGroup.isSelectionRequired=true
        setupRadioGroup()
        chipData.forEach { data ->
            val chip = createChip(chipType)
            chip.text = data.name
            generateViewWithId(chip, data)
            setChipEvents(chip)
            setChipAttributes(chip)
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
        }
    }

    private fun setupRadioGroup() {

        if (this.orientation == VERTICAL) {

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

    private fun setChipAttributes(chip: Chip) {
        chip.apply {
            chipBackgroundColor = chipBGColor
            setTextColor(chipTextColor)
            isClickable = true
            isCheckable = true
        }
    }

    private fun setChipEvents(chip: Chip) {
        chip.setOnCheckedChangeListener { _, _ ->
            onChipGroupClickListener?.onSingleSelection(chip.tag as RadioGroupData)
        }
    }
    private fun generateViewWithId(radioButton: Chip, data: RadioGroupData)  {
        radioButton.id = View.generateViewId()
        radioButton.tag = data
    }
}

