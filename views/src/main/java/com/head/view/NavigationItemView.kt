package com.head.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RestrictTo
import com.head.view.menu.ItemViewState


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class NavigationItemView : FrameLayout, ItemViewState {
    // 代表选中状态的集合
    private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)

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

    /**
     * 是否选中
     */
    private var mChecked = false

    /**
     * 提醒角标
     */
//    private val badge: TextView by lazy {
//        TextView(context).apply {
//            layoutParams = LayoutParams(
//                300,
//                300,
//                Gravity.CENTER
//            )
//
//        }
//    }

    /**
     * 标签
     */
    private val labelView: TextView by lazy {
        TextView(context).apply {
            text = "测试的数据"
            paint.textSize = resources.getDimension(R.dimen.head_view_default_size)
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            gravity = Gravity.CENTER
            isSingleLine = true
            ellipsize = null
        }

    }

    /**
     * 图标
     */
    private val icon: ImageView by lazy {
        ImageView(context).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            setImageDrawable(context.resources.getDrawable(R.drawable.head_edit_text_clear))
        }
    }

    val linearLayout: LinearLayout by lazy {
        LinearLayout(context).apply {
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
    }

//    设置点击水波纹效果
//    val attrs = intArrayOf(android.R.attr.selectableItemBackground)
//    val typedArray = context.obtainStyledAttributes(attrs)
//    val backgroundResource = typedArray.getDrawable(0)
//    foreground  = backgroundResource
//    typedArray.recycle()

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        linearLayout.addView(icon)
        linearLayout.addView(labelView)
        val attrs = intArrayOf(android.R.attr.selectableItemBackground)
        val typedArray = context.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getDrawable(0)
        foreground = backgroundResource
        typedArray.recycle()
        addView(linearLayout)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        return if (!mChecked) {
//            Log.e("TAG", "onCreateDrawableState: 1")
            labelView.text = "未选中"
            super.onCreateDrawableState(extraSpace);

        } else {
            // 如果选中，将父类的结果和选中状态合并之后返回
//            Log.e("TAG", "onCreateDrawableState: 2")
            labelView.text = "选中"

            // Set the item as selected to send an AccessibilityEvent.TYPE_VIEW_SELECTED from View, so that
            // the item is read out as selected.

            mergeDrawableStates(super.onCreateDrawableState(extraSpace + 1), CHECKED_STATE_SET);
        }
    }


    override fun isChecked(): Boolean {
        return mChecked
    }


    override fun setChecked(checked: Boolean) {
        if (checked != mChecked) {
            mChecked = checked
            refreshDrawableState()
            isSelected = isChecked()
        }
    }

    fun toggle() {
        setChecked(!mChecked)
    }


}