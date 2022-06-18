package com.head.view.style

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes


interface GeneralModeState {

    fun leftIcon(@DrawableRes icon: Int):GeneralModeState
    fun leftText(text:CharSequence):GeneralModeState
    fun leftTextSize(size: Float):GeneralModeState
    fun leftTextColor(@ColorInt color: Int):GeneralModeState
    fun setOnLeftListener(listener: (v: View) -> Unit):GeneralModeState


    fun rightIcon(@DrawableRes icon: Int):GeneralModeState
    fun rightText(text:CharSequence):GeneralModeState
    fun rightTextSize(size: Float):GeneralModeState
    fun rightTextColor(@ColorInt color: Int):GeneralModeState
    fun setOnRightListener(listener: (v: View) -> Unit):GeneralModeState

    fun centerMainText(text:CharSequence):GeneralModeState
    fun centerMainTextSize(size: Float):GeneralModeState
    fun centerMainTextColor(@ColorInt color: Int):GeneralModeState
    fun centerMainMarquee(isMarquee:Boolean):GeneralModeState
    fun setOnCenterMainListener(listener: (v: View) -> Unit):GeneralModeState
   
    fun centerSubText(text:CharSequence):GeneralModeState
    fun centerSubTextSize(size: Float):GeneralModeState
    fun centerSubTextColor(@ColorInt color: Int):GeneralModeState
    fun centerSubMarquee(isMarquee:Boolean):GeneralModeState
    fun setOnCenterSubListener(listener: (v: View) -> Unit):GeneralModeState

}
