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
class MultiSelectionListAdapter(
    private val checkSelector: Drawable?,
    private val primaryTextColor: ColorStateList?,
    private val selectedItemsPositions: MutableList<Int>,
    private val onMultiSelectionClicked: ((List<Int>) -> Unit)?
) : RecyclerView.Adapter<MultiSelectionListAdapter.MultiselectionViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    var data: List<Data> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectionViewHolder {
        val binding = RowItemCheckableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MultiselectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MultiselectionViewHolder, position: Int) {
        holder.bind(data[position])
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

        fun bind(data: Data) {
            binding.ctv.apply {
                isChecked = selectedItemsPositions.contains(adapterPosition)
                text = data.name
                applySelector(this)
                setCompoundDrawablesWithIntrinsicBounds(0, data.image, 0, 0)
                setPaddingRelative(0, 30, 0, 30)

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
        onMultiSelectionClicked?.invoke(selectedItemsPositions)
    }


   protected fun setData(data: Data) = data.name

    private fun applySelector(textView: CheckedTextView) {
        textView.setTextColor(primaryTextColor)
        textView.background=(checkSelector?.constantState?.newDrawable()?.mutate())
        textView.compoundDrawableTintList=primaryTextColor
    }

}