package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.head.view.utils.TemplateDrawable


/**
 *
 * 类名称：HeadButton.kt <br/>
 * 类描述：自定义的Button如下功能
 *·设置按钮的默认状态，按下状态，禁用状态
 *·设置按钮的弧度，阴影，边框
 * <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/6 16:30 <br/>
 * @version
 */

class HeadButton : AppCompatButton {

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

    private var headNormalBackgroundColor: Int = Color.TRANSPARENT
    private var headNormalSupportGradient: Boolean = false
    private var headNormalGradientFrom: Int = Color.TRANSPARENT
    private var headNormalGradientTo: Int = Color.TRANSPARENT
    private var headNormalRadians: Int = 0
    private var headNormalRadianLeftTop: Int = 0
    private var headNormalRadianRightTop: Int = 0
    private var headNormalRadianLeftBottom: Int = 0
    private var headNormalRadianRightBottom: Int = 0
    private var headNormalStrokeWidth: Int = 0
    private var headNormalStrokeColor: Int = -1
    private var headNormalStrokeDashWidth: Float = 0F
    private var headNormalStrokeDashGap: Float = 0F

    private var headPressedBackgroundColor: Int = -1
    private var headPressedSupportGradient: Boolean = false
    private var headPressedGradientFrom: Int = headNormalGradientFrom
    private var headPressedGradientTo: Int = headNormalGradientTo
    private var headPressedRadians: Int = headNormalRadians
    private var headPressedRadianLeftTop: Int = headNormalRadianLeftTop
    private var headPressedRadianRightTop: Int = headNormalRadianRightTop
    private var headPressedRadianLeftBottom: Int = headNormalRadianLeftBottom
    private var headPressedRadianRightBottom: Int = headNormalRadianRightBottom
    private var headPressedStrokeWidth: Int = headNormalStrokeWidth
    private var headPressedStrokeColor: Int = headNormalStrokeColor
    private var headPressedStrokeDashWidth: Float = headNormalStrokeDashWidth
    private var headPressedStrokeDashGap: Float = headNormalStrokeDashGap

