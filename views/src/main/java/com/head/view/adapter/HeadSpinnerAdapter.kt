package com.head.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.head.view.HeadSpinner
import com.head.view.R

class HeadSpinnerAdapter<T>(
    val context: Context, private var array: List<T>? = null,
    private var format: ((t: T) -> String)? = null
) : BaseAdapter() {

    private var drawables: MutableList<Drawable?> = arrayListOf()
    private var color: Int = ContextCompat.getColor(context, R.color.spinner_text_default_color)
    private var size: Float = context.resources.getDimension(R.dimen.spinner_item_default_text_size)
    private var gravity: Int = -3


    override fun getCount(): Int {
        return array?.size ?: 0
    }

    override fun getItem(position: Int): T? {
        return array?.get(position) ?: null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        var textView: TextView

        if (view == null) {
            view = View.inflate(context, R.layout.spinner_item, null).apply {
                textView = findViewById(R.id.text_view_spinner)
            }
            view.tag = ViewHolder(textView)
        } else {
            textView = (view.tag as ViewHolder).textView
        }
        textView.gravity = when (gravity) {
            HeadSpinner.Gravity.LEFT.ordinal -> Gravity.LEFT
            HeadSpinner.Gravity.RIGHT.ordinal -> Gravity.RIGHT
            else -> Gravity.CENTER
        }
        textView.paint.textSize =size
        textView.setTextColor(color)

        if (drawables.size > position) {
            val drawable = drawables[position]
            drawable?.let {
                it.setBounds(
                    0,
                    0,
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight
                )
                textView.setCompoundDrawables(drawable, null, null, null)
            }
        } else {
            textView.setCompoundDrawables(null, null, null, null)
        }



        textView.text =
            if (format == null) getItem(position).toString() else getItem(position)?.let {
                format!!.invoke(
                    it
                )
            }
        return view
    }

    fun setData(array: List<T>) {
        this.array = array
        notifyDataSetChanged()
    }

    fun setDropDownGravity(gravity: Int) {
        this.gravity = gravity
        notifyDataSetChanged()
    }


    fun setItemFormat(format: (t: T) -> String) {
        this.format = format
        notifyDataSetChanged()
    }

    fun setTextSize(size: Float) {
        this.size = size
        notifyDataSetChanged()
    }

    fun setTextColor(@ColorInt color: Int) {
        this.color = color
        notifyDataSetChanged()
    }

    fun seItemIcon(drawables: MutableList<Drawable?>) {
        this.drawables = drawables
    }

    internal class ViewHolder(var textView: TextView)

}