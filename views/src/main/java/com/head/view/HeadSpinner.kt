package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.ListPopupWindow
import com.head.view.adapter.HeadSpinnerAdapter
import com.head.view.utils.TemplateDrawable

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


    private var position: Int =-1
    private var format:( (t: T) -> String)?= null
    private var dataSource: ArrayList<T> = arrayListOf()

    private val adapter: HeadSpinnerAdapter<T> by lazy {
        HeadSpinnerAdapter(context, null, null)
    }

    private val popupWindow: ListPopupWindow by lazy { ListPopupWindow(context) }

    private var onItemClick: ((item: T, position: Int) -> Unit)? = null

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadSpinner)
        popupWindow.setAdapter(adapter)
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.width = ListPopupWindow.WRAP_CONTENT
        popupWindow.height = ListPopupWindow.WRAP_CONTENT
        popupWindow.setOnItemClickListener { parent, view, position, id ->
            this.position = position
            onItemClick?.invoke(dataSource[position], position)
            text =(view as TextView).text
            popupWindow.dismiss()
        }
        popupWindow.isModal = true
        popupWindow.setBackgroundDrawable(TemplateDrawable(context, backgroundColor = Color.WHITE, radians = 30))
    }


    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        val parcelable=super.onSaveInstanceState()
        bundle.putParcelable(SUPER_HEAD_SPINNER, parcelable)
        bundle.putSerializable(SAVE_HEAD_SPINNER, text.toString())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val bundle = ( state as Bundle)
        val parcelable : Parcelable? = bundle.getParcelable(SUPER_HEAD_SPINNER)
        text = bundle.getSerializable(SAVE_HEAD_SPINNER).toString()
        super.onRestoreInstanceState(parcelable)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isEnabled && event!!.action == MotionEvent.ACTION_UP){
            if (!popupWindow.isShowing) showDropDown() else dismissDropDown()
        }
        return true
    }

    private fun showDropDown() {
        popupWindow.anchorView = this
        popupWindow.show()
    }

    private fun dismissDropDown() {
        popupWindow.dismiss()
    }

    fun setOnItemClickListener(onItemClick: ((item: T, position: Int) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    fun setData(array: ArrayList<T>) {
        dataSource = array
        adapter.setData(array)
        setSelection(position)
    }

    fun setData(array: ArrayList<T>, format: (t: T) -> String) {
        this.format = format
        dataSource = array
        adapter.setData(array)
        adapter.setItemFormat(format)
        setSelection(position)
    }

    fun setItemFormat(format: (t: T) -> String) {
        this.format = format
        adapter.setItemFormat(format)
    }

    /**
     * 设置默认选中项
     */
    fun setSelection(position: Int){
        this.position=position
        if (0<position&&position<dataSource.size){
            text = if (format == null) dataSource[position].toString() else dataSource[position]?.let { format!!.invoke(it) }
        }
    }


    fun getSelection():Int{
        return position
    }
}

private const val SUPER_HEAD_SPINNER = "super_head_Spinner"

private const val SAVE_HEAD_SPINNER = "save_head_Spinner"