package com.head.view.drawable

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

/**
 *
 * 类名称：TemplateDrawable.kt <br/>
 * 类描述：生成Drawable <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/7 13:53 <br/>
 * @version
 */
class TemplateDrawable(
    var supportGradient: Boolean = false,
    var gradientFrom: Int = Color.TRANSPARENT,
    var gradientTo: Int = Color.TRANSPARENT,
    var backgroundColor: Int = Color.TRANSPARENT,
    var radianLeftTop: Float = 0F,
    var radianLeftBottom: Float = 0F,
    var radianRightTop: Float = 0F,
    var radianRightBottom: Float = 0F,
    var radians: Float = 0F,
    var strokeWidth: Float = 0F,
    var strokeColor: Int = Color.TRANSPARENT,
    var strokeDashWidth: Float = 0F,
    var strokeDashGap: Float = 0F
) : GradientDrawable(), TemplateImpl {

    override fun setSupportGradient(supportGradient: Boolean): TemplateImpl {
        this.supportGradient = supportGradient
        if (this.supportGradient) {
            orientation = Orientation.LEFT_RIGHT
            colors = intArrayOf(gradientFrom, gradientTo)
        } else {
            setColor(backgroundColor)
        }
        return this
    }

    override fun setGradientFrom(gradientFrom: Int): TemplateImpl {
        this.gradientFrom = gradientFrom
        setSupportGradient(this.supportGradient)
        return this
    }

    override fun setGradientTo(gradientTo: Int): TemplateImpl {
        this.gradientTo = gradientTo
        setSupportGradient(this.supportGradient)
        return this
    }

    override fun setBackgroundColor(backgroundColor: Int): TemplateImpl {
        this.backgroundColor = backgroundColor
        setSupportGradient(this.supportGradient)
        return this
    }


    override fun setRadianLeftTop(radianLeftTop: Float): TemplateImpl {
        this.radianLeftTop = radianLeftTop
        aroundRadians()
        return this
    }

    override fun setRadianLeftBottom(radianLeftBottom: Float): TemplateImpl {
        this.radianLeftBottom = radianLeftBottom
        aroundRadians()
        return this

    }

    override fun setRadianRightTop(radianRightTop: Float): TemplateImpl {
        this.radianRightTop = radianRightTop
        aroundRadians()
        return this

    }

    override fun setRadianRightBottom(radianRightBottom: Float): TemplateImpl {
        this.radianRightBottom = radianRightBottom
        aroundRadians()
        return this
    }

    override fun setRadians(radians: Float): TemplateImpl {
        this.radians = radians
        if (this.radians != 0F) cornerRadius = this.radians
        return this
    }

    private fun aroundRadians() {
        cornerRadii = floatArrayOf(
            radianLeftTop,
            radianLeftTop,
            radianRightTop,
            radianRightTop,
            radianRightBottom,
            radianRightBottom,
            radianLeftBottom,
            radianLeftBottom
        )

    }

    override fun setStrokeWidth(strokeWidth: Float): TemplateImpl {
        this.strokeWidth = strokeWidth
        stroke()
        return this

    }

    override fun setStrokeColor(strokeColor: Int): TemplateImpl {
        this.strokeColor = strokeColor
        stroke()
        return this

    }

    override fun setStrokeDashWidth(strokeDashWidth: Float): TemplateImpl {
        this.strokeDashWidth = strokeDashWidth
        stroke()
        return this
    }

    override fun setStrokeDashGap(strokeDashGap: Float): TemplateImpl {
        this.strokeDashGap = strokeDashGap
        stroke()
        return this
    }

    private fun stroke() {
        if (strokeColor != -1)
            setStroke(
                this.strokeWidth.toInt(),
                this.strokeColor.toInt(),
                this.strokeDashWidth,
                this.strokeDashGap
            )

    }
}

fun builderTemplateDrawable(builder: TemplateDrawable.() -> TemplateDrawable) = TemplateDrawable().apply {
    builder(this)
    shape = GradientDrawable.RECTANGLE
    this.setSupportGradient(supportGradient)
        .setGradientFrom(gradientFrom)
        .setGradientTo(gradientTo)
        .setBackgroundColor(backgroundColor)
        .setRadianLeftTop(radianLeftTop)
        .setRadianLeftBottom(radianLeftBottom)
        .setRadians(radians)
        .setStrokeWidth(strokeWidth)
        .setStrokeColor(strokeColor)
        .setStrokeDashWidth(strokeDashWidth)
        .setStrokeDashGap(strokeDashGap)
}

fun modifyTemplateDrawable(
    drawable: TemplateDrawable,
    modify: TemplateDrawable.() -> TemplateDrawable
) = drawable.apply {
    modify(drawable)
}