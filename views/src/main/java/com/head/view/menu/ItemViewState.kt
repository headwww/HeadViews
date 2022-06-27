package com.head.view.menu

import android.graphics.drawable.Drawable
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuItemImpl

interface ItemViewState {
    fun isChecked():Boolean
    fun setChecked(checked: Boolean)
}
