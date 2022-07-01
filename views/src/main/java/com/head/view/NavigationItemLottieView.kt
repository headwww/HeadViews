package com.head.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RestrictTo
import com.head.view.menu.MenuItemLottieImpl
import com.head.view.utils.CheckableLottieAnimationView


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class NavigationItemLottieView : FrameLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }
    private var mChecked = false
    private var backgroundResource: Drawable? = null
    private lateinit var menuItemImpl: MenuItemLottieImpl
    private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)


    /**
     * 标签
     */
    private val labelView: TextView by lazy {
        TextView(context).apply {
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

    private val lottieAnimationView: CheckableLottieAnimationView by lazy {
        CheckableLottieAnimationView(context).apply {
            isChecked = mChecked
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


    private fun init() {
        val typedArray =
            context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
        backgroundResource = typedArray.getDrawable(0)
        typedArray.recycle()
        linearLayout.addView(lottieAnimationView)
        linearLayout.addView(labelView)
        addView(linearLayout)
    }


    fun initialize(menuItemImpl: MenuItemLottieImpl) {
        this.menuItemImpl = menuItemImpl

        if (menuItemImpl.ripples) foreground = backgroundResource
        if (menuItemImpl.itemView.rawRes!=0){
            lottieAnimationView.setAnimation(menuItemImpl.itemView.rawRes)
        }

        labelView.setTextColor(
            ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_selected),
                    intArrayOf(-android.R.attr.state_selected)
                ),
                intArrayOf(
                    menuItemImpl.itemView.checkedLabelColor,
                    menuItemImpl.itemView.unCheckedLabelColor
                )
            )
        )
        setChecked(menuItemImpl.checked)

    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        return if (!mChecked) {
            super.onCreateDrawableState(extraSpace)
        } else {
            mergeDrawableStates(super.onCreateDrawableState(extraSpace + 1), CHECKED_STATE_SET)
        }
    }


    private fun isChecked(): Boolean {
        return mChecked
    }

    private fun setLabel() {
        labelView.text =
            if (isChecked()) menuItemImpl.itemView.checkedLabel else menuItemImpl.itemView.unCheckedLabel
        labelView.visibility =
            if (TextUtils.isEmpty(labelView.text.toString())) View.GONE else VISIBLE
    }

    private fun setChecked(checked: Boolean) {
        if (checked != mChecked) {
            mChecked = checked
            refreshDrawableState()
            isSelected = isChecked()
        }
        lottieAnimationView.isChecked = menuItemImpl.checked
        labelView.isSelected = menuItemImpl.checked
        setLabel()
    }


}