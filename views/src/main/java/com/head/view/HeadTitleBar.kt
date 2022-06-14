package com.head.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

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

    /**
     * 左侧视图布局
     */
    private lateinit var leftTextView: TextView

    /**
     * 右侧视图布局
     */
    private lateinit var rightTextView: TextView

    /**
     * 中间视图布局
     */
    private lateinit var centerLinearLayout: LinearLayout

    /**
     * 中间视图的主标题
     */
    private lateinit var centerMainTitleTextView: TextView

    /**
     * 中间视图的副标题
     */
    private lateinit var centerSubtitleTextView: TextView


    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {


        leftTextView = TextView(context)
        leftTextView.text = "左边"
        leftTextView.textSize = 30F
        leftTextView.setTextColor(Color.BLACK)
        leftTextView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            Gravity.START
        )
        leftTextView.gravity = Gravity.CENTER_VERTICAL
//        leftTextView.setPadding(50,0,50,0)


        rightTextView = TextView(context)
        rightTextView.text = "右边"
        rightTextView.textSize = 30F
//        rightTextView.setPadding(50,0,50,0)
        rightTextView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            Gravity.END
        )
        rightTextView.gravity = Gravity.CENTER_VERTICAL


        centerLinearLayout = LinearLayout(context)
        centerLinearLayout.background = ColorDrawable(Color.RED)
        centerLinearLayout.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        centerLinearLayout.orientation = LinearLayout.VERTICAL
        centerLinearLayout.gravity = Gravity.CENTER


        centerMainTitleTextView = TextView(context)
        centerMainTitleTextView.text = "center main-------------------------------------------"
        centerMainTitleTextView.maxLines = 1
        centerMainTitleTextView.maxEms = 10

        centerMainTitleTextView.textSize = 30F
        centerMainTitleTextView.setPadding(50,0,50,0)
        centerMainTitleTextView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        centerLinearLayout.addView(centerMainTitleTextView)


        centerSubtitleTextView = TextView(context)
        centerSubtitleTextView.text = "center sub"
        centerSubtitleTextView.textSize = 30F
//        rightTextView.setPadding(50,0,50,0)
        centerSubtitleTextView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        centerLinearLayout.addView(centerSubtitleTextView)

        addView(centerLinearLayout)
        addView(leftTextView)
        addView(rightTextView)


    }

}