package com.head.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.head.view.R

class HeadSpinnerAdapter<T>(
    val context: Context, private var array: ArrayList<T>? = null,
    private var format: ((t: T) -> String)? = null
) : BaseAdapter() {

    override fun getCount(): Int {
       return array?.size ?: 0
    }

    override fun getItem(position: Int): T? {
        return array?.get(position)?:null
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
        textView.text =
            if (format == null) getItem(position).toString() else getItem(position)?.let {
                format!!.invoke(
                    it
                )
            }
        return view
    }

   fun setData( array: ArrayList<T>){
       this.array=array
       notifyDataSetChanged()
   }

    fun setItemFormat(format:(t:T)->String){
        this.format = format
        notifyDataSetChanged()
    }
    internal class ViewHolder(var textView: TextView)

}