    private var headEnabledBackgroundColor: Int = -1
    private var headEnabledSupportGradient: Boolean = false
    private var headEnabledGradientFrom: Int = headNormalGradientFrom
    private var headEnabledGradientTo: Int = headNormalGradientTo
    private var headEnabledRadians: Int = headNormalRadians
    private var headEnabledRadianLeftTop: Int = headNormalRadianLeftTop
    private var headEnabledRadianRightTop: Int = headNormalRadianRightTop
    private var headEnabledRadianLeftBottom: Int = headNormalRadianLeftBottom
    private var headEnabledRadianRightBottom: Int = headNormalRadianRightBottom
    private var headEnabledStrokeWidth: Int = headNormalStrokeWidth
    private var headEnabledStrokeColor: Int = headNormalStrokeColor
    private var headEnabledStrokeDashWidth: Float = headNormalStrokeDashWidth
    private var headEnabledStrokeDashGap: Float = headNormalStrokeDashGap
    private var headButtonShape: Int = 0

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadButton)
        headNormalBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headNormalBackgroundColor,
            Color.TRANSPARENT
        )
        headNormalSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headNormalSupportGradient,
            false
        )
        headNormalGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headNormalGradientFrom,
            Color.TRANSPARENT
        )
        headNormalGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headNormalGradientTo,
            Color.TRANSPARENT
        )
        headNormalRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalRadians,
            0
        )
        headNormalRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalRadianLeftTop,
            0
        )
        headNormalRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalRadianRightTop,
            0
        )
        headNormalRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalRadianLeftBottom,
            0
        )
        headNormalRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalRadianRightBottom,
            0
        )
        headNormalStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headNormalStrokeColor,
            -1
        )
        headNormalStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headNormalStrokeWidth,
            0
        )
        headNormalStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headNormalStrokeDashWidth,
            0F
        )
        headNormalStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headNormalStrokeDashGap,
            0F
        )

        /////

        headPressedBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headPressedBackgroundColor,
            -1
        )
        headPressedSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headPressedSupportGradient,
            false
        )
        headPressedGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headPressedGradientFrom,
            Color.TRANSPARENT
        )
        headPressedGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headPressedGradientTo,
            Color.TRANSPARENT
        )
        headPressedRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedRadians,
            0
        )
        headPressedRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedRadianLeftTop,
            0
        )
        headPressedRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedRadianRightTop,
            0
        )
        headPressedRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedRadianLeftBottom,
            0
        )
        headPressedRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedRadianRightBottom,
            0
        )
        headPressedStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headPressedStrokeColor,
            -1
        )
        headPressedStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headPressedStrokeWidth,
            0
        )
        headPressedStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headPressedStrokeDashWidth,
            0F
        )
        headPressedStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headPressedStrokeDashGap,
            0F
        )


        /////

        headEnabledBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headEnabledBackgroundColor,
            -1
        )
        headEnabledSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headEnabledSupportGradient,
            false
        )
        headEnabledGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headEnabledGradientFrom,
            Color.TRANSPARENT
        )
        headEnabledGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headEnabledGradientTo,
            Color.TRANSPARENT
        )
        headEnabledRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledRadians,
            0
        )
        headEnabledRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledRadianLeftTop,
            0
        )
        headEnabledRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledRadianRightTop,
            0
        )
        headEnabledRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledRadianLeftBottom,
            0
        )
        headEnabledRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledRadianRightBottom,
            0
        )
        headEnabledStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headEnabledStrokeColor,
            -1
        )
        headEnabledStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headEnabledStrokeWidth,
            0
        )
        headEnabledStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headEnabledStrokeDashWidth,
            0F
        )
        headEnabledStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headEnabledStrokeDashGap,
            0F
        )

        headButtonShape = typedArray.getInt(
            R.styleable.HeadButton_headButtonShape,
            0
        )


        background = createDrawable()
    }


    private fun createDrawable(): StateListDrawable? = StateListDrawable().apply {
        //默认状态
        addState(
            intArrayOf(
                -android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), TemplateDrawable(
                context,
                headNormalSupportGradient,
                headNormalGradientFrom,
                headNormalGradientTo,
                headNormalBackgroundColor,
                headNormalRadianLeftTop,
                headNormalRadianLeftBottom,
                headNormalRadianRightTop,
                headNormalRadianRightBottom,
                headNormalRadians,
                headNormalStrokeWidth,
                headNormalStrokeColor,
                headNormalStrokeDashWidth,
                headNormalStrokeDashGap
            ).apply { shape = headButtonShape }

        )
        //按下状态
        headPressedBackgroundColor =
            if (headPressedBackgroundColor == 0 || headPressedBackgroundColor == -1) headNormalBackgroundColor else headPressedBackgroundColor
        Log.e("====", "${headPressedBackgroundColor}")
        addState(
            intArrayOf(
                android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), TemplateDrawable(
                context,
                headPressedSupportGradient,
                headPressedGradientFrom,
                headPressedGradientTo,
                headPressedBackgroundColor,
                headPressedRadianLeftTop,
                headPressedRadianLeftBottom,
                headPressedRadianRightTop,
                headPressedRadianRightBottom,
                headPressedRadians,
                headPressedStrokeWidth,
                headPressedStrokeColor,
                headPressedStrokeDashWidth,
                headPressedStrokeDashGap
            ).apply { shape = headButtonShape }

        )
        //禁止状态
        addState(
            intArrayOf(-android.R.attr.state_enabled),
            TemplateDrawable(
                context,
                headEnabledSupportGradient,
                headEnabledGradientFrom,
                headEnabledGradientTo,
                headEnabledBackgroundColor,
                headEnabledRadianLeftTop,
                headEnabledRadianLeftBottom,
                headEnabledRadianRightTop,
                headEnabledRadianRightBottom,
                headEnabledRadians,
                headEnabledStrokeWidth,
                headEnabledStrokeColor,
                headEnabledStrokeDashWidth,
                headEnabledStrokeDashGap
            ).apply { shape = headButtonShape }
        )
        invalidate()
    }

    fun setHeadNormalBackgroundColor(headNormalBackgroundColor: Int) {
        this.headNormalBackgroundColor = headNormalBackgroundColor
        Log.e("====", headNormalBackgroundColor.toString());
        background = createDrawable()
    }

    fun setHeadNormalSupportGradient(headNormalSupportGradient: Boolean) {
        this.headNormalSupportGradient = headNormalSupportGradient

        background = createDrawable()
    }

    fun setHeadNormalGradientFrom(headNormalGradientFrom: Int) {
        this.headNormalGradientFrom = headNormalGradientFrom
        background = createDrawable()
    }

    fun setHeadNormalGradientTo(headNormalGradientTo: Int) {
        this.headNormalGradientTo = headNormalGradientTo
        background = createDrawable()
    }

    fun setHeadNormalRadians(headNormalRadians: Int) {
        this.headNormalRadians = headNormalRadians
        background = createDrawable()
    }

    fun setHeadNormalRadianLeftTop(headNormalRadianLeftTop: Int) {
        this.headNormalRadianLeftTop = headNormalRadianLeftTop
        background = createDrawable()
    }

    fun setHeadNormalRadianRightTop(headNormalRadianRightTop: Int) {
        this.headNormalRadianRightTop = headNormalRadianRightTop
        background = createDrawable()
    }

    fun setHeadNormalRadianLeftBottom(headNormalRadianLeftBottom: Int) {
        this.headNormalRadianLeftBottom = headNormalRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadNormalRadianRightBottom(headNormalRadianRightBottom: Int) {
        this.headNormalRadianRightBottom = headNormalRadianRightBottom
        background = createDrawable()
    }

    fun setHeadNormalStrokeWidth(headNormalStrokeWidth: Int) {
        this.headNormalStrokeWidth = headNormalStrokeWidth
        background = createDrawable()
    }

    fun setHeadNormalStrokeColor(headNormalStrokeColor: Int) {
        this.headNormalStrokeColor = headNormalStrokeColor
        background = createDrawable()
    }

    fun setHeadNormalStrokeDashWidth(headNormalStrokeDashWidth: Float) {
        this.headNormalStrokeDashWidth = headNormalStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadNormalStrokeDashGap(headNormalStrokeDashGap: Float) {
        this.headNormalStrokeDashGap = headNormalStrokeDashGap
        background = createDrawable()
    }

    fun setHeadPressedBackgroundColor(headPressedBackgroundColor: Int) {
        this.headPressedBackgroundColor = headPressedBackgroundColor
        background = createDrawable()
    }

    fun setHeadPressedSupportGradient(headPressedSupportGradient: Boolean) {
        this.headPressedSupportGradient = headPressedSupportGradient
        background = createDrawable()
    }

    fun setHeadPressedGradientFrom(headPressedGradientFrom: Int) {
        this.headPressedGradientFrom = headPressedGradientFrom
        background = createDrawable()
    }

    fun setHeadPressedGradientTo(headPressedGradientTo: Int) {
        this.headPressedGradientTo = headPressedGradientTo
        background = createDrawable()
    }

    fun setHeadPressedRadians(headPressedRadians: Int) {
        this.headPressedRadians = headPressedRadians
        background = createDrawable()
    }

    fun setHeadPressedRadianLeftTop(headPressedRadianLeftTop: Int) {
        this.headPressedRadianLeftTop = headPressedRadianLeftTop
        background = createDrawable()
    }

    fun setHeadPressedRadianRightTop(headPressedRadianRightTop: Int) {
        this.headPressedRadianRightTop = headPressedRadianRightTop
        background = createDrawable()
    }

    fun setHeadPressedRadianLeftBottom(headPressedRadianLeftBottom: Int) {
        this.headPressedRadianLeftBottom = headPressedRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadPressedRadianRightBottom(headPressedRadianRightBottom: Int) {
        this.headPressedRadianRightBottom = headPressedRadianRightBottom
        background = createDrawable()
    }

    fun setHeadPressedStrokeWidth(headPressedStrokeWidth: Int) {
        this.headPressedStrokeWidth = headPressedStrokeWidth
        background = createDrawable()
    }

    fun setHeadPressedStrokeColor(headPressedStrokeColor: Int) {
        this.headPressedStrokeColor = headPressedStrokeColor
        background = createDrawable()
    }

    fun setHeadPressedStrokeDashWidth(headPressedStrokeDashWidth: Float) {
        this.headPressedStrokeDashWidth = headPressedStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadPressedStrokeDashGap(headPressedStrokeDashGap: Float) {
        this.headPressedStrokeDashGap = headPressedStrokeDashGap
        background = createDrawable()
    }

    fun setHeadEnabledBackgroundColor(headEnabledBackgroundColor: Int) {
        this.headEnabledBackgroundColor = headEnabledBackgroundColor
        background = createDrawable()
    }

    fun setHeadEnabledSupportGradient(headEnabledSupportGradient: Boolean) {
        this.headEnabledSupportGradient = headEnabledSupportGradient
        background = createDrawable()
    }

    fun setHeadEnabledGradientFrom(headEnabledGradientFrom: Int) {
        this.headEnabledGradientFrom = headEnabledGradientFrom
        background = createDrawable()
    }

    fun setHeadEnabledGradientTo(headEnabledGradientTo: Int) {
        this.headEnabledGradientTo = headEnabledGradientTo
        background = createDrawable()
    }

    fun setHeadEnabledRadians(headEnabledRadians: Int) {
        this.headEnabledRadians = headEnabledRadians
        background = createDrawable()
    }

    fun setHeadEnabledRadianLeftTop(headEnabledRadianLeftTop: Int) {
        this.headEnabledRadianLeftTop = headEnabledRadianLeftTop
        background = createDrawable()
    }

    fun setHeadEnabledRadianRightTop(headEnabledRadianRightTop: Int) {
        this.headEnabledRadianRightTop = headEnabledRadianRightTop
        background = createDrawable()
    }

    fun setHeadEnabledRadianLeftBottom(headEnabledRadianLeftBottom: Int) {
        this.headEnabledRadianLeftBottom = headEnabledRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadEnabledRadianRightBottom(headEnabledRadianRightBottom: Int) {
        this.headEnabledRadianRightBottom = headEnabledRadianRightBottom
        background = createDrawable()
    }

    fun setHeadEnabledStrokeWidth(headEnabledStrokeWidth: Int) {
        this.headEnabledStrokeWidth = headEnabledStrokeWidth
        background = createDrawable()
    }

    fun setHeadEnabledStrokeColor(headEnabledStrokeColor: Int) {
        this.headEnabledStrokeColor = headEnabledStrokeColor
        background = createDrawable()
    }

    fun setHeadEnabledStrokeDashWidth(headEnabledStrokeDashWidth: Float) {
        this.headEnabledStrokeDashWidth = headEnabledStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadEnabledStrokeDashGap(headEnabledStrokeDashGap: Float) {
        this.headEnabledStrokeDashGap = headEnabledStrokeDashGap
        background = createDrawable()
    }

    fun setHeadButtonShape(headButtonShape: Int) {
        this.headButtonShape = headButtonShape
        background = createDrawable()
    }


}