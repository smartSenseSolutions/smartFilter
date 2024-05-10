package com.ss.smartfilterlib.singleselection


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.callback.RadioGroupCallback
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.databinding.RowItemMultiLineBinding
import com.ss.smartfilterlib.utils.BaseRecycleView
import com.ss.smartfilterlib.utils.Constant.DEFAULT_SPACING
import com.ss.smartfilterlib.utils.Constant.DEFAULT_SPAN_COUNT
import com.ss.smartfilterlib.utils.GridSpacingItemDecoration
import com.ss.smartfilterlib.utils.SingleChangeDiffUtil

class SingleSelectionMultiLineRadioButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseRecycleView<RadioGroupData>(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }
    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.MultiLineRadioGroup, 0, 0)
        with(typedArray) {
            try {
                setColumnCount(getInt(R.styleable.MultiLineRadioGroup_rg_ml_spancount,DEFAULT_SPAN_COUNT))
                setSpace(getInt(R.styleable.MultiLineRadioGroup_rg_ml_spacing, DEFAULT_SPACING))
                setEdge(getBoolean(R.styleable.MultiLineRadioGroup_rg_ml_includeedge, false))

                viewTextSelector =getColorStateList(R.styleable.MultiLineRadioGroup_rg_ml_text_selector) ?: setDefaultTextColor()
                viewBgSelector = getDrawable(R.styleable.MultiLineRadioGroup_rg_ml_background) ?: setDefaultDrawable()

                dataFromXml = getResourceId(R.styleable.MultiLineRadioGroup_rg_ml_list_item, 0)

            } finally {
                typedArray.recycle()
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun initializeView() {
        layoutManager = GridLayoutManager(context, spanCount)
        addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        adapter = MultiLineRadioButtonAdapter(context).also { this.mAdapter = it }
        mAdapter?.onChoseListener = RadioGroupCallback { radioGroupData -> onSingleSelectionClicked?.invoke( radioGroupData) }
    }
    private fun populateDataFromAttributes(){
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            mData.forEach {
                val data = RadioGroupData(name = it)
                mList.add(data)
            }
            mAdapter?.setData(mList)
        }
    }


    fun configureRadioButton(mData: ArrayList<RadioGroupData>, bgSelector: Int, textSelector: Int,  checkedChangedListener: (RadioGroupData) -> Unit) {
       updateValue(bgSelector, textSelector, checkedChangedListener)
        mAdapter?.setData(mData)
    }
    private fun updateValue(checkSelector: Int, textSelector: Int,onCheckedChangeListener: ((RadioGroupData) -> Unit)?) {
        this.viewBgSelector = checkSelector.let { ContextCompat.getDrawable(context, it) }
        this.viewTextSelector = textSelector.let { ContextCompat.getColorStateList(context, it) }
        this.onSingleSelectionClicked = onCheckedChangeListener

    }
    private fun setColumnCount(spanCount: Int) {
        require(spanCount >= 2) { "spanCount minimum is 2, current: $spanCount" }
        this.spanCount = spanCount
        initializeView()
    }

    private fun setSpace(spacing: Int){
        this.spacing = spacing
        initializeView()
    }

    private fun setEdge(includeEdge: Boolean){
        this.includeEdge = includeEdge
        initializeView()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setChecked(position: Int){
        mAdapter.let {
            mAdapter?.currentSelected = position
            mAdapter?.notifyDataSetChanged()

        }
    }

    inner class MultiLineRadioButtonAdapter(private val context: Context) : Adapter<ViewHolder>() {
        var currentSelected: Int? = null
        var onChoseListener: RadioGroupCallback? = null

        internal inner class GridRadioHolder(itemView: RowItemMultiLineBinding) : ViewHolder(itemView.root) {
            private val binding = itemView

            fun setData(radioGroupData: RadioGroupData, selected: Boolean, function: (Int) -> Unit, position: Int) {
                binding.multiLineRadioGroupDefaultRadioButton.apply {
                    text = setData(radioGroupData)
                    applySelector(this)
                    isChecked = selected
                    setOnClickListener {
                        onChoseListener!!.onSingleSelection( radioGroupData)
                        function(position)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = RowItemMultiLineBinding.inflate(LayoutInflater.from(context), parent, false)
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


    private fun applySelector(radioButton: RadioButton) {
        radioButton.setTextColor(viewTextSelector)
        radioButton.buttonDrawable = viewBgSelector?.constantState?.newDrawable()?.mutate()
        radioButton.background = viewBgSelector?.constantState?.newDrawable()?.mutate()
    }
    private fun setDefaultDrawable() : Drawable?{
        return  ContextCompat.getDrawable(context, R.drawable.multiline_default,)
    }
    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }



    fun onCheckedChangeListener(onCheckedChangeListener: (RadioGroupData) -> Unit) {
        this.onSingleSelectionClicked = onCheckedChangeListener
    }
}