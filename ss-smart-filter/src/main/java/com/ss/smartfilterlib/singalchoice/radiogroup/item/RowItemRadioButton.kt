package com.ss.smartfilterlib.singalchoice.radiogroup.item

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 17/04/24
 */
class RowItemRadioButton @JvmOverloads constructor(context: Context,attrs: AttributeSet? = null,defStyleAttr: Int = 0,defStyleRes: Int = 0) : RelativeLayout(context, attrs, defStyleAttr, defStyleRes), RadioCheckable {


    private lateinit var mtvTitle: AppCompatTextView
    private lateinit var mtvDesc: AppCompatTextView

    private val defult_Text_Colour = Color.TRANSPARENT

    private var mTitleValue: String? = null
    private var mDescriptionValue: String? = null
    private var mTitleTextColor = 0
    private var mDesciptionTextColor = 0
    private var mPressedTextColor = 0


    private var mInitialBackgroundDrawable: Drawable? = null
    private var mOnClickListener: OnClickListener? = null
    private var mOnTouchListener: OnTouchListener? = null
    private var mChecked = false
    private val mOnCheckedChangeListeners: MutableList<RadioCheckable.OnCheckedChangeListener> = ArrayList()

    init {
        parseAttributes(attrs)
        setupView()
    }


    private fun parseAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SSRadioGroupRowItemButton, 0, 0)
        val resources: Resources = context.resources
        try {
            mTitleValue = typedArray.getString(R.styleable.SSRadioGroupRowItemButton_presetButtonValueText)
            mDescriptionValue = typedArray.getString(R.styleable.SSRadioGroupRowItemButton_presetButtonUnitText)
            mTitleTextColor = typedArray.getColor(
                R.styleable.SSRadioGroupRowItemButton_presetButtonValueTextColor, resources.getColor(
                    R.color.black
                )
            )
            mPressedTextColor =
                typedArray.getColor(R.styleable.SSRadioGroupRowItemButton_presetButtonPressedTextColor, Color.BLACK)
            mDesciptionTextColor = typedArray.getColor(
                R.styleable.SSRadioGroupRowItemButton_presetButtonUnitTextColor, resources.getColor(
                    R.color.purple_200
                )
            )
        } finally {
            typedArray.recycle()
        }
    }


    private fun setupView() {
        inflateView()
        bindView()
        setCustomTouchListener()
    }

    private fun inflateView() {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_radiogroup_button, this, true)
        mtvTitle = findViewById(R.id.tvTitle)
        mtvDesc = findViewById(R.id.tvDescription)
        mInitialBackgroundDrawable = background
    }

    private fun bindView() {
        if (mDesciptionTextColor != defult_Text_Colour) {
            mtvDesc.setTextColor(mDesciptionTextColor)
        }
        if (mTitleTextColor != defult_Text_Colour) {
            mtvTitle.setTextColor(mTitleTextColor)
        }
        mtvDesc.text = mDescriptionValue
        mtvTitle.text = mTitleValue
    }


    override fun setOnClickListener(l: OnClickListener?) {
        mOnClickListener = l
    }

    private fun setCustomTouchListener() {
        super.setOnTouchListener(TouchListener())
    }

    override fun setOnTouchListener(onTouchListener: OnTouchListener?) {
        mOnTouchListener = onTouchListener
    }

    fun getOnTouchListener(): OnTouchListener? {
        return mOnTouchListener
    }

    private fun onTouchDown(motionEvent: MotionEvent) {
        isChecked = true
    }

    private fun onTouchUp(motionEvent: MotionEvent) {
        mOnClickListener?.onClick(this)
    }


    private fun setCheckedState() {
        setBackgroundResource(R.drawable.row_item_pressed)
        mtvTitle.setTextColor(mPressedTextColor)
        mtvDesc.setTextColor(mPressedTextColor)
    }

    private fun setNormalState() {
        background = mInitialBackgroundDrawable
        mtvTitle.setTextColor(mTitleTextColor)
        mtvDesc.setTextColor(mDesciptionTextColor)
    }

    fun getValue(): String? {
        return mTitleValue
    }

    fun setValue(value: String) {
        mTitleValue = value
    }

    fun getUnit(): String? {
        return mDescriptionValue
    }

    fun setUnit(unit: String) {
        mDescriptionValue = unit
    }


    override fun setChecked(checked: Boolean) {
        if (mChecked != checked) {
            mChecked = checked
            if (mOnCheckedChangeListeners.isNotEmpty()) {
                for (i in mOnCheckedChangeListeners.indices) {
                    mOnCheckedChangeListeners[i].onCheckedChanged(this, mChecked)
                }
            }
            if (mChecked) {
                setCheckedState()
            } else {
                setNormalState()
            }
        }
    }

    override fun isChecked(): Boolean {
        return mChecked
    }

    override fun toggle() {
        isChecked = !mChecked
    }


    override fun addOnCheckChangeListener(onCheckedChangeListener: RadioCheckable.OnCheckedChangeListener) {
        mOnCheckedChangeListeners.add(onCheckedChangeListener)
    }

    override fun removeOnCheckChangeListener(onCheckedChangeListener: RadioCheckable.OnCheckedChangeListener) {
        mOnCheckedChangeListeners.remove(onCheckedChangeListener)
    }


    private inner class TouchListener : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> onTouchDown(event)
                MotionEvent.ACTION_UP -> onTouchUp(event)
            }
            mOnTouchListener?.onTouch(v, event)
            return true
        }
    }
}