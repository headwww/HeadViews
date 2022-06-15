package com.head.view.style

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.head.view.R

/**
 *
 * 类名称： GeneralModeTitle<br/>
 * 类描述： 通用标题<br/>
 * 创建人： <br/>
 * 创建时间：  <br/>
 * @version
 */
class GeneralModeTitle(val context: Context) {
    val leftTextView = TextView(context)


    inner class Builder {

        fun setLeftViewText(text: String): Builder {
            leftTextView.text = text
            return this
        }

        fun setLeftViewIcon(leftRes: Int): Builder {
            val drawable = ContextCompat.getDrawable(context, leftRes)
            drawable?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
            leftTextView.setCompoundDrawables(drawable, null, null, null)
            return this
        }

        fun build(): View {
            return FrameLayout(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                leftTextView.id = R.id.general_mode_title_left_textview
                addView(leftTextView,0)
            }


        }

    }


}