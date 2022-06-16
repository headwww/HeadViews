package com.head.view.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.widget.TextView
import com.head.view.style.GeneralModeTitle

/**
 *
 * 类名称：TemplateDrawable.kt <br/>
 * 类描述：生成Drawable <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/7 13:53 <br/>
 * @version
 */
data class HeadDrawable(
    var context: Context? =null,
    var supportGradient: Boolean = false,
    var gradientFrom: Int = Color.TRANSPARENT,
    var gradientTo: Int = Color.TRANSPARENT,
    var backgroundColor: Int = Color.TRANSPARENT,
    var radianLeftTop: Int = 0,
    var radianLeftBottom: Int = 0,
    var radianRightTop: Int = 0,
    var radianRightBottom: Int = 0,
    var radians: Int = 0,
    var strokeWidth: Int = 0,
    var strokeColor: Int = Color.TRANSPARENT,
    var strokeDashWidth: Float = 0F,
    var strokeDashGap: Float = 0F
) : GradientDrawable()

fun builderHeadDrawable(builder: HeadDrawable.() -> Unit):HeadDrawable = HeadDrawable().apply(builder)
fun modifyHeadDrawable(
    generalModeTitle: HeadDrawable,
    modifyAction: HeadDrawable.() -> Unit
) = generalModeTitle.apply(modifyAction)