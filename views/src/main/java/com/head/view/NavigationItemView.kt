package com.head.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat
import com.head.view.menu.MenuItemImpl


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class NavigationItemView : FrameLayout {
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

    private var selectDrawable: Drawable? = null
    private var unSelectDrawable: Drawable? = null
    private var mChecked = false
    private var backgroundResource: Drawable? = null
    private lateinit var menuItemImpl: MenuItemImpl
    private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    private val CHECKED_STATE_SELECTED = intArrayOf(android.R.attr.state_selected)
    private val UN_CHECKED_STATE_SELECTED = intArrayOf(-android.R.attr.state_selected)

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray =
            context.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
        backgroundResource = typedArray.getDrawable(0)
        typedArray.recycle()
        linearLayout.addView(icon)
        linearLayout.addView(labelView)
        addView(linearLayout)
    }


    fun initialize(menuItemImpl: MenuItemImpl) {
        this.menuItemImpl = menuItemImpl

        if (menuItemImpl.ripples) foreground = backgroundResource

        setChecked(menuItemImpl.checked)

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

        selectDrawable = ContextCompat.getDrawable(context, menuItemImpl.itemView.checkedIcon)
        unSelectDrawable = ContextCompat.getDrawable(context, menuItemImpl.itemView.unCheckedIcon)
        val iconDrawable = StateListDrawable().apply {
            addState(
                CHECKED_STATE_SELECTED,
                selectDrawable
            )
            addState(
                UN_CHECKED_STATE_SELECTED,
                unSelectDrawable
            )
        }
        icon.background = iconDrawable
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        return if (!mChecked) {
            if (unSelectDrawable is AnimationDrawable) (unSelectDrawable as AnimationDrawable).start()
            super.onCreateDrawableState(extraSpace)
        } else {
            if (selectDrawable is AnimationDrawable) (selectDrawable as AnimationDrawable).start()
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
            icon.background = StateListDrawable().apply {
                addState(
                    CHECKED_STATE_SELECTED,
                    selectDrawable
                )
                addState(
                    UN_CHECKED_STATE_SELECTED,
                    unSelectDrawable
                )
            }
            refreshDrawableState()
            isSelected = isChecked()
        }
        icon.isSelected = menuItemImpl.checked
        labelView.isSelected = menuItemImpl.checked
        setLabel()
    }


}