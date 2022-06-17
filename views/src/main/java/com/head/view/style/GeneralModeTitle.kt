package com.head.view.style

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.head.view.R
import kotlin.math.max

/**
 *
 * 类名称： GeneralModeTitle<br/>
 * 类描述： 通用标题<br/>
 * 创建人： <br/>
 * 创建时间：  <br/>
 * @version
 */
data class GeneralModeTitle(
    var mContext: Context? = null,

    var leftText: String = "",
    var leftTextColor: Int = Color.WHITE,
    var leftTextSize: Float = 0F,
    var leftIcon: Int = 0,

    var rightText: String = "",
    var rightTextColor: Int = Color.WHITE,
    var rightTextSize: Float = 0F,
    var rightIcon: Int = 0,

    var centerMainTitleText: String = "",
    var centerMainTitleTextColor: Int = Color.WHITE,
    var centerMainTitleTextSize: Float = 0F,
    var isCenterMainTitleMarquee: Boolean = false,

    var centerSubTitleText: String = "",
    var centerSubTitleTextColor: Int = Color.WHITE,
    var centerSubTitleTextSize: Float = 0F,
    var isCenterSubTitleMarquee: Boolean = false,


    var layoutChange: LayoutChange? = null


) {
    val leftTextView: TextView by lazy {
        TextView(mContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL
            )
            isSingleLine = true
            ellipsize = null
        }
    }
    val centerLinearLayout: LinearLayout by lazy {
        LinearLayout(mContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
    }
    val rightTextView: TextView by lazy {
        TextView(mContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL or Gravity.RIGHT
            )
            isSingleLine = true
            ellipsize = null
        }
    }
    val centerMainTitleTextView: TextView by lazy {
        TextView(mContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            ellipsize = null
            isSingleLine = true

        }
    }
    val centerSubTitleTextView: TextView by lazy {
        TextView(mContext).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
            ellipsize = null
            isSingleLine = true
        }
    }

}

fun builderGeneralModeTitle(context: Context?, builder: GeneralModeTitle.() -> GeneralModeTitle) =
    GeneralModeTitle().apply {
        mContext = context
        loadView()(builder(this))
    }

fun modifyBuilderGeneralModeTitle(
    generalModeTitle: GeneralModeTitle,
    modifyAction: GeneralModeTitle.() -> GeneralModeTitle
) = generalModeTitle.apply {
    loadView()(modifyAction(generalModeTitle))
    centerLinearLayout.removeAllViews()
    centerLinearLayout.addView(centerMainTitleTextView)
    if (centerSubTitleText != "") centerLinearLayout.addView(centerSubTitleTextView)

}

