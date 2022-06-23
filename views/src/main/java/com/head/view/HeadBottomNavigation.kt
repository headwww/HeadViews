package com.head.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
/**
*
* 类名称：HeadBottomNavigation.kt <br/>
* 类描述：底部导航栏 <br/>
* 创建人：shuwen <br/>
* 创建时间：2022/6/23 13:55 <br/>
* @version
*/
class HeadBottomNavigation  : FrameLayout {

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

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
    }

}