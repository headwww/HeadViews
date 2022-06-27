package com.head.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.head.view.menu.ItemViewState

/**
 *
 * 类名称：HeadBottomNavigation.kt <br/>
 * 类描述：底部导航栏 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/23 13:55 <br/>
 * @version
 */
class HeadBottomNavigation : LinearLayout, View.OnLayoutChangeListener {

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
        addOnLayoutChangeListener(this)
    }


    private val navigationItemViews: MutableList<NavigationItemView> = ArrayList()
    private var itemViewStates: MutableList<ItemViewState> = ArrayList()
    private var position: Int = 0
    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        orientation = HORIZONTAL
        addOnLayoutChangeListener(this)

    }

    fun addItem(itemView: NavigationItemView): HeadBottomNavigation {
        navigationItemViews.add(itemView)
        return this
    }

    fun build() {
        itemViewStates.addAll(navigationItemViews)
        for (navigationItemView in navigationItemViews) {
            navigationItemView.setOnClickListener {
                for (itemViewState in itemViewStates) {
                    itemViewState.setChecked(false)
                }
                navigationItemView.setChecked(true)
            }
            addView(navigationItemView)
        }
    }

    override fun onLayoutChange(
        v: View?,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        oldLeft: Int,
        oldTop: Int,
        oldRight: Int,
        oldBottom: Int
    ) {
        // 先移除当前的监听，因为子View在setMaxWidth时候会重新触发监听，解决递归
        removeOnLayoutChangeListener(this)
        for (item in navigationItemViews) {
            item.layoutParams.width = (right - left) / navigationItemViews.size
        }
        post {
            //这里再次监听需要延迟，否则会导致递归的情况发生
            addOnLayoutChangeListener(this)
        }
    }
}