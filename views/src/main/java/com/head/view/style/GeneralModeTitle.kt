package com.head.view.style

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View.MeasureSpec
import android.view.ViewGroup
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
    var layoutChange: LayoutChange? = null

) {
    val leftTextView: TextView by lazy { TextView(mContext).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER_VERTICAL
        )
        isSingleLine = true
        ellipsize = null;
    } }
    val centerLinearLayout: LinearLayout by lazy { LinearLayout(mContext).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
    } }
    val rightTextView: TextView by lazy { TextView(mContext).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER_VERTICAL or Gravity.RIGHT
        )
        isSingleLine = true
        ellipsize = null;
    } }
    val centerMainTitleTextView: TextView by lazy { TextView(mContext).apply {
        ellipsize = null;
        isSingleLine = true;

    } }
    val centerSubTitleTextView: TextView by lazy { TextView(mContext).apply {
        isSingleLine = true
        ellipsize = null;
    } }

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
    Log.e("TAG", "modifyBuilderGeneralModeTitle: ")
    loadView()(modifyAction(generalModeTitle))
    centerLinearLayout.removeAllViews()
    centerLinearLayout.addView(centerMainTitleTextView)
    centerLinearLayout.addView(centerSubTitleTextView)

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
        centerMainTitleTextView.text = "主标题"
        centerSubTitleTextView.text = "次标题"


        //测量左中右的位置
        if (layoutChange != null) {
            if (leftTextView.maxWidth != Int.MAX_VALUE && rightTextView.maxWidth != Int.MAX_VALUE && centerMainTitleTextView.maxWidth != Int.MAX_VALUE) {
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
            val sideWidth: Int = max(leftTextView.measuredWidth, rightTextView.measuredWidth)
            val maxWidth: Int = sideWidth * 2 + centerMainTitleTextView.measuredWidth
            // 算出来子 View 的宽大于标题栏的宽度
            if (maxWidth >= barWidth) {
                // 判断是左右项太长还是标题项太长
                if (sideWidth > barWidth / 3) {
                    // 如果是左右项太长，那么按照比例进行划分
                    leftTextView.maxWidth = barWidth / 4
                    centerMainTitleTextView.maxWidth = barWidth / 2
                    centerSubTitleTextView.maxWidth = barWidth / 2
                    rightTextView.maxWidth = barWidth / 4
                } else {
                    // 如果是标题项太长，那么就进行动态计算
                    leftTextView.maxWidth = sideWidth
                    centerMainTitleTextView.maxWidth = barWidth - sideWidth * 2
                    centerSubTitleTextView.maxWidth = barWidth - sideWidth * 2
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

        }
    }
}

data class LayoutChange(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val oldLeft: Int,
    val oldTop: Int,
    val oldRight: Int,
    val oldBottom: Int
)