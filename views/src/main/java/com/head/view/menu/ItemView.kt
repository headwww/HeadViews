package com.head.view.menu

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.head.view.drawable.BadgeDrawable

data class ItemView(
    @DrawableRes var checkedIcon: Int = 0,
    @DrawableRes var unCheckedIcon: Int = 0,
    var checkedLabel: CharSequence = "",
    var unCheckedLabel: CharSequence = checkedLabel,
    @ColorInt var checkedLabelColor: Int = Color.WHITE,
    @ColorInt var unCheckedLabelColor: Int = Color.WHITE
)
//fun builderItemView(builder: ItemView.() -> Unit) = ItemView().apply(builder)
