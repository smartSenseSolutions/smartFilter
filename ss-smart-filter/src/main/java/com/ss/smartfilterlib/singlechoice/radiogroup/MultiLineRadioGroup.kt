package com.ss.smartfilterlib.singlechoice.radiogroup

import RadioGroupCallback
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.databinding.MultiLineBinding
import com.ss.smartfilterlib.singlechoice.radiogroup.data.RadioGroupData
import com.ss.smartfilterlib.singlechoice.util.GridSpacingItemDecoration
import com.ss.smartfilterlib.singlechoice.util.SingleChangeDiffUtil

class MultiLineRadioGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private var mList: ArrayList<RadioGroupData> = ArrayList()
    private var mAdapter: MultiLineRadioButtonAdapter? = null
    private var spanCount: Int = resources.getInteger(R.integer.int_03)
    private var spacing: Int = resources.getInteger(R.integer.int_04)
    private var includeEdge: Boolean = false
    private var setOnChoseListener: RadioGroupCallback? = null
    private var textSelectorColor: ColorStateList? = null
    private var radioButtonDrawable: Drawable? = null
    init {
        initAttrs(attrs)
        setupView()
    }
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.MultiLineRadioGroup, 0, 0)
        try {
            setSpanCount(typedArray.getInt(R.styleable.MultiLineRadioGroup_rg_ml_SpanCount, spanCount))
            setSpacing(typedArray.getInt(R.styleable.MultiLineRadioGroup_rg_ml_Spacing, spacing))
            setIncludeEdge(typedArray.getBoolean(R.styleable.MultiLineRadioGroup_rg_ml_IncludeEdge, includeEdge))

            textSelectorColor = typedArray.getColorStateList(R.styleable.MultiLineRadioGroup_rg_ml_TextSelector)
            radioButtonDrawable = typedArray.getDrawable(R.styleable.MultiLineRadioGroup_rg_ml_Background)


        } finally {
            typedArray.recycle()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupView() {
        layoutManager = GridLayoutManager(context, spanCount)
        addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        adapter = MultiLineRadioButtonAdapter(context).also { this.mAdapter = it }
        mAdapter?.onChoseListener = RadioGroupCallback { radioGroupData -> setOnChoseListener?.onSingleSelection(radioGroupData) }
    }



    fun setData(mData: ArrayList<RadioGroupData>, bgSelector: Int?, textSelector: Int?, callback: RadioGroupCallback) {
        this.radioButtonDrawable = bgSelector?.let { ContextCompat.getDrawable(context, it) }
        this.textSelectorColor = textSelector?.let { ContextCompat.getColorStateList(context, it) }
        setOnChoseListener = callback
        mAdapter?.setData(mData)
    }

    private fun setSpanCount(spanCount: Int) {
        require(spanCount >= 2) { "spanCount minimum is 2, current: $spanCount" }
        this.spanCount = spanCount
        setupView()
    }

    private fun setSpacing(spacing: Int){
        this.spacing = spacing
        setupView()
    }

    private fun setIncludeEdge(includeEdge: Boolean){
        this.includeEdge = includeEdge
        setupView()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setChecked(position: Int){
        mAdapter.let {
            mAdapter?.currentSelected = position
            mAdapter?.notifyDataSetChanged()

        }
    }

    internal inner class MultiLineRadioButtonAdapter(private val context: Context) : Adapter<ViewHolder>() {
        var currentSelected: Int? = null
        var onChoseListener: RadioGroupCallback? = null

        internal inner class GridRadioHolder(itemView: MultiLineBinding) : ViewHolder(itemView.root) {
            private val binding = itemView

            fun setData(radioGroupData: RadioGroupData, selected: Boolean, function: (Int) -> Unit, position: Int) {
                binding.multiLineRadioGroupDefaultRadioButton.apply {
                    text = radioGroupData.name
                    background = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
                    buttonDrawable = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
                    setTextColor(textSelectorColor)
                    isChecked = selected
                    setOnClickListener {
                        onChoseListener!!.onSingleSelection( radioGroupData)
                        function(position)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = MultiLineBinding.inflate(LayoutInflater.from(context), parent, false)
            return GridRadioHolder(view)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val function = { pos: Int ->
                if (currentSelected == null || currentSelected != pos) {
                    currentSelected = pos
                    notifyDataSetChanged()
                }
            }
            (holder as GridRadioHolder).setData(mList[position], position == currentSelected, function, position)
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        fun setData(newList: ArrayList<RadioGroupData>){
            val diffUtil = SingleChangeDiffUtil(mList, newList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            mList = newList
            diffResult.dispatchUpdatesTo(this)
        }
    }



    override fun canScrollVertically(direction: Int): Boolean {
        return false
    }
}