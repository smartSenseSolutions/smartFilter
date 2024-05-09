package com.ss.smartfilterlib

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData


/**
 * created by Mala Ruparel ON 02/05/24
 */
class MultiSelectionListView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) :  BaseClass<RadioGroupData>(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }

    private fun populateDataFromAttributes() {
        val mData = resources.getStringArray(dataFromXml);
        val data = mData.map { RadioGroupData(name = it) } as ArrayList<RadioGroupData>
        configureView(
            data = data,
            orientation = orientation,
            checkSelector = checkSelector,
            primaryTextColor = primaryTextColor,
            onCheckedChangeListener = onMultiSelectionClicked
        )

    }

    override fun initializeView() {
        layoutManager = when (orientation) {
            SmartOrientation.VERTICAL -> LinearLayoutManager(context, VERTICAL, false)
            else -> LinearLayoutManager(context, HORIZONTAL, false)
        }
    }

    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectionView)
        try {
            primaryTextColor = typedArray.getColor(R.styleable.SingleSelectionView_ss_textselector, primaryTextColor)
            orientation = typedArray.getInt(R.styleable.SingleSelectionView_ss_orientation,RecyclerView.VERTICAL)
            checkSelector = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_checkdrawableselector,R.drawable.ic_check_selector)
            dataFromXml = typedArray.getResourceId(R.styleable.SingleSelectionView_ss_listitem, 0)
        } finally {
            typedArray.recycle()
        }
    }

    fun configureView(
        data: ArrayList<RadioGroupData>,
        orientation: Int,
        checkSelector: Int, primaryTextColor: Int, onCheckedChangeListener: ((List<RadioGroupData>) -> Unit)?
    ) {

       updateValue(orientation, checkSelector, primaryTextColor, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }

    private fun updateValue(
        orientation: Int,
        checkSelector: Int,
        primaryTextColor: Int,
        onCheckedChangeListener: ((List<RadioGroupData>) -> Unit)?
    ) {
        this.onMultiSelectionClicked = onCheckedChangeListener
        this.orientation = orientation
        this.checkSelector = checkSelector
        this.primaryTextColor = primaryTextColor
    }
    private fun setItems(items: List<RadioGroupData>) {
        adapter = MultiSelectionListAdapter().apply { data = items }
    }

    private inner class MultiSelectionListAdapter() : RecyclerView.Adapter<MultiSelectionListAdapter.MultiselectionViewHolder>() {

        private var selectedItemPosition: Int = RecyclerView.NO_POSITION

        var data: List<RadioGroupData> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectionViewHolder {
            val view = LayoutInflater.from(parent.context) .inflate(R.layout.item_simple_list_checkable, parent, false)
            return MultiselectionViewHolder(view)
        }

        override fun onBindViewHolder(holder: MultiselectionViewHolder, position: Int) {
            holder.bind(data[position],position == selectedItemPosition)
        }

        override fun getItemCount(): Int = data.size

        inner class MultiselectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val cardView: MaterialCardView by lazy { MaterialCardView(itemView.context) }
            val textView: CheckedTextView by lazy { CheckedTextView(itemView.context) }


            init {
                with(cardView) {
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
                    isChecked = selectedItemsPositions.contains(adapterPosition)
                    applyTextAttributes(this)
                    applyPaddingAttributes(this)
                    text = setData(data)
                    applySelector(this)
                    extracted()
                }

            }
            private fun CheckedTextView.extracted() {
                checkMarkDrawable?.setBounds(
                    0,
                    0,
                    checkMarkDrawable.intrinsicWidth,
                    checkMarkDrawable.intrinsicHeight
                )
                gravity = Gravity.CENTER_VERTICAL
                setPadding(10, 10, 10, 10)
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

        textView.setTextColor(primaryTextColor)
        textView.setCheckMarkDrawable(checkSelector)

    }

    fun setOnMultiSelection(callback: (List<RadioGroupData>) -> Unit) {
        onMultiSelectionClicked = callback
    }
}
