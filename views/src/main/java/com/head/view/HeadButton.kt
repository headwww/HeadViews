package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.head.view.utils.TemplateDrawable
import com.head.view.utils.builderHeadDrawable


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
    private var headButtonNormalRadians: Int = 0
    private var headButtonNormalRadianLeftTop: Int = 0
    private var headButtonNormalRadianRightTop: Int = 0
    private var headButtonNormalRadianLeftBottom: Int = 0
    private var headButtonNormalRadianRightBottom: Int = 0
    private var headButtonNormalStrokeWidth: Int = 0
    private var headButtonNormalStrokeColor: Int = -1
    private var headButtonNormalStrokeDashWidth: Float = 0F
    private var headButtonNormalStrokeDashGap: Float = 0F

    private var headButtonPressedBackgroundColor: Int = -1
    private var headButtonPressedSupportGradient: Boolean = false
    private var headButtonPressedGradientFrom: Int = headButtonNormalGradientFrom
    private var headButtonPressedGradientTo: Int = headButtonNormalGradientTo
    private var headButtonPressedRadians: Int = headButtonNormalRadians
    private var headButtonPressedRadianLeftTop: Int = headButtonNormalRadianLeftTop
    private var headButtonPressedRadianRightTop: Int = headButtonNormalRadianRightTop
    private var headButtonPressedRadianLeftBottom: Int = headButtonNormalRadianLeftBottom
    private var headButtonPressedRadianRightBottom: Int = headButtonNormalRadianRightBottom
    private var headButtonPressedStrokeWidth: Int = headButtonNormalStrokeWidth
    private var headButtonPressedStrokeColor: Int = headButtonNormalStrokeColor
    private var headButtonPressedStrokeDashWidth: Float = headButtonNormalStrokeDashWidth
    private var headButtonPressedStrokeDashGap: Float = headButtonNormalStrokeDashGap

    private var headButtonEnabledBackgroundColor: Int = -1
    private var headButtonEnabledSupportGradient: Boolean = false
    private var headButtonEnabledGradientFrom: Int = headButtonNormalGradientFrom
    private var headButtonEnabledGradientTo: Int = headButtonNormalGradientTo
    private var headButtonEnabledRadians: Int = headButtonNormalRadians
    private var headButtonEnabledRadianLeftTop: Int = headButtonNormalRadianLeftTop
    private var headButtonEnabledRadianRightTop: Int = headButtonNormalRadianRightTop
    private var headButtonEnabledRadianLeftBottom: Int = headButtonNormalRadianLeftBottom
    private var headButtonEnabledRadianRightBottom: Int = headButtonNormalRadianRightBottom
    private var headButtonEnabledStrokeWidth: Int = headButtonNormalStrokeWidth
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
        headButtonNormalRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalRadians,
            0
        )
        headButtonNormalRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalRadianLeftTop,
            0
        )
        headButtonNormalRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalRadianRightTop,
            0
        )
        headButtonNormalRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalRadianLeftBottom,
            0
        )
        headButtonNormalRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalRadianRightBottom,
            0
        )
        headButtonNormalStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonNormalStrokeColor,
            -1
        )
        headButtonNormalStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonNormalStrokeWidth,
            0
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
        headButtonPressedRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedRadians,
            0
        )
        headButtonPressedRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedRadianLeftTop,
            0
        )
        headButtonPressedRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedRadianRightTop,
            0
        )
        headButtonPressedRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedRadianLeftBottom,
            0
        )
        headButtonPressedRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedRadianRightBottom,
            0
        )
        headButtonPressedStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonPressedStrokeColor,
            -1
        )
        headButtonPressedStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonPressedStrokeWidth,
            0
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
        headButtonEnabledRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledRadians,
            0
        )
        headButtonEnabledRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledRadianLeftTop,
            0
        )
        headButtonEnabledRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledRadianRightTop,
            0
        )
        headButtonEnabledRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledRadianLeftBottom,
            0
        )
        headButtonEnabledRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledRadianRightBottom,
            0
        )
        headButtonEnabledStrokeColor = typedArray.getColor(
            R.styleable.HeadButton_headButtonEnabledStrokeColor,
            -1
        )
        headButtonEnabledStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadButton_headButtonEnabledStrokeWidth,
            0
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
    }


    private fun createDrawable(): StateListDrawable? = StateListDrawable().apply {
        //默认状态
        addState(
            intArrayOf(
                -android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), TemplateDrawable(
                context,
                headButtonNormalSupportGradient,
                headButtonNormalGradientFrom,
                headButtonNormalGradientTo,
                headButtonNormalBackgroundColor,
                headButtonNormalRadianLeftTop,
                headButtonNormalRadianLeftBottom,
                headButtonNormalRadianRightTop,
                headButtonNormalRadianRightBottom,
                headButtonNormalRadians,
                headButtonNormalStrokeWidth,
                headButtonNormalStrokeColor,
                headButtonNormalStrokeDashWidth,
                headButtonNormalStrokeDashGap
            ).apply { shape = headButtonShape }

        )
        //按下状态
        headButtonPressedBackgroundColor =
            if (headButtonPressedBackgroundColor == 0 || headButtonPressedBackgroundColor == -1) headButtonNormalBackgroundColor else headButtonPressedBackgroundColor
        addState(
            intArrayOf(
                android.R.attr.state_pressed,
                android.R.attr.state_enabled
            ), TemplateDrawable(
                context,
                headButtonPressedSupportGradient,
                headButtonPressedGradientFrom,
                headButtonPressedGradientTo,
                headButtonPressedBackgroundColor,
                headButtonPressedRadianLeftTop,
                headButtonPressedRadianLeftBottom,
                headButtonPressedRadianRightTop,
                headButtonPressedRadianRightBottom,
                headButtonPressedRadians,
                headButtonPressedStrokeWidth,
                headButtonPressedStrokeColor,
                headButtonPressedStrokeDashWidth,
                headButtonPressedStrokeDashGap
            ).apply { shape = headButtonShape }

        )
        //禁止状态
        addState(
            intArrayOf(-android.R.attr.state_enabled),
            TemplateDrawable(
                context,
                headButtonEnabledSupportGradient,
                headButtonEnabledGradientFrom,
                headButtonEnabledGradientTo,
                headButtonEnabledBackgroundColor,
                headButtonEnabledRadianLeftTop,
                headButtonEnabledRadianLeftBottom,
                headButtonEnabledRadianRightTop,
                headButtonEnabledRadianRightBottom,
                headButtonEnabledRadians,
                headButtonEnabledStrokeWidth,
                headButtonEnabledStrokeColor,
                headButtonEnabledStrokeDashWidth,
                headButtonEnabledStrokeDashGap
            ).apply { shape = headButtonShape }
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

    fun setHeadButtonNormalRadians(headButtonNormalRadians: Int) {
        this.headButtonNormalRadians = headButtonNormalRadians
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianLeftTop(headButtonNormalRadianLeftTop: Int) {
        this.headButtonNormalRadianLeftTop = headButtonNormalRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianRightTop(headButtonNormalRadianRightTop: Int) {
        this.headButtonNormalRadianRightTop = headButtonNormalRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianLeftBottom(headButtonNormalRadianLeftBottom: Int) {
        this.headButtonNormalRadianLeftBottom = headButtonNormalRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonNormalRadianRightBottom(headButtonNormalRadianRightBottom: Int) {
        this.headButtonNormalRadianRightBottom = headButtonNormalRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonNormalStrokeWidth(headButtonNormalStrokeWidth: Int) {
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

    fun setHeadButtonPressedRadians(headButtonPressedRadians: Int) {
        this.headButtonPressedRadians = headButtonPressedRadians
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianLeftTop(headButtonPressedRadianLeftTop: Int) {
        this.headButtonPressedRadianLeftTop = headButtonPressedRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianRightTop(headButtonPressedRadianRightTop: Int) {
        this.headButtonPressedRadianRightTop = headButtonPressedRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianLeftBottom(headButtonPressedRadianLeftBottom: Int) {
        this.headButtonPressedRadianLeftBottom = headButtonPressedRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonPressedRadianRightBottom(headButtonPressedRadianRightBottom: Int) {
        this.headButtonPressedRadianRightBottom = headButtonPressedRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonPressedStrokeWidth(headButtonPressedStrokeWidth: Int) {
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

    fun setHeadButtonEnabledRadians(headButtonEnabledRadians: Int) {
        this.headButtonEnabledRadians = headButtonEnabledRadians
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianLeftTop(headButtonEnabledRadianLeftTop: Int) {
        this.headButtonEnabledRadianLeftTop = headButtonEnabledRadianLeftTop
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianRightTop(headButtonEnabledRadianRightTop: Int) {
        this.headButtonEnabledRadianRightTop = headButtonEnabledRadianRightTop
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianLeftBottom(headButtonEnabledRadianLeftBottom: Int) {
        this.headButtonEnabledRadianLeftBottom = headButtonEnabledRadianLeftBottom
        background = createDrawable()
    }

    fun setHeadButtonEnabledRadianRightBottom(headButtonEnabledRadianRightBottom: Int) {
        this.headButtonEnabledRadianRightBottom = headButtonEnabledRadianRightBottom
        background = createDrawable()
    }

    fun setHeadButtonEnabledStrokeWidth(headButtonEnabledStrokeWidth: Int) {
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