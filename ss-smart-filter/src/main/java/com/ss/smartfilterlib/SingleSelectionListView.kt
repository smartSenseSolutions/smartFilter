package com.ss.smartfilterlib

import RadioGroupCallback
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

typealias SmartOrientation = com.ss.smartfilterlib.singlechoice.util.Orientation

/**
 * created by Mala Ruparel ON 02/05/24
 */
class SingleSelectionView @JvmOverloads constructor( context: Context,attrs: AttributeSet? = null,defStyle: Int = 0) : BaseClass<RadioGroupData>(context, attrs, defStyle){


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
            onCheckedChangeListener = onSingleSelectionClicked
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

    fun configureView(data: ArrayList<RadioGroupData>, orientation: Int, checkSelector: Int, primaryTextColor: Int, onCheckedChangeListener: RadioGroupCallback?){
        updateValue(orientation, checkSelector, primaryTextColor, onCheckedChangeListener)
        initializeView()
        setItems(data)
    }
    private fun updateValue(
        orientation: Int,
        checkSelector: Int,
        primaryTextColor: Int,
        onCheckedChangeListener: RadioGroupCallback?
    ) {
        this.onSingleSelectionClicked = onCheckedChangeListener
        this.orientation = orientation
        this.checkSelector = checkSelector
        this.primaryTextColor = primaryTextColor
    }

    private fun setItems(items: List<RadioGroupData>) {
        adapter = SingleSelectionListAdapter().apply { data = items }
    }

    private inner class SingleSelectionListAdapter() : RecyclerView.Adapter<SingleSelectionListAdapter.SingleSelectionListViewHolder>() {

        private var selectedItemPosition: Int = RecyclerView.NO_POSITION

        var data: List<RadioGroupData> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleSelectionListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple_list_checkable, parent, false)
            return SingleSelectionListViewHolder(view)
        }

        override fun onBindViewHolder(holder: SingleSelectionListViewHolder, position: Int) {
            holder.bind(data[position],position == selectedItemPosition)
        }

        override fun getItemCount(): Int = data.size

        inner class SingleSelectionListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
                    isChecked = adapterPosition == selectedItemPosition
                    applyTextAttributes(this)
                    applyPaddingAttributes(this)
                    text = setData(data)
                    applySelector(this)
                    compoundDrawablePadding =10
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
            val previousSelectedItemPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(previousSelectedItemPosition)
            notifyItemChanged(selectedItemPosition)
            onSingleSelectionClicked?.onSingleSelection(data[position])

        }

    }
    private fun applySelector(textView: CheckedTextView) {

        textView.setTextColor(primaryTextColor)
        textView.setCheckMarkDrawable(checkSelector)

    }



  fun setOnSingleSelection(callback: RadioGroupCallback) {
      onSingleSelectionClicked = callback
  }
}
