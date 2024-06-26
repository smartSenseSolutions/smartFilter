package com.ss.smartfilter.singleselection


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
import com.ss.smartfilter.data.Data
import com.ss.smartfilter.utils.BaseRecycleView
import com.ss.smartfilter.utils.Constant.DEFAULT_SPACING
import com.ss.smartfilter.utils.Constant.DEFAULT_SPAN_COUNT
import com.ss.smartfilter.utils.GridSpacingItemDecoration
import com.ss.smartfilter.utils.SingleChangeDiffUtil
import com.ss.smartfilter.R
import com.ss.smartfilter.databinding.RowItemMultiLineBinding

/**
 * SingleSelectionMultiLineRadioButton is a custom view that allows single selection from a list of options.
 * The options are presented as RadioButtons within a RecyclerView.
 * The RecyclerView is arranged in a grid layout.
 *
 * @property spanCount The number of columns in the grid layout.
 * @property spacing The spacing between items in the grid.
 * @property includeEdge Whether to include edge spacing in the grid.
 * @property viewTextSelector The ColorStateList for the text color of the RadioButtons.
 * @property viewBgSelector The Drawable for the background of the RadioButtons.
 * @property dataFromXml The resource ID of the string array containing the list items.
 * @property onSingleSelectionClicked The listener for click events of the RadioButtons.
 *
 * @constructor Creates a new SingleSelectionMultiLineRadioButton with the given context, attrs, and defStyle.
 */
class SingleSelectionMultiLineRadioButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : BaseRecycleView(context, attrs, defStyle){


    init {
        initAttributes(attrs=attrs)
        initializeView()
        populateDataFromAttributes()
    }
    override fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleSelectionView)
        with(typedArray) {
            try {
                setColumnCount(getInt(R.styleable.MultiLineRadioGroup_rg_ml_spancount,DEFAULT_SPAN_COUNT))
                setSpace(getInt(R.styleable.MultiLineRadioGroup_rg_ml_spacing, DEFAULT_SPACING))
                setEdge(getBoolean(R.styleable.MultiLineRadioGroup_rg_ml_includeedge, false))

                viewTextSelector = getColorStateList(R.styleable.MultiLineRadioGroup_rg_ml_text_selector)  ?: setDefaultTextColor()
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
        adapter = MultiLineRadioButtonAdapter(context,onSingleSelectionClicked).also { this.mAdapter = it }

    }
    private fun populateDataFromAttributes() {
        if (dataFromXml != 0) {
            resources.getStringArray(dataFromXml).forEach {
                mList.add(Data(name = it))
            }
            mAdapter?.setData(mList)
        }
    }


    fun configureRadioButton(mData: ArrayList<Data>,bgSelector: Int, textSelector: Int,checkedChangedListener: (Data) -> Unit,spanCount: Int = 2    ) {
        updateValue(bgSelector, textSelector, checkedChangedListener)
        setColumnCount(spanCount)
        mAdapter?.setData(mData)
    }

    private fun updateValue(checkSelector: Int,textSelector: Int, onCheckedChangeListener: ((Data) -> Unit)?) {
        this.viewBgSelector = checkSelector.let { ContextCompat.getDrawable(context, it) }
        this.viewTextSelector = textSelector.let { ContextCompat.getColorStateList(context, it) }
        this.onSingleSelectionClicked = onCheckedChangeListener

    }

    fun setColumnCount(spanCount: Int) {
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


    inner class MultiLineRadioButtonAdapter(private val context: Context, var onSingleSelectionClicked: ((Data) -> Unit)?) : Adapter<ViewHolder>() {
        var currentSelected: Int? = null


        internal inner class GridRadioHolder(itemView: RowItemMultiLineBinding) :
            ViewHolder(itemView.root) {
            private val binding = itemView

            fun setData(data: Data, selected: Boolean, function: (Int) -> Unit, position: Int) {
                binding.mlrb.apply {
                    text = setData(data)
                    applySelector(this)
                    isChecked = selected
                    setCompoundDrawablesWithIntrinsicBounds(0, data.image, 0, 0)
                    setPaddingRelative(0, 30, 0, 30)
                    setOnClickListener {
                        onSingleSelectionClicked?.invoke(data)
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

        fun setData(newList: ArrayList<Data>) {
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
        radioButton.background = (viewBgSelector?.constantState?.newDrawable()?.mutate())
        radioButton.compoundDrawableTintList = viewTextSelector

    }

    private fun setDefaultDrawable(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.multiline_bg_selector)
    }

    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.multiline_text_selector)
    }


    fun onCheckedChangeListener(onCheckedChangeListener: (Data) -> Unit) {
        this.onSingleSelectionClicked = onCheckedChangeListener
    }
}