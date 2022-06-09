package com.head.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.ColorInt

/**
 *
 * 类名称：TemplateDrawable.kt <br/>
 * 类描述：生成Drawable <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/7 13:53 <br/>
 * @version
 */
class TemplateDrawable(
    context: Context,
    supportGradient: Boolean = false,
    gradientFrom: Int = Color.TRANSPARENT,
    gradientTo: Int = Color.TRANSPARENT,
    backgroundColor: Int = Color.TRANSPARENT,
    radianLeftTop: Int = 0,
    radianLeftBottom: Int = 0,
    radianRightTop: Int = 0,
    radianRightBottom: Int = 0,
    radians: Int = 0,
    strokeWidth: Int = 0,
    strokeColor: Int = Color.TRANSPARENT,
    strokeDashWidth: Float = 0F,
    strokeDashGap: Float = 0F
) : GradientDrawable() {

    init {
        shape = RECTANGLE
        if (supportGradient) {
            orientation = Orientation.LEFT_RIGHT
            colors = intArrayOf(gradientFrom, gradientTo)
        } else {
            setColor(backgroundColor)
        }

        cornerRadii = floatArrayOf(
            Utils.dp2px(context, radianLeftTop.toFloat()),
            Utils.dp2px(context, radianLeftTop.toFloat()),
            Utils.dp2px(context, radianRightTop.toFloat()),
            Utils.dp2px(context, radianRightTop.toFloat()),
            Utils.dp2px(context, radianRightBottom.toFloat()),
            Utils.dp2px(context, radianRightBottom.toFloat()),
            Utils.dp2px(context, radianLeftBottom.toFloat()),
            Utils.dp2px(context, radianLeftBottom.toFloat())
        )
        if (radians != 0) cornerRadius =
            radians.toFloat()

        if (strokeColor != -1)
            setStroke(
                strokeWidth,
                strokeColor,
                strokeDashWidth,
                strokeDashGap
            )
    }


}