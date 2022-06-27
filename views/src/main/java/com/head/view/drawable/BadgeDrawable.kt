package com.head.view.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.util.Log
import androidx.annotation.ColorInt


class BadgeDrawable(@ColorInt val paintColor: Int = Color.RED) : Drawable() {
    private val paint = Paint().apply {
        textSize = 130F
        color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        alpha = 255
        typeface = Typeface.DEFAULT
        textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        Log.e("TAG", "draw:${bounds.right} ")
        canvas.drawCircle(150F, 150F, 70F, paint);//绘制圆角矩形区域
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    fun toSpannable(): SpannableString? {
        val spanStr = SpannableString("")
        spanStr.setSpan(
            ImageSpan(this, ImageSpan.ALIGN_BOTTOM),
            0,
            0,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        return spanStr
    }

}


fun builderBadgeDrawable(builder: BadgeDrawable.() -> Unit) = BadgeDrawable().apply(builder)
