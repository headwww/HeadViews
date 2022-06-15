package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.head.view.utils.TemplateDrawable

/**
 *
 * 类名称：HeadTitleBar.kt <br/>
 * 类描述：自定义标题栏 <br/>
 * 适配各种机型刘海屏，水滴屏，全面屏，折叠屏
 * 支持左、中、右常规标题栏设置
 * 支持自定义视图
 * 支持沉浸式标题栏
 * 支持自定义标题栏颜色、渐变色
 * 支持控制状态栏的状态
 * 支持带分割线的标题栏
 * 支持带搜索框标题栏
 * 支持全局配置标题栏的样式
 * 支持折叠效果CoordinatorLayout
 *
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/14 10:47 <br/>
 * @version
 */
class HeadTitleBar : FrameLayout {

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

    private var headTitleBarGradientFrom: Int = Color.WHITE
    private var headTitleBarGradientTo: Int = Color.WHITE
    private var headTitleBarSupportGradient: Boolean = false
    private var headTitleBarBackgroundColor: Int = Color.WHITE


    private var customView: View? = null

    private var headTitleBarCustomViewRes: Int = 0

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadTitleBar)
        headTitleBarCustomViewRes =
            typedArray.getResourceId(R.styleable.HeadTitleBar_headTitleBarCustomView, 0)

        headTitleBarBackgroundColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarBackgroundColor,
            Color.WHITE
        )
        headTitleBarSupportGradient = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleBarSupportGradient,
            false
        )
        headTitleBarGradientFrom = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarGradientFrom,
            Color.WHITE
        )
        headTitleBarGradientTo = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarGradientTo,
            Color.WHITE
        )

        if (headTitleBarCustomViewRes != 0) {
            customView =
                LayoutInflater.from(context).inflate(headTitleBarCustomViewRes, null, false)
            removeAllViews()
            addView(customView, 0)
        }

        background = getDrawable()
    }

    private fun getDrawable(): TemplateDrawable = TemplateDrawable(
        context,
        headTitleBarSupportGradient,
        headTitleBarGradientFrom,
        headTitleBarGradientTo,
        headTitleBarBackgroundColor,
    ).apply {
        this@HeadTitleBar.invalidate()
    }

    fun getCustomView(): View? {
        return customView
    }

    fun addCustomView(headTitleBarCustomViewRes: Int, onBind: (view: View?) -> Unit) {
        this.headTitleBarCustomViewRes = headTitleBarCustomViewRes
        customView = LayoutInflater.from(context).inflate(headTitleBarCustomViewRes, null, false)
        removeAllViews()
        addView(customView)
        onBind(customView)
    }

    fun onBindViewClick(onBind: (view: View?) -> Unit) {
        if (customView != null)
            onBind(customView)
    }


}