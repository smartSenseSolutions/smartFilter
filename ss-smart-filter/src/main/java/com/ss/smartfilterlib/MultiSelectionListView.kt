package com.ss.smartfilterlib

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singlechoice.util.PaddingAttributes


/**
 * created by Mala Ruparel ON 02/05/24
 */
class MultiSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private var textSelectorColor: ColorStateList? = null
    private var bgSelector: Drawable? = null
    private var orientation: Int = com.ss.smartfilterlib.singlechoice.util.Orientation.VERTICAL
    private var checkDrawableSelector: Int = 0
    private  var paddingAttributes: PaddingAttributes = PaddingAttributes()
    private var onMultiSelectionClicked: ((List<RadioGroupData>) -> Unit)? = null

    private var dataFromXml: Int = 0
    private val selectedItemsPositions = mutableListOf<Int>()

    init {
        initAttrs(attrs)
        setupView()
        setDataFromAttrs()
    }

    private fun setDataFromAttrs() {
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            val data = ArrayList<RadioGroupData>()
            for (i in mData.indices) {
                data.add(RadioGroupData(name = mData[i]))
            }
            configureView(data,orientation,checkDrawableSelector,textSelectorColor,onMultiSelectionClicked)


        }
    }

    private fun setupView() {
        layoutManager = when (orientation) {
            com.ss.smartfilterlib.singlechoice.util.Orientation.VERTICAL -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            else -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectionView)
        try {
            bgSelector = typedArray.getDrawable(R.styleable.SingleSelectionView_ss_Background)
            textSelectorColor = typedArray.getColorStateList(R.styleable.SingleSelectionView_ss_TextSelector)
            orientation = typedArray.getInt(R.styleable.SingleSelectionView_ss_Orientation,RecyclerView.VERTICAL)
            checkDrawableSelector = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_checkDrawableSelector,R.drawable.ic_check_selector)
            dataFromXml = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_Listitem, 0)
        } finally {
            typedArray.recycle()
        }
    }

    fun configureView(
        mData: ArrayList<RadioGroupData>,
        orientation: Int,
        checkSelector: Int, textSelector: ColorStateList?, onCheckedChangeListener: ((List<RadioGroupData>) -> Unit)?
    ) {

        this.orientation = orientation
        this.onMultiSelectionClicked = onCheckedChangeListener
        setupView()
        adapter = CustomAdapter(checkSelector, textSelector)
        setItems(mData)
    }

    private fun setItems(items: List<RadioGroupData>) {
        (adapter as? CustomAdapter)?.data = items
    }

    private inner class CustomAdapter(
        var checkSelector: Int,
        var textSelector: ColorStateList?,

    ) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

        private var selectedItemPosition: Int = RecyclerView.NO_POSITION

        var data: List<RadioGroupData> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_simple_list_checkable, parent, false)
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.bind(data[position],
                position == selectedItemPosition
            )
        }

        override fun getItemCount(): Int = data.size

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val cardView = MaterialCardView(itemView.context)
            private val textView = CheckedTextView(itemView.context)

            init {
                cardView.apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    addView(textView)
                }


                (itemView as ViewGroup).addView(cardView)

                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        toggleItemSelection(position)
                    }
                }
            }


            fun bind(data: RadioGroupData, isSelected: Boolean) {
                textView.apply {

                    setPadding(
                        paddingAttributes.paddingStart,
                        paddingAttributes.paddingTop,
                        paddingAttributes.paddingEnd,
                        paddingAttributes.paddingBottom
                    ) // Set
                    text = data.name
                    applySelector(this)
                   // setTextColor(textSelector)
                    isChecked = adapterPosition == selectedItemPosition
                    checkDrawableSelector = checkSelector
                    setCheckMarkDrawable(checkDrawableSelector)
                    checkMarkDrawable?.setBounds(
                        40,
                        400,
                        checkMarkDrawable.intrinsicWidth,
                        checkMarkDrawable.intrinsicHeight
                    )
                    isChecked = selectedItemsPositions.contains(adapterPosition)
                }

            }
        }

        private fun toggleItemSelection(position: Int) {
            if (selectedItemsPositions.contains(position)) {
                selectedItemsPositions.remove(position)
            } else {
                selectedItemsPositions.add(position)
            }
            notifyItemChanged(position)
            onMultiSelectionClicked?.invoke(data.filterIndexed { index, _ -> selectedItemsPositions.contains(index) })

        }
    }
    private fun applySelector(textView: CheckedTextView) {
        with(textView) {
            setTextColor(textSelectorColor ?: setDefaultTextColor())
            // radioButton.buttonDrawable = checkDrawableSelector?.constantState?.newDrawable()?.mutate()
        }
    }
    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)?.let {
            it
        } ?: run {
            null
        }
    }

    fun setOnMultiSelectionClicked(callback: (List<RadioGroupData>) -> Unit) {
        onMultiSelectionClicked = callback
    }
}
