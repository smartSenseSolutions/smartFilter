package com.ss.smartfilter.adapter

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.databinding.RowItemCheckableBinding


/**
 * created by Mala Ruparel ON 10/05/24
 */
class SingleSelectionListAdapter(
    private val checkSelector: Drawable?,
    private val viewTextColor: ColorStateList?,
    private val onItemSelectionChanged: ((Data) -> Unit)?
) : RecyclerView.Adapter<SingleSelectionListAdapter.SingleSelectionViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    var data: List<Data> = emptyList()
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

        fun bind(data: Data, isSelected: Boolean) {
            binding.apply {
                ctv.apply {
                    isChecked = isSelected
                    text = data.name
                    applySelector(this)
                    setCompoundDrawablesWithIntrinsicBounds(0, data.image, 0, 0)
                    setPaddingRelative(0, 30, 0, 30)
                }
            }
        }

        private fun applySelector(textView: CheckedTextView) {
            textView.setTextColor(viewTextColor)
            textView.background = (checkSelector?.constantState?.newDrawable()?.mutate())
            textView.compoundDrawableTintList = viewTextColor
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