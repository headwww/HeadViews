package com.head.view.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.head.view.R

class HeadSpinnerAdapter<T>(val context: Context, private val array: ArrayList<T>) : BaseAdapter() {

    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): T {
       return  array[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        var textView: TextView

        if (view ==null){
            view = View.inflate(context, R.layout.spinner_item,null).apply {
                textView= findViewById(R.id.text_view_spinner)
            }
            view.tag = ViewHolder(textView)
        }else{
            textView = (view.tag as ViewHolder).textView
        }

        return view
    }

    internal class ViewHolder(var textView: TextView)

}