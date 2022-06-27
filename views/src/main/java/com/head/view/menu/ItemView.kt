package com.head.view.menu

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class ItemView(@DrawableRes var drawable: Int, @ColorInt var color: Int = Color.WHITE)