package com.head.view

import android.content.Context
import android.util.TypedValue

object Utils {
    /**
     * dp转px
     */
     fun dp2px(context :Context ,dpVal: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpVal,
        context.resources.displayMetrics
    )


}