package com.ss.smartfilterlib.singalchoice.radiogroup

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.databinding.MultiLineRadioButtonBinding
import com.ss.smartfilterlib.singalchoice.callback.RadioGroupCallback
import com.ss.smartfilterlib.singalchoice.data.RadioGroupData
import kotlin.collections.ArrayList

class MultiLineRadioGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private var mList: ArrayList<RadioGroupData> = ArrayList()
    private var mAdapter: MultiLineRadioButtonAdapter? = null
    private var spanCount: Int = 3
    private var spacing: Int = 4
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

            override fun onRowLineCallBackSelected(radioGroupData: RadioGroupData) {}


            override fun singleLineCallBack(radioGroupData: RadioGroupData, radioGroup: RadioGroup, radioButton: RadioButton, checkId: Int) {}


        }
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.MultiLineRadioGroup, 0, 0)
        try {
            setSpanCount(typedArray.getInt(R.styleable.MultiLineRadioGroup_spanCount, spanCount))
            setSpacing(typedArray.getInt(R.styleable.MultiLineRadioGroup_spacing, spacing))
            setIncludeEdge(typedArray.getBoolean(R.styleable.MultiLineRadioGroup_includeEdge, includeEdge))
            textSelectorColor = typedArray.getColorStateList(R.styleable.MultiLineRadioGroup_textSelector)
            radioButtonDrawable = typedArray.getDrawable(R.styleable.MultiLineRadioGroup_background)

            val buttonText = typedArray.getTextArray(R.styleable.MultiLineRadioGroup_addList)
            //setData(buttonText.asList() as MutableList<String>)
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

    internal inner class MultiLineRadioButtonAdapter(private val context: Context) :
        Adapter<ViewHolder>() {
        var currentSelected: Int? = null
        var onChoseListener: RadioGroupCallback? = null

        internal inner class GridRadioHolder(itemView: MultiLineRadioButtonBinding) : ViewHolder(itemView.root) {
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
            val view = MultiLineRadioButtonBinding.inflate(LayoutInflater.from(context), parent, false)
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
            (holder as GridRadioHolder).setData(
                mList[position], position == currentSelected, function, position
            )
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        fun setData(newList: ArrayList<RadioGroupData>){
            val diffUtil = MultiLineDiffUtil(mList, newList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            mList = newList
            diffResult.dispatchUpdatesTo(this)
        }
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int,private val spacing: Int,private val includeEdge: Boolean) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount
            if (includeEdge){
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount){
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount){
                    outRect.top = spacing
                }
            }
        }
    }

    internal inner class MultiLineDiffUtil(private val oldList: ArrayList<RadioGroupData>, private val newList: ArrayList<RadioGroupData>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }
        override fun getNewListSize(): Int {
            return newList.size
        }
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList == newList
        }
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return false
    }
}