package com.head.view.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.annotation.RestrictTo
import com.airbnb.lottie.LottieAnimationView
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class CheckableLottieAnimationView : LottieAnimationView, Checkable {
    private var checked = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setChecked(checked: Boolean) {
        try {
            if (this.checked !== checked) {
                this.checked = checked
                if (isAnimating) {
                    cancelAnimation()
                }
                if (checked) {
                    if (speed < 0.0f) {
                        reverseAnimationSpeed()
                    }
                    playAnimation()
                } else {
                    if (speed > 0.0f) {
                        reverseAnimationSpeed()
                    }
                    playAnimation()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun isChecked(): Boolean {
        return this.checked;
    }

    override fun toggle() {
        isChecked = !this.checked;
    }
}