private fun loadView(): GeneralModeTitle.() -> Unit {
    return {


        //左边视图
        leftTextView.text = leftText
        val drawableLeft = if (leftIcon == 0) null else ContextCompat.getDrawable(
            mContext!!,
            leftIcon
        )
        drawableLeft?.let {
            it.setBounds(
                0,
                0,
                it.intrinsicWidth,
                it.intrinsicHeight
            )
        }
        leftTextView.setCompoundDrawables(drawableLeft, null, null, null)
        leftTextView.paint.textSize =
            if (leftTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_left_textview_size) else leftTextSize
        leftTextView.setTextColor(leftTextColor)

        //右边视图
        rightTextView.text = rightText
        val drawableRight = if (rightIcon == 0) null else ContextCompat.getDrawable(
            mContext!!,
            rightIcon
        )
        drawableRight?.let {
            it.setBounds(
                0,
                0,
                it.intrinsicWidth,
                it.intrinsicHeight
            )
        }
        rightTextView.setCompoundDrawables(null, null, drawableRight, null)
        rightTextView.paint.textSize =
            if (rightTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_right_textview_size) else rightTextSize
        rightTextView.setTextColor(rightTextColor)

        // 中间视图
        //主标题
        centerMainTitleTextView.text = centerMainTitleText
        centerMainTitleTextView.setTextColor(centerMainTitleTextColor)
        centerMainTitleTextView.paint.textSize = centerMainTitleTextSize

        if (isCenterMainTitleMarquee) {
            //跑马灯
            centerMainTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerMainTitleTextView.marqueeRepeatLimit = -1
            centerMainTitleTextView.requestFocus()
            centerMainTitleTextView.isSelected = true
        }

        //次要标题
        centerSubTitleTextView.text = centerSubTitleText
        centerSubTitleTextView.setTextColor(centerSubTitleTextColor)
        centerSubTitleTextView.paint.textSize = centerSubTitleTextSize

        if (isCenterSubTitleMarquee) {
            //跑马灯
            centerSubTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerSubTitleTextView.marqueeRepeatLimit = -1
            centerSubTitleTextView.requestFocus()
            centerSubTitleTextView.isSelected = true
        }


        //测量左中右的位置
        if (layoutChange != null) {
            centerLinearLayout.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    centerLinearLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    if (leftTextView.maxWidth != Int.MAX_VALUE && rightTextView.maxWidth != Int.MAX_VALUE && centerMainTitleTextView.maxWidth != Int.MAX_VALUE && centerMainTitleTextView.maxWidth != Int.MAX_VALUE) {
                        leftTextView.maxWidth = Int.MAX_VALUE
                        rightTextView.maxWidth = Int.MAX_VALUE
                        centerMainTitleTextView.maxWidth = Int.MAX_VALUE
                        centerSubTitleTextView.maxWidth = Int.MAX_VALUE
                        leftTextView.measure(
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                        )
                        rightTextView.measure(
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                        )
                        centerMainTitleTextView.measure(
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                        )
                        centerSubTitleTextView.measure(
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                        )
                    }

                    // 标题栏子 View 最大宽度限制算法
                    val barWidth: Int = layoutChange!!.right - layoutChange!!.left
                    val sideWidth: Int =
                        max(leftTextView.measuredWidth, rightTextView.measuredWidth)

                    val maxWidth: Int = sideWidth * 2 + max(
                        centerMainTitleTextView.measuredWidth,
                        centerSubTitleTextView.measuredWidth
                    )
                    // 算出来子 View 的宽大于标题栏的宽度
                    if (maxWidth >= barWidth) {
                        // 判断是左右项太长还是标题项太长
                        if (sideWidth > barWidth / 3) {
                            // 如果是左右项太长，那么按照比例进行划分
                            leftTextView.maxWidth = barWidth / 4
                            centerMainTitleTextView.maxWidth =
                                barWidth / 2 - layoutChange!!.paddingLeft - layoutChange!!.paddingRight
                            centerSubTitleTextView.maxWidth =
                                barWidth / 2 - layoutChange!!.paddingLeft - layoutChange!!.paddingRight
                            rightTextView.maxWidth = barWidth / 4
                        } else {
                            // 如果是标题项太长，那么就进行动态计算
                            leftTextView.maxWidth = sideWidth
                            centerMainTitleTextView.maxWidth =
                                barWidth - sideWidth * 2 - layoutChange!!.paddingLeft - layoutChange!!.paddingRight
                            centerSubTitleTextView.maxWidth =
                                barWidth - sideWidth * 2 - layoutChange!!.paddingLeft - layoutChange!!.paddingRight
                            rightTextView.maxWidth = sideWidth
                        }
                    } else {
                        if (leftTextView.maxWidth != Int.MAX_VALUE && centerMainTitleTextView.maxWidth != Int.MAX_VALUE && rightTextView.maxWidth != Int.MAX_VALUE) {
                            // 不限制子 View 的最大宽度
                            leftTextView.maxWidth = Int.MAX_VALUE
                            centerMainTitleTextView.maxWidth = Int.MAX_VALUE
                            centerSubTitleTextView.maxWidth = Int.MAX_VALUE
                            rightTextView.maxWidth = Int.MAX_VALUE
                        }
                    }

                    centerLinearLayout.post {
                        centerLinearLayout.viewTreeObserver.addOnGlobalLayoutListener(this)
                    }

                }
            })
        }
    }
}

data class LayoutChange(
    val left: Int,
    val right: Int,
    val paddingLeft: Int,
    val paddingRight: Int,
)

