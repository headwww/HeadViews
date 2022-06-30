package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.head.view.menu.ItemView
import com.head.view.menu.MenuItemImpl

/**
 *
 * 类名称：HeadBottomNavigation.kt <br/>
 * 类描述：底部导航栏 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/23 13:55 <br/>
 * @version
 */
class HeadBottomNavigation : LinearLayout {

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


    private var headBottomNavigationClickRipples: Boolean = true

    private var itemViews: MutableList<ItemView> = ArrayList()

    private var menuItemImpl: MutableList<MenuItemImpl> = ArrayList()

    private var position: Int = 0

    private var onItemSelectedListener: ((v: ViewGroup, position: Int) -> Unit)? = null

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        orientation = HORIZONTAL
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.HeadBottomNavigation)
        headBottomNavigationClickRipples = typedArray.getBoolean(
            R.styleable.HeadBottomNavigation_HeadBottomNavigationClickRipples,
            true
        )
        typedArray.recycle()

    }

    fun getSelectPosition(): Int {
        return position
    }

    fun addItem(state: ItemView): HeadBottomNavigation {
        itemViews.add(state)
        return this
    }

    fun firstSelectedPosition(position: Int): HeadBottomNavigation {
        this.position = position
        return this
    }

    fun build() {
        val size = itemViews.size
        if (position !in 0 until size) throw IndexOutOfBoundsException("The setting position is greater than the length of the HeadBottomNavigation!!!")
        for (i in 0 until size) {
            menuItemImpl.add(MenuItemImpl().apply {
                if (position == i) checked = true
                itemView = itemViews[i]
                ripples = headBottomNavigationClickRipples
            })
            val navigationItemView = NavigationItemView(context).apply {
                layoutParams = LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f
                )

                initialize(menuItemImpl[i])
                setOnClickListener {
                    if (i != position) {
                        menuItemImpl[position].checked = false
                        (this@HeadBottomNavigation.getChildAt(position) as NavigationItemView).initialize(
                            menuItemImpl[position]
                        )
                        menuItemImpl[i].checked = true
                        (this@HeadBottomNavigation.getChildAt(i) as NavigationItemView).initialize(
                            menuItemImpl[i]
                        )
                    }
                    position = i
                    this@HeadBottomNavigation.onItemSelectedListener?.invoke(
                        this@HeadBottomNavigation.getChildAt(
                            position
                        ) as ViewGroup, position
                    )
                }
            }
            addView(navigationItemView)
        }
    }

    fun setOnItemSelectedListener(listener: (v: ViewGroup, position: Int) -> Unit): HeadBottomNavigation {
        this.onItemSelectedListener = listener
        return this
    }
}