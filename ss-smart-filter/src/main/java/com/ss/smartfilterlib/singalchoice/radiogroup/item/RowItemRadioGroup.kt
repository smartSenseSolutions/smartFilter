package com.ss.smartfilterlib.singalchoice.radiogroup.item

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import com.ss.smartfilterlib.R

/**
 * created by Mala Ruparel ON 17/04/24
 */
class RowItemRadioGroup : LinearLayout {
    // Attribute Variables
    private var mCheckedId = View.NO_ID
    private var mProtectFromCheckedChange = false
    // Variables
    private var mOnCheckedChangeListener: OnCheckedChangeListener? = null
    private val mChildViewsMap = HashMap<Int, View>()
    private val mPassThroughListener = PassThroughHierarchyChangeListener()
    private var mChildOnCheckedChangeListener: RadioCheckable.OnCheckedChangeListener? = null


    constructor(context: Context) : super(context) {
        setupView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        parseAttributes(attrs)
        setupView()
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        parseAttributes(attrs)
        setupView()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        parseAttributes(attrs)
        setupView()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        val a = getContext().obtainStyledAttributes(attrs,
            R.styleable.SSRadioGroupRowItemRadioGroup, 0, 0)
        try {
            mCheckedId = a.getResourceId(R.styleable.SSRadioGroupRowItemRadioGroup_presetRadioCheckedId, View.NO_ID)
        } finally {
            a.recycle()
        }
    }

    // Template method
    private fun setupView() {
        mChildOnCheckedChangeListener = CheckedStateTracker()
        super.setOnHierarchyChangeListener(mPassThroughListener)
    }


    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is RadioCheckable) {
            val button = child as RadioCheckable
            if (button.isChecked) {
                mProtectFromCheckedChange = true
                if (mCheckedId != View.NO_ID) {
                    setCheckedStateForView(mCheckedId, false)
                }
                mProtectFromCheckedChange = false
                setCheckedId(child.id, true)
            }
        }
        super.addView(child, index, params)
    }


    override fun setOnHierarchyChangeListener(listener: OnHierarchyChangeListener) {
        mPassThroughListener.mOnHierarchyChangeListener = listener
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (mCheckedId != View.NO_ID) {
            mProtectFromCheckedChange = true
            setCheckedStateForView(mCheckedId, true)
            mProtectFromCheckedChange = false
            setCheckedId(mCheckedId, true)
        }
    }

    private fun setCheckedId(@IdRes id: Int, isChecked: Boolean) {
        mCheckedId = id
        mOnCheckedChangeListener?.onCheckedChanged(this, mChildViewsMap[id], isChecked, mCheckedId)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    fun clearCheck() {
        check(View.NO_ID)
    }

    fun check(@IdRes id: Int) {
        if (id != View.NO_ID && (id == mCheckedId)) {
            return
        }

        if (mCheckedId != View.NO_ID) {
            setCheckedStateForView(mCheckedId, false)
        }

        if (id != View.NO_ID) {
            setCheckedStateForView(id, true)
        }

        setCheckedId(id, true)
    }

    private fun setCheckedStateForView(viewId: Int, checked: Boolean) {
        var checkedView = mChildViewsMap[viewId]
        if (checkedView == null) {
            checkedView = findViewById(viewId)
            if (checkedView != null) {
                mChildViewsMap[viewId] = checkedView
            }
        }
        if (checkedView != null && checkedView is RadioCheckable) {
            checkedView.isChecked = checked
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }


    fun setOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeListener?) {
        mOnCheckedChangeListener = onCheckedChangeListener
    }

    fun getOnCheckedChangeListener(): OnCheckedChangeListener? {
        return mOnCheckedChangeListener
    }


    interface OnCheckedChangeListener {
        fun onCheckedChanged(radioGroup: View?, radioButton: View?, isChecked: Boolean, checkedId: Int)
    }

    class LayoutParams : LinearLayout.LayoutParams {

        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)

        constructor(w: Int, h: Int) : super(w, h)

        constructor(w: Int, h: Int, initWeight: Float) : super(w, h, initWeight)
        constructor(p: ViewGroup.LayoutParams?) : super(p)

        constructor(source: MarginLayoutParams?) : super(source)


        override fun setBaseAttributes(
            a: TypedArray,
            widthAttr: Int, heightAttr: Int
        ) {
            width = if (a.hasValue(widthAttr)) {
                a.getLayoutDimension(widthAttr, "layout_width")
            } else {
                WRAP_CONTENT
            }
            height = if (a.hasValue(heightAttr)) {
                a.getLayoutDimension(heightAttr, "layout_height")
            } else {
                WRAP_CONTENT
            }
        }
    }

    inner class CheckedStateTracker : RadioCheckable.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: View, isChecked: Boolean) {
            // prevents from infinite recursion
            if (mProtectFromCheckedChange) {
                return
            }
            mProtectFromCheckedChange = true
            if (mCheckedId != NO_ID) {
                setCheckedStateForView(mCheckedId, false)
            }
            mProtectFromCheckedChange = false
            val id = buttonView.id
            setCheckedId(id, true)
        }
    }


    inner class PassThroughHierarchyChangeListener : OnHierarchyChangeListener {
        internal var mOnHierarchyChangeListener: OnHierarchyChangeListener? = null


        override fun onChildViewAdded(parent: View, child: View) {
            if (parent === this@RowItemRadioGroup && child is RadioCheckable) {
                var id = child.id
                // generates an id if it's missing
                if (id == NO_ID) {
                    id = View.generateViewId()
                    child.setId(id)
                }
                mChildOnCheckedChangeListener?.let {
                    (child as RadioCheckable).addOnCheckChangeListener(
                        it
                    )
                }
                mChildViewsMap.put(id, child)
            }
            mOnHierarchyChangeListener?.onChildViewAdded(parent, child)
        }


        override fun onChildViewRemoved(parent: View, child: View) {
            if (parent === this@RowItemRadioGroup && child is RadioCheckable) {
                mChildOnCheckedChangeListener?.let {
                    (child as RadioCheckable).removeOnCheckChangeListener(
                        it
                    )
                }
            }
            mChildViewsMap.remove(child.id)
            mOnHierarchyChangeListener?.onChildViewRemoved(parent, child)
        }
    }


}


