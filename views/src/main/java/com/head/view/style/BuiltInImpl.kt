package com.head.view.style

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.head.view.utils.TemplateDrawable


interface BuiltInImpl {

    fun leftIcon(@DrawableRes icon: Int): BuiltInImpl
    fun leftText(text: CharSequence): BuiltInImpl
    fun leftTextSize(size: Float): BuiltInImpl
    fun leftTextColor(@ColorInt color: Int): BuiltInImpl
    fun setOnLeftListener(listener: (v: View) -> Unit): BuiltInImpl


    fun rightIcon(@DrawableRes icon: Int): BuiltInImpl
    fun rightText(text: CharSequence): BuiltInImpl
    fun rightTextSize(size: Float): BuiltInImpl
    fun rightTextColor(@ColorInt color: Int): BuiltInImpl
    fun setOnRightListener(listener: (v: View) -> Unit): BuiltInImpl
    fun setOnRightSearchListener(listener: (v: View, text: String) -> Unit): BuiltInImpl

    fun centerMainText(text: CharSequence): BuiltInImpl
    fun centerMainTextSize(size: Float): BuiltInImpl
    fun centerMainTextColor(@ColorInt color: Int): BuiltInImpl
    fun centerMainMarquee(isMarquee: Boolean): BuiltInImpl
    fun setOnCenterMainListener(listener: (v: View) -> Unit): BuiltInImpl
    fun centerSubText(text: CharSequence): BuiltInImpl
    fun centerSubTextSize(size: Float): BuiltInImpl
    fun centerSubTextColor(@ColorInt color: Int): BuiltInImpl
    fun centerSubMarquee(isMarquee: Boolean): BuiltInImpl
    fun setOnCenterSubListener(listener: (v: View) -> Unit): BuiltInImpl

    fun setOnSearchEditorActionListener(listener: (v: View, text: String) -> Unit): BuiltInImpl
    fun setOnSearchClearListener(listener: (v: View) -> Unit): BuiltInImpl

    fun setSearchSoftInputKeyBoard(isShow: Boolean): BuiltInImpl
    fun setSearchLeftIcon(iconRes: Int): BuiltInImpl
    fun setSearchHint(text: CharSequence): BuiltInImpl
    fun setSearchHintColor(@ColorInt color: Int): BuiltInImpl
    fun setSearchTextColor(@ColorInt color: Int): BuiltInImpl
    fun setSearchTextSize(size: Float): BuiltInImpl
    fun setSearchAttribute(drawable: TemplateDrawable): BuiltInImpl


}
