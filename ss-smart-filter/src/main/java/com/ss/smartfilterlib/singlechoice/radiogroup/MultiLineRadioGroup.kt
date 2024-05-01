package com.ss.smartfilterlib.singlechoice.radiogroup
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.smartfilterlib.R
import com.ss.smartfilterlib.databinding.MultiLineBinding
import com.ss.smartfilterlib.callback.RadioGroupCallback
import com.ss.smartfilterlib.data.RadioGroupData
import com.ss.smartfilterlib.utils.GridSpacingItemDecoration
import com.ss.smartfilterlib.utils.PaddingAttributes
import com.ss.smartfilterlib.utils.SingleChangeDiffUtil
import com.ss.smartfilterlib.utils.TextAttributes

class MultiLineRadioGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private var textSelectorColor: ColorStateList? = null
    private var radioButtonDrawable: Drawable? = null

    private var dataFromXml: Int = 0
    private var onCheckedChangeListener: RadioGroupCallback? = null

    private var mList: ArrayList<RadioGroupData> = ArrayList()
    private var mAdapter: MultiLineRadioButtonAdapter? = null
    private var spanCount: Int = resources.getInteger(R.integer.int_03)
    private var spacing: Int = resources.getInteger(R.integer.int_04)
    private var includeEdge: Boolean = false

    private var textAttributes: TextAttributes = TextAttributes()
    private var paddingAttributes: PaddingAttributes = PaddingAttributes()


    init {
        initAttrs(attrs)
        setupView()
        setDataFromAttrs()
    }
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.MultiLineRadioGroup, 0, 0)
        try {
            setSpanCount(typedArray.getInt(R.styleable.MultiLineRadioGroup_rg_ml_SpanCount, spanCount))
            setSpacing(typedArray.getInt(R.styleable.MultiLineRadioGroup_rg_ml_Spacing, spacing))
            setIncludeEdge(typedArray.getBoolean(R.styleable.MultiLineRadioGroup_rg_ml_IncludeEdge, includeEdge))

            textSelectorColor = typedArray.getColorStateList(R.styleable.MultiLineRadioGroup_rg_ml_TextSelector)
            radioButtonDrawable = typedArray.getDrawable(R.styleable.MultiLineRadioGroup_rg_ml_Background)

            dataFromXml = typedArray.getResourceId(R.styleable.MultiLineRadioGroup_rg_ml_Listitem, 0)

        } finally {
            typedArray.recycle()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupView() {
        layoutManager = GridLayoutManager(context, spanCount)
        addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        adapter = MultiLineRadioButtonAdapter(context).also { this.mAdapter = it }
        mAdapter?.onChoseListener = RadioGroupCallback { radioGroupData -> onCheckedChangeListener?.onSingleSelection(radioGroupData) }
    }
    private fun setDataFromAttrs(){
        if (dataFromXml != 0) {
            val mData = resources.getStringArray(dataFromXml);
            mData.forEach {
                val data = RadioGroupData(name = it)
                mList.add(data)
            }
            mAdapter?.setData(mList)
        }
    }


    fun configureRadioButton(mData: ArrayList<RadioGroupData>, bgSelector: Int?, textSelector: Int?, callback: RadioGroupCallback) {
        this.radioButtonDrawable = bgSelector?.let { ContextCompat.getDrawable(context, it) }
        this.textSelectorColor = textSelector?.let { ContextCompat.getColorStateList(context, it) }
        onCheckedChangeListener = callback
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
                    text = setData(radioGroupData)
                    applyTextAttributes(this)
                    applyPaddingAttributes(this)
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
    private fun setData(data: RadioGroupData) = data.name

    private fun applySelector(radioButton: RadioButton) {
        if(textSelectorColor == null){
            textSelectorColor= setDefaultTextColor()
        }
        if(radioButtonDrawable == null){
            radioButtonDrawable=setDefaultDrawable()
        }
        radioButton.setTextColor(textSelectorColor)
        radioButton.buttonDrawable = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
        radioButton.background = radioButtonDrawable?.constantState?.newDrawable()?.mutate()
    }
    private fun setDefaultDrawable() : Drawable?{
        return  ContextCompat.getDrawable(context,R.drawable.multiline_default,)
    }
    private fun setDefaultTextColor(): ColorStateList? {
        return ContextCompat.getColorStateList(context, R.color.black)
    }
    private fun applyTextAttributes(radioButton: RadioButton) {
        textAttributes.let { attributes ->
            radioButton.apply {
                textSize = attributes.textSize
                typeface = Typeface.create(Typeface.MONOSPACE, attributes.fontFamily)
            }
        }
    }

    private fun applyPaddingAttributes(radioButton: RadioButton) {
        paddingAttributes.let { attributes ->
            radioButton.setPadding(
                attributes.paddingStart,
                attributes.paddingTop,
                attributes.paddingEnd,
                attributes.paddingBottom
            )
        }
    }
}