package com.head.view.style

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.view.View
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

    var leftText: CharSequence = "",
    var leftTextColor: Int = Color.WHITE,
    var leftTextSize: Float = 0F,
    var leftIcon: Int = 0,

    var rightText: CharSequence = "",
    var rightTextColor: Int = Color.WHITE,
    var rightTextSize: Float = 0F,
    var rightIcon: Int = 0,

    var centerMainTitleText: CharSequence = "",
    var centerMainTitleTextColor: Int = Color.WHITE,
    var centerMainTitleTextSize: Float = 0F,
    var isCenterMainTitleMarquee: Boolean = false,

    var centerSubTitleText: CharSequence = "",
    var centerSubTitleTextColor: Int = Color.WHITE,
    var centerSubTitleTextSize: Float = 0F,
    var isCenterSubTitleMarquee: Boolean = false,
    var leftListener:( (v: View) -> Unit)? = null,
    var rightListener:( (v: View) -> Unit)? = null,
    var centerMainListener:( (v: View) -> Unit)? = null,
    var centerSubListener:( (v: View) -> Unit)? = null,
    var layoutChange: LayoutChange? = null


) : GeneralModeState {
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

    override fun leftText(text: CharSequence): GeneralModeState {
        leftText = text
        leftTextView.text = leftText
        return this
    }

    override fun leftIcon(icon: Int): GeneralModeState {
        leftIcon = icon
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
        return this
    }

    override fun leftTextSize(size: Float): GeneralModeState {
        leftTextSize = size
        leftTextView.paint.textSize = if (leftTextSize == 0F) mContext!!
            .resources.getDimension(R.dimen.head_left_textview_size)
        else leftTextSize
        return this
    }

    override fun leftTextColor(color: Int): GeneralModeState {
        leftTextColor = color
        leftTextView.setTextColor(leftTextColor)
        return this
    }

    override fun setOnLeftListener(listener: (v: View) -> Unit): GeneralModeState {
        this.leftListener = listener
        leftTextView.setOnClickListener(leftListener)
        return this
    }

    override fun rightText(text: CharSequence): GeneralModeState {
        rightText = text
        rightTextView.text = rightText
        return this
    }

    override fun rightIcon(icon: Int): GeneralModeState {
        rightIcon =icon
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
        return this
    }

    override fun rightTextSize(size: Float): GeneralModeState {
        rightTextSize =size
        rightTextView.paint.textSize =
            if (rightTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_right_textview_size) else rightTextSize

        return this
    }

    override fun rightTextColor(color: Int): GeneralModeState {
        rightTextColor =color
        rightTextView.setTextColor(rightTextColor)
        return this
    }

    override fun setOnRightListener(listener: (v: View) -> Unit): GeneralModeState {
        this.rightListener = listener
        rightTextView.setOnClickListener(rightListener)
        return this
    }

    override fun centerMainText(text: CharSequence): GeneralModeState {
        centerMainTitleText  = text
        centerMainTitleTextView.text = centerMainTitleText


        return this
    }

    override fun centerMainTextSize(size: Float): GeneralModeState {
        centerMainTitleTextSize =size
        centerMainTitleTextView.paint.textSize =
            if (centerMainTitleTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_center_main_textview_size) else centerMainTitleTextSize

        return this

    }

    override fun centerMainTextColor(color: Int): GeneralModeState {
        centerMainTitleTextColor =color
        centerMainTitleTextView.setTextColor(centerMainTitleTextColor)

        return this

    }

    override fun centerMainMarquee(isMarquee: Boolean): GeneralModeState {
        isCenterMainTitleMarquee =isMarquee
        if (isCenterMainTitleMarquee) {
            centerMainTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerMainTitleTextView.marqueeRepeatLimit = -1
            centerMainTitleTextView.requestFocus()
            centerMainTitleTextView.isSelected = true
        }

        return this

    }

    override fun setOnCenterMainListener(listener: (v: View) -> Unit): GeneralModeState {
        this.centerMainListener = listener
        centerMainTitleTextView.setOnClickListener(centerMainListener)
        return this
    }

    override fun centerSubText(text: CharSequence): GeneralModeState {
        centerSubTitleText  = text
        centerSubTitleTextView.text = centerSubTitleText
        return this
    }

    override fun centerSubTextSize(size: Float): GeneralModeState {
        centerSubTitleTextSize =size
        centerSubTitleTextView.paint.textSize =
            if (centerSubTitleTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_center_sub_textview_size) else centerSubTitleTextSize

        return this

    }

    override fun centerSubTextColor(color: Int): GeneralModeState {
        centerSubTitleTextColor =color
        centerSubTitleTextView.setTextColor(centerSubTitleTextColor)

        return this

    }

    override fun centerSubMarquee(isMarquee: Boolean): GeneralModeState {
        isCenterSubTitleMarquee =isMarquee
        if (isCenterSubTitleMarquee) {
            centerSubTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerSubTitleTextView.marqueeRepeatLimit = -1
            centerSubTitleTextView.requestFocus()
            centerSubTitleTextView.isSelected = true
        }
        return this

    }

    override fun setOnCenterSubListener(listener: (v: View) -> Unit): GeneralModeState {
        this.centerSubListener = listener
        centerSubTitleTextView.setOnClickListener(centerSubListener)
        return this
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
        this.leftText(leftText)
            .leftIcon(leftIcon)
            .leftTextSize(leftTextSize)
            .leftTextColor(leftTextColor)

        //右边视图
        this.rightText(rightText)
            .rightIcon(rightIcon)
            .rightTextSize(rightTextSize)
            .rightTextColor(rightTextColor)

        // 中间视图
        //主标题
        this.centerMainText(centerMainTitleText)
            .centerMainTextColor(centerMainTitleTextColor)
            .centerMainTextSize(centerMainTitleTextSize)
            .centerMainMarquee(isCenterMainTitleMarquee)

        //次要标题
        this.centerSubText(centerSubTitleText)
            .centerSubTextColor(centerSubTitleTextColor)
            .centerSubTextSize(centerSubTitleTextSize)
            .centerSubMarquee(isCenterSubTitleMarquee)


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

