package com.ss.smartfilterlib.adapter

import android.content.res.ColorStateList
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.databinding.RowItemCheckableBinding

/**
 * created by Mala Ruparel ON 10/05/24
 */
class MultiSelectionListAdapter(
    private val checkSelector: Int,
    private val primaryTextColor: ColorStateList?,
    private val selectedItemsPositions: MutableList<Int>,
    private val onMultiSelectionClicked: ((List<RadioGroupData>) -> Unit)?
) : RecyclerView.Adapter<MultiSelectionListAdapter.MultiselectionViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    var data: List<RadioGroupData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectionViewHolder {
        val binding = RowItemCheckableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MultiselectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MultiselectionViewHolder, position: Int) {
        holder.bind(data[position], position == selectedItemPosition)
    }

    override fun getItemCount(): Int = data.size

    inner class MultiselectionViewHolder(private val binding: RowItemCheckableBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    toggleItemSelection(position)
                }
            }
        }

        fun bind(data: RadioGroupData, isSelected: Boolean) {
            binding.text1.apply {
                isChecked = selectedItemsPositions.contains(adapterPosition)
                text = data.name
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

   protected fun setData(data: RadioGroupData) = data.name

    private fun applySelector(textView: CheckedTextView) {
        textView.setTextColor(primaryTextColor)
        textView.setCheckMarkDrawable(checkSelector)
    }
}