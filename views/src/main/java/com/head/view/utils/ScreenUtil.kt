package com.head.view.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 *
 * 类名称：ScreenUtil.kt <br/>
 * 类描述：分辨率适配工具 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/17 17:11 <br/>
 * @version
 */
object ScreenUtil {
    private fun dp2px(context: Context?, dp: Float): Float {
        return if (context == null) {
            (-1).toFloat()
        } else dp * density(context)
    }

    fun px2dp(context: Context?, px: Float): Float {
        return if (context == null) {
            (-1).toFloat()
        } else px / density(context)
    }

    private fun density(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun dp2PxInt(context: Context?, dp: Float): Int {
        return (dp2px(context, dp) + 0.5f).toInt()
    }

    private fun getDisplayMetrics(context: Context): DisplayMetrics {
        val activity: Activity = if (context !is Activity && context is ContextWrapper) {
            context.baseContext as Activity
        } else {
            context as Activity
        }
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics
    }

    fun getScreenPixelSize(context: Context): IntArray? {
        val metrics: DisplayMetrics = getDisplayMetrics(context)
        return intArrayOf(metrics.widthPixels, metrics.heightPixels)
    }

    fun hideSoftInputKeyBoard(focusView: View?) {
        focusView?.let {
            val imm = focusView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusView.windowToken, 0);
        }
    }

    fun showSoftInputKeyBoard(context: Context, focusView: View?) {
        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(focusView, InputMethodManager.SHOW_FORCED)
    }


    //根据当前键盘状态切换，显示就隐藏，隐藏就显示
    fun toggleKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, 0)
    }


}