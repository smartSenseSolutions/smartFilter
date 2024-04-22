package com.ss.smartfilterlib.singalchoice.radiogroup

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.databinding.MultiLineBinding
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData
import com.ss.smartfilterlib.singalchoice.util.GridSpacingItemDecoration
import com.ss.smartfilterlib.singalchoice.util.SingleChangeDiffUtil

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

    @SuppressLint("NotifyDataSetChanged")
    private fun setupView() {
        layoutManager = GridLayoutManager(context, spanCount)
        addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        adapter = MultiLineRadioButtonAdapter(context).also { this.mAdapter = it }
        mAdapter?.onChoseListener = object : RadioGroupCallback {
            override fun multiLineCallBack(position: Int, data: RadioGroupData) {
                mAdapter?.currentSelected = position
                mAdapter?.notifyDataSetChanged()
                setOnChoseListener?.multiLineCallBack(position, data)
            }

            override fun onRowLineCallBackSelected(radioGroupData: RadioGroupData) { // Noncompliant - method is empty
                 }


            override fun singleLineCallBack(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int) {
                // Noncompliant - method is empty
            }


        }
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

    fun setData(mData: ArrayList<RadioGroupData>, bgSelector: Int, textSelector: Int, callback: RadioGroupCallback) {
        radioButtonDrawable = ContextCompat.getDrawable(context, bgSelector)
        textSelectorColor = ContextCompat.getColorStateList(context, textSelector)
        setOnChoseListener = callback
        mAdapter?.setData(mData)
    }

    private fun setSpanCount(spanCount: Int){
        if (spanCount < 2){
            throw IllegalArgumentException("spanCount minimum is 2, current: $spanCount")
        }
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
                binding.multiLineRadioGroupDefaultRadioButton.text = radioGroupData.name
               // binding.multiLineRadioGroupDefaultRadioButton.background = radioButtonDrawable
               // binding.multiLineRadioGroupDefaultRadioButton.buttonDrawable = radioButtonDrawable
                binding.multiLineRadioGroupDefaultRadioButton.setTextColor(textSelectorColor)
                binding.multiLineRadioGroupDefaultRadioButton.isChecked = selected
                binding.multiLineRadioGroupDefaultRadioButton.setOnClickListener {
                    onChoseListener!!.multiLineCallBack(
                        position, radioGroupData
                    )
                    function(position)
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