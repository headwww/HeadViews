package com.head.view.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt

object StatusBarUtil {
    /**
     * 设备是否支持透明状态栏
     */
    fun supportTransparentStatusBar(): Boolean {
        return OSUtil.isVivo()
                || OSUtil.isMiui()
                || OSUtil.isFlyme()
                || (OSUtil.isOppo() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    }

    /**
     * 设置状态栏透明
     *
     * @param activity
     */
    fun transparentStatusBar(activity: Activity) {
        activity.window.addFlags(
            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
        )
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        activity.window.statusBarColor = Color.TRANSPARENT
        if (OSUtil.isMiui() || OSUtil.isFlyme()) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    /**
     * 设置状态栏图标白色主题
     *
     * @param activity
     */
    open fun setLightMode(activity: Activity) {
        if (OSUtil.isMiui()) {
            setMIUIStatusBarDarkMode(activity, false)

        } else if (OSUtil.isFlyme()) {
            FlymeStatusBarUtil.setStatusBarDarkIcon(activity.window, false)

        } else if (OSUtil.isOppo()) {
            setOppoStatusBarDarkMode(activity, false)

        } else {
            setStatusBarDarkMode(activity, false)
        }
    }

    open fun setDarkMode(activity: Activity) {
        if (OSUtil.isMiui()) {
            setMIUIStatusBarDarkMode(activity, true)

        } else if (OSUtil.isFlyme()) {
            FlymeStatusBarUtil.setStatusBarDarkIcon(activity.window, true)

        } else if (OSUtil.isOppo()) {
            setOppoStatusBarDarkMode(activity, true)

        } else {
            setStatusBarDarkMode(activity, true)
        }
    }

    private fun setOppoStatusBarDarkMode(
        activity: Activity,
        darkMode: Boolean
    ) {
        var vis = activity.window.decorView.systemUiVisibility
        vis = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkMode) {
                vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        } else
            if (darkMode) {
                vis or 0x00000010
            } else {
                vis and 0x00000010.inv()

            }
        activity.window.decorView.systemUiVisibility = vis
    }

    private fun setMIUIStatusBarDarkMode(activity: Activity, darkMode: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val clazz: Class<out Window?> = activity.window.javaClass
            try {
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                val darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                extraFlagField
                    .invoke(activity.window, if (darkMode) darkModeFlag else 0, darkModeFlag)
            } catch (e: Exception) {
            }
        }
        setStatusBarDarkMode(activity, darkMode)
    }


    private fun setStatusBarDarkMode(activity: Activity, darkMode: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkMode) {
                activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }

    /**
     * 设置状态栏颜色和透明度
     *
     * @param window
     * @param color
     * @param alpha
     */
    fun setStatusBarColor(
        window: Window, @ColorInt color: Int,
        alpha: Int
    ) {
        window.statusBarColor = calculateStatusColor(color, alpha)
    }


    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private fun calculateStatusColor(@ColorInt color: Int, alpha: Int): Int {
        if (alpha == 0) {
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }


    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context) = context.resources.getDimensionPixelSize(
        context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
    )

}