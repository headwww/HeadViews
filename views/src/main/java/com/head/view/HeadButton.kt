package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.head.view.drawable.builderDrawable


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

    enum class ButtonShape {
        RECTANGLE,
        OVAL
    }

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

    private var headButtonNormalBackgroundColor: Int = Color.TRANSPARENT
    private var headButtonNormalSupportGradient: Boolean = false
    private var headButtonNormalGradientFrom: Int = Color.TRANSPARENT
    private var headButtonNormalGradientTo: Int = Color.TRANSPARENT
    private var headButtonNormalRadians: Float = 0F
    private var headButtonNormalRadianLeftTop: Float = 0F
    private var headButtonNormalRadianRightTop: Float = 0F
    private var headButtonNormalRadianLeftBottom: Float = 0F
    private var headButtonNormalRadianRightBottom: Float = 0F
    private var headButtonNormalStrokeWidth: Float = 0F
    private var headButtonNormalStrokeColor: Int = -1
    private var headButtonNormalStrokeDashWidth: Float = 0F
    private var headButtonNormalStrokeDashGap: Float = 0F

    private var headButtonPressedBackgroundColor: Int = -1
    private var headButtonPressedSupportGradient: Boolean = false
    private var headButtonPressedGradientFrom: Int = headButtonNormalGradientFrom
    private var headButtonPressedGradientTo: Int = headButtonNormalGradientTo
    private var headButtonPressedRadians: Float = headButtonNormalRadians
    private var headButtonPressedRadianLeftTop: Float = headButtonNormalRadianLeftTop
    private var headButtonPressedRadianRightTop: Float = headButtonNormalRadianRightTop
    private var headButtonPressedRadianLeftBottom: Float = headButtonNormalRadianLeftBottom
    private var headButtonPressedRadianRightBottom: Float = headButtonNormalRadianRightBottom
    private var headButtonPressedStrokeWidth: Float = headButtonNormalStrokeWidth
    private var headButtonPressedStrokeColor: Int = headButtonNormalStrokeColor
    private var headButtonPressedStrokeDashWidth: Float = headButtonNormalStrokeDashWidth
    private var headButtonPressedStrokeDashGap: Float = headButtonNormalStrokeDashGap

    private var headButtonEnabledBackgroundColor: Int = -1
    private var headButtonEnabledSupportGradient: Boolean = false
    private var headButtonEnabledGradientFrom: Int = headButtonNormalGradientFrom
    private var headButtonEnabledGradientTo: Int = headButtonNormalGradientTo
    private var headButtonEnabledRadians: Float = headButtonNormalRadians
    private var headButtonEnabledRadianLeftTop: Float = headButtonNormalRadianLeftTop
    private var headButtonEnabledRadianRightTop: Float = headButtonNormalRadianRightTop
    private var headButtonEnabledRadianLeftBottom: Float = headButtonNormalRadianLeftBottom
    private var headButtonEnabledRadianRightBottom: Float = headButtonNormalRadianRightBottom
    private var headButtonEnabledStrokeWidth: Float = headButtonNormalStrokeWidth
    private var headButtonEnabledStrokeColor: Int = headButtonNormalStrokeColor
    private var headButtonEnabledStrokeDashWidth: Float = headButtonNormalStrokeDashWidth
    private var headButtonEnabledStrokeDashGap: Float = headButtonNormalStrokeDashGap
    private var headButtonShape: Int = ButtonShape.RECTANGLE.ordinal

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadButton)
        headButtonNormalBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonNormalBackgroundColor,
            Color.TRANSPARENT
        )
        headButtonNormalSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headButtonNormalSupportGradient,
            false
        )
        headButtonNormalGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headButtonNormalGradientFrom,
            Color.TRANSPARENT
        )
        headButtonNormalGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headButtonNormalGradientTo,
            Color.TRANSPARENT
        )
        headButtonNormalRadians = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalRadians,
            0F
        )
        headButtonNormalRadianLeftTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalRadianLeftTop,
            0F
        )
        headButtonNormalRadianRightTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalRadianRightTop,
            0F
        )
        headButtonNormalRadianLeftBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalRadianLeftBottom,
            0F
        )
        headButtonNormalRadianRightBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalRadianRightBottom,
            0F
        )
        headButtonNormalStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonNormalStrokeColor,
            -1
        )
        headButtonNormalStrokeWidth = typedArray.getDimension(
            R.styleable.HeadButton_headButtonNormalStrokeWidth,
            0F
        )
        headButtonNormalStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headButtonNormalStrokeDashWidth,
            0F
        )
        headButtonNormalStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headButtonNormalStrokeDashGap,
            0F
        )

        /////

        headButtonPressedBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonPressedBackgroundColor,
            -1
        )
        headButtonPressedSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headButtonPressedSupportGradient,
            false
        )
        headButtonPressedGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headButtonPressedGradientFrom,
            Color.TRANSPARENT
        )
        headButtonPressedGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headButtonPressedGradientTo,
            Color.TRANSPARENT
        )
        headButtonPressedRadians = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedRadians,
            0F
        )
        headButtonPressedRadianLeftTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedRadianLeftTop,
            0F
        )
        headButtonPressedRadianRightTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedRadianRightTop,
            0F
        )
        headButtonPressedRadianLeftBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedRadianLeftBottom,
            0F
        )
        headButtonPressedRadianRightBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedRadianRightBottom,
            0F
        )
        headButtonPressedStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonPressedStrokeColor,
            -1
        )
        headButtonPressedStrokeWidth = typedArray.getDimension(
            R.styleable.HeadButton_headButtonPressedStrokeWidth,
            0F
        )
        headButtonPressedStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headButtonPressedStrokeDashWidth,
            0F
        )
        headButtonPressedStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headButtonPressedStrokeDashGap,
            0F
        )


        /////

        headButtonEnabledBackgroundColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonEnabledBackgroundColor,
            -1
        )
        headButtonEnabledSupportGradient = typedArray.getBoolean(
            R.styleable.HeadButton_headButtonEnabledSupportGradient,
            false
        )
        headButtonEnabledGradientFrom = typedArray.getColor(
            R.styleable.HeadButton_headButtonEnabledGradientFrom,
            Color.TRANSPARENT
        )
        headButtonEnabledGradientTo = typedArray.getColor(
            R.styleable.HeadButton_headButtonEnabledGradientTo,
            Color.TRANSPARENT
        )
        headButtonEnabledRadians = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledRadians,
            0F
        )
        headButtonEnabledRadianLeftTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledRadianLeftTop,
            0F
        )
        headButtonEnabledRadianRightTop = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledRadianRightTop,
            0F
        )
        headButtonEnabledRadianLeftBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledRadianLeftBottom,
            0F
        )
        headButtonEnabledRadianRightBottom = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledRadianRightBottom,
            0F
        )
        headButtonEnabledStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonEnabledStrokeColor,
            -1
        )
        headButtonEnabledStrokeWidth = typedArray.getDimension(
            R.styleable.HeadButton_headButtonEnabledStrokeWidth,
            0F
        )
        headButtonEnabledStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadButton_headButtonEnabledStrokeDashWidth,
            0F
        )
        headButtonEnabledStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadButton_headButtonEnabledStrokeDashGap,
            0F
        )

        headButtonShape = typedArray.getInt(
            R.styleable.HeadButton_headButtonShape,
            ButtonShape.RECTANGLE.ordinal
        )


        background = createDrawable()
        typedArray.recycle()

    }


    private fun createDrawable(): StateListDrawable? = StateListDrawable().apply {
        //默认状态
        addState(
            intArrayOf(
                -android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), builderDrawable {
                supportGradient = headButtonNormalSupportGradient
                gradientFrom = headButtonNormalGradientFrom
                gradientTo = headButtonNormalGradientTo
                backgroundColor = headButtonNormalBackgroundColor
                radianLeftTop = headButtonNormalRadianLeftTop.toFloat()
                radianLeftBottom = headButtonNormalRadianLeftBottom.toFloat()
                radianRightTop = headButtonNormalRadianRightTop.toFloat()
                radianRightBottom =
                    headButtonNormalRadianRightBottom.toFloat()
                radians = headButtonNormalRadians.toFloat()
                strokeWidth = headButtonNormalStrokeWidth
                strokeColor = headButtonNormalStrokeColor
                strokeDashWidth = headButtonNormalStrokeDashWidth
                strokeDashGap = headButtonNormalStrokeDashGap
                this
            }.apply { shape = headButtonShape }
        )
        //按下状态
        headButtonPressedBackgroundColor =
            if (headButtonPressedBackgroundColor == 0 || headButtonPressedBackgroundColor == -1) headButtonNormalBackgroundColor else headButtonPressedBackgroundColor
        addState(
            intArrayOf(
                android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), builderDrawable {
                supportGradient = headButtonPressedSupportGradient
                gradientFrom = headButtonPressedGradientFrom
                gradientTo = headButtonPressedGradientTo
                backgroundColor = headButtonPressedBackgroundColor
                radianLeftTop = headButtonPressedRadianLeftTop.toFloat()
                radianLeftBottom = headButtonPressedRadianLeftBottom.toFloat()
                radianRightTop = headButtonPressedRadianRightTop.toFloat()
                radianRightBottom = headButtonPressedRadianRightBottom.toFloat()
                radians = headButtonPressedRadians.toFloat()
                strokeWidth = headButtonPressedStrokeWidth
                strokeColor = headButtonPressedStrokeColor
                strokeDashWidth = headButtonPressedStrokeDashWidth
                strokeDashGap = headButtonPressedStrokeDashGap
                this
            }.apply { shape = headButtonShape }
        )
        //禁止状态
        addState(
            intArrayOf(-android.R.attr.state_enabled),
            builderDrawable {
                supportGradient = headButtonEnabledSupportGradient
                gradientFrom = headButtonEnabledGradientFrom
                gradientTo = headButtonEnabledGradientTo
                backgroundColor = headButtonEnabledBackgroundColor
                radianLeftTop = headButtonEnabledRadianLeftTop.toFloat()
                radianLeftBottom = headButtonEnabledRadianLeftBottom.toFloat()
                radianRightTop = headButtonEnabledRadianRightTop.toFloat()
                radianRightBottom =
                    headButtonEnabledRadianRightBottom.toFloat()
                radians = headButtonEnabledRadians.toFloat()
                strokeWidth = headButtonEnabledStrokeWidth
                strokeColor = headButtonEnabledStrokeColor
                strokeDashWidth = headButtonEnabledStrokeDashWidth
                strokeDashGap = headButtonEnabledStrokeDashGap
                this
            }.apply { shape = headButtonShape }
        )
        invalidate()
    }

    fun setHeadButtonNormalBackgroundColor(headButtonNormalBackgroundColor: Int) {
        this.headButtonNormalBackgroundColor = headButtonNormalBackgroundColor
        background = createDrawable()
    }

    fun setHeadButtonNormalSupportGradient(headButtonNormalSupportGradient: Boolean) {
        this.headButtonNormalSupportGradient = headButtonNormalSupportGradient

        background = createDrawable()
    }

    fun setHeadButtonNormalGradientFrom(headButtonNormalGradientFrom: Int) {
        this.headButtonNormalGradientFrom = headButtonNormalGradientFrom
        background = createDrawable()
    }

    fun setHeadButtonNormalGradientTo(headButtonNormalGradientTo: Int) {
        this.headButtonNormalGradientTo = headButtonNormalGradientTo
        background = createDrawable()
    }

    fun setHeadButtonNormalRadians(headButtonNormalRadians: Float) {
        this.headButtonNormalRadians = headButtonNormalRadians
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianLeftTop(headButtonNormalRadianLeftTop: Float) {
        this.headButtonNormalRadianLeftTop = headButtonNormalRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianRightTop(headButtonNormalRadianRightTop: Float) {
        this.headButtonNormalRadianRightTop = headButtonNormalRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianLeftBottom(headButtonNormalRadianLeftBottom: Float) {
        this.headButtonNormalRadianLeftBottom = headButtonNormalRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianRightBottom(headButtonNormalRadianRightBottom: Float) {
        this.headButtonNormalRadianRightBottom = headButtonNormalRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonNormalStrokeWidth(headButtonNormalStrokeWidth: Float) {
        this.headButtonNormalStrokeWidth = headButtonNormalStrokeWidth
        background = createDrawable()
    }

    fun setHeadButtonNormalStrokeColor(headButtonNormalStrokeColor: Int) {
        this.headButtonNormalStrokeColor = headButtonNormalStrokeColor
        background = createDrawable()
    }

    fun setHeadButtonNormalStrokeDashWidth(headButtonNormalStrokeDashWidth: Float) {
        this.headButtonNormalStrokeDashWidth = headButtonNormalStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadButtonNormalStrokeDashGap(headButtonNormalStrokeDashGap: Float) {
        this.headButtonNormalStrokeDashGap = headButtonNormalStrokeDashGap
        background = createDrawable()
    }

    fun setHeadButtonPressedBackgroundColor(headButtonPressedBackgroundColor: Int) {
        this.headButtonPressedBackgroundColor = headButtonPressedBackgroundColor
        background = createDrawable()
    }

    fun setHeadButtonPressedSupportGradient(headButtonPressedSupportGradient: Boolean) {
        this.headButtonPressedSupportGradient = headButtonPressedSupportGradient
        background = createDrawable()
    }

    fun setHeadButtonPressedGradientFrom(headButtonPressedGradientFrom: Int) {
        this.headButtonPressedGradientFrom = headButtonPressedGradientFrom
        background = createDrawable()
    }

    fun setHeadButtonPressedGradientTo(headButtonPressedGradientTo: Int) {
        this.headButtonPressedGradientTo = headButtonPressedGradientTo
        background = createDrawable()
    }

    fun setHeadButtonPressedRadians(headButtonPressedRadians: Float) {
        this.headButtonPressedRadians = headButtonPressedRadians
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianLeftTop(headButtonPressedRadianLeftTop: Float) {
        this.headButtonPressedRadianLeftTop = headButtonPressedRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianRightTop(headButtonPressedRadianRightTop: Float) {
        this.headButtonPressedRadianRightTop = headButtonPressedRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianLeftBottom(headButtonPressedRadianLeftBottom: Float) {
        this.headButtonPressedRadianLeftBottom = headButtonPressedRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianRightBottom(headButtonPressedRadianRightBottom: Float) {
        this.headButtonPressedRadianRightBottom = headButtonPressedRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonPressedStrokeWidth(headButtonPressedStrokeWidth: Float) {
        this.headButtonPressedStrokeWidth = headButtonPressedStrokeWidth
        background = createDrawable()
    }

    fun setHeadButtonPressedStrokeColor(headButtonPressedStrokeColor: Int) {
        this.headButtonPressedStrokeColor = headButtonPressedStrokeColor
        background = createDrawable()
    }

    fun setHeadButtonPressedStrokeDashWidth(headButtonPressedStrokeDashWidth: Float) {
        this.headButtonPressedStrokeDashWidth = headButtonPressedStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadButtonPressedStrokeDashGap(headButtonPressedStrokeDashGap: Float) {
        this.headButtonPressedStrokeDashGap = headButtonPressedStrokeDashGap
        background = createDrawable()
    }

    fun setHeadButtonEnabledBackgroundColor(headButtonEnabledBackgroundColor: Int) {
        this.headButtonEnabledBackgroundColor = headButtonEnabledBackgroundColor
        background = createDrawable()
    }

    fun setHeadButtonEnabledSupportGradient(headButtonEnabledSupportGradient: Boolean) {
        this.headButtonEnabledSupportGradient = headButtonEnabledSupportGradient
        background = createDrawable()
    }

    fun setHeadButtonEnabledGradientFrom(headButtonEnabledGradientFrom: Int) {
        this.headButtonEnabledGradientFrom = headButtonEnabledGradientFrom
        background = createDrawable()
    }

    fun setHeadButtonEnabledGradientTo(headButtonEnabledGradientTo: Int) {
        this.headButtonEnabledGradientTo = headButtonEnabledGradientTo
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadians(headButtonEnabledRadians: Float) {
        this.headButtonEnabledRadians = headButtonEnabledRadians
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianLeftTop(headButtonEnabledRadianLeftTop: Float) {
        this.headButtonEnabledRadianLeftTop = headButtonEnabledRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianRightTop(headButtonEnabledRadianRightTop: Float) {
        this.headButtonEnabledRadianRightTop = headButtonEnabledRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianLeftBottom(headButtonEnabledRadianLeftBottom: Float) {
        this.headButtonEnabledRadianLeftBottom = headButtonEnabledRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianRightBottom(headButtonEnabledRadianRightBottom: Float) {
        this.headButtonEnabledRadianRightBottom = headButtonEnabledRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonEnabledStrokeWidth(headButtonEnabledStrokeWidth: Float) {
        this.headButtonEnabledStrokeWidth = headButtonEnabledStrokeWidth
        background = createDrawable()
    }

    fun setHeadButtonEnabledStrokeColor(headButtonEnabledStrokeColor: Int) {
        this.headButtonEnabledStrokeColor = headButtonEnabledStrokeColor
        background = createDrawable()
    }

    fun setHeadButtonEnabledStrokeDashWidth(headButtonEnabledStrokeDashWidth: Float) {
        this.headButtonEnabledStrokeDashWidth = headButtonEnabledStrokeDashWidth
        background = createDrawable()
    }

    fun setHeadButtonEnabledStrokeDashGap(headButtonEnabledStrokeDashGap: Float) {
        this.headButtonEnabledStrokeDashGap = headButtonEnabledStrokeDashGap
        background = createDrawable()
    }

    fun setHeadButtonShape(headButtonShape: ButtonShape) {
        this.headButtonShape = headButtonShape.ordinal
        background = createDrawable()
    }


}