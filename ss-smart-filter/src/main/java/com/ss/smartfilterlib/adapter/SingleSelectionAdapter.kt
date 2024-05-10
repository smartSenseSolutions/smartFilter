package com.ss.smartfilterlib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.databinding.RowItemCheckableBinding

/**
 * created by Mala Ruparel ON 10/05/24
 */
class SingleSelectionListAdapter(
    private val checkSelector: Int,
    private val primaryTextColor: Int,
    private val onItemSelectionChanged: ((RadioGroupData) -> Unit)?
) : RecyclerView.Adapter<SingleSelectionListAdapter.SingleSelectionViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    var data: List<RadioGroupData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleSelectionViewHolder {
        val binding = RowItemCheckableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SingleSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleSelectionViewHolder, position: Int) {
        holder.bind(data[position], position == selectedItemPosition)
    }

    override fun getItemCount(): Int = data.size

    inner class SingleSelectionViewHolder(private val binding: RowItemCheckableBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    selectItem(position)
                }
            }
        }

        fun bind(data: RadioGroupData, isSelected: Boolean) {
            binding.apply {
                text1.apply {
                    isChecked = isSelected
                    text = data.name
                    applySelector(this)
                }
            }
        }

        private fun applySelector(textView: CheckedTextView) {
            textView.setTextColor(primaryTextColor)
            textView.setCheckMarkDrawable(checkSelector)
        }
    }

    private fun selectItem(position: Int) {
        if (selectedItemPosition != position) {
            val oldPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedItemPosition)
            onItemSelectionChanged?.let { it(data[selectedItemPosition]) }
        }
    }
}