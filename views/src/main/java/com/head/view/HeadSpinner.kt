package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.view.get
import com.head.view.adapter.HeadSpinnerAdapter

/**
 *
 * 类名称：HeadSpinner.kt <br/>
 * 类描述：下拉选择框 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/10 10:54 <br/>
 * @version
 */
class HeadSpinner<T> : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }


    private lateinit var dataSource: ArrayList<T>

    private lateinit var adapter: HeadSpinnerAdapter<T>

    private val popupWindow: ListPopupWindow by lazy { ListPopupWindow(context) }

    private var onItemClick: ((item: T, position: Int) -> Unit)? = null

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadSpinner)
        adapter = HeadSpinnerAdapter(context, null, null)
        popupWindow.setAdapter(adapter)

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.width = ListPopupWindow.WRAP_CONTENT
        popupWindow.height = ListPopupWindow.WRAP_CONTENT
        popupWindow.setOnItemClickListener { parent, view, position, id ->
            onItemClick?.invoke(dataSource[position], position)
            text =(view as TextView).text
            popupWindow.dismiss()
        }
        popupWindow.isModal = true
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isEnabled && event!!.action == MotionEvent.ACTION_UP) return if (!popupWindow.isShowing) showDropDown() else dismissDropDown()
        return super.onTouchEvent(event)
    }

    private fun showDropDown(): Boolean {
        popupWindow.anchorView = this
        popupWindow.show()
        return true
    }

    private fun dismissDropDown(): Boolean {
        popupWindow.dismiss()
        return true
    }

    fun setOnItemClickListener(onItemClick: ((item: T, position: Int) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    fun setData(array: ArrayList<T>) {
        dataSource = array
        adapter.setData(array)
    }

    fun setData(array: ArrayList<T>, format: (t: T) -> String) {
        dataSource = array
        adapter.setData(array)
        adapter.setItemFormat(format)
    }

    fun setItemFormat(format: (t: T) -> String) {
        adapter.setItemFormat(format)
    }


}