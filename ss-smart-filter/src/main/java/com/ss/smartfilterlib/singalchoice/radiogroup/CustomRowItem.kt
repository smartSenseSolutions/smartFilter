package com.ss.smartfilterlib.singalchoice.radiogroup


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ss.smartfilterlib.R


/**
 * created by Mala Ruparel ON 19/04/24
 */

class CustomRowItem : LinearLayout {
    private var imageView: ImageView? = null
    private var textView: TextView? = null

    constructor(context: Context) : super(context) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeViews(context)
    }

    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_row_item, this)
        imageView = findViewById<ImageView>(R.id.imageView)
        textView = findViewById<TextView>(R.id.textView)
    }

    fun setImage(resourceId: Int) {
        imageView!!.setImageResource(resourceId)
    }

    fun setText(text: String?) {
        textView!!.text = text
    }
    fun setTextSize(textSize : Float) {
        textView!!.textSize = textSize
    }
    fun setTextColor(textColor : Int) {
        textView!!.setTextColor(textColor)
    }
    override fun setSelected(selected: Boolean) {
        if (selected) {
          //  setBackgroundColor(resources.getColor(R.color.selected_item_background))
        } else {
           // setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
    }
}