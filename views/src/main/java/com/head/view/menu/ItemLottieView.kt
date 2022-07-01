package com.head.view.menu

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.head.view.drawable.BadgeDrawable

data class ItemLottieView(
    @RawRes var rawRes: Int = 0,
    var checkedLabel: CharSequence = "",
    var unCheckedLabel: CharSequence = checkedLabel,
    @ColorInt var checkedLabelColor: Int = Color.WHITE,
    @ColorInt var unCheckedLabelColor: Int = Color.WHITE
)
//fun builderItemView(builder: ItemView.() -> Unit) = ItemView().apply(builder)
//
//setAnimation(@RawRes final int rawRes)
//setAnimation(final String assetName)
//setAnimationFromJson(String jsonString) //不建议使用
//setAnimationFromJson(String jsonString, @Nullable String cacheKey)
//setAnimation(JsonReader reader, @Nullable String cacheKey)
//setAnimationFromUrl(String url)