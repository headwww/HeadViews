package com.head.view.style

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.head.view.HeadEditTextView
import com.head.view.HeadTitleBar
import com.head.view.R
import com.head.view.utils.ScreenUtil
import com.head.view.drawable.TemplateDrawable
import kotlin.math.max

/**
 *
 * 类名称： BuiltInStyle<br/>
 * 类描述： 内置了两种方式的标题，通用模式和搜索模式<br/>
 * 创建人： <br/>
 * 创建时间：  <br/>
 * @version
 */
data class BuiltInStyle(
    var mContext: Context? = null,
    var style: Int = HeadTitleBar.HeadTitleStyle.GENERAL.ordinal,
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
    var leftListener: ((v: View) -> Unit)? = null,
    var rightListener: ((v: View) -> Unit)? = null,
    var rightTextListener: ((v: View, text: String) -> Unit)? = null,
    var onEditorActionListener: ((v: View, text: String) -> Unit)? = null,
    var onSearchClearListener: ((v: View) -> Unit)? = null,
    var centerMainListener: ((v: View) -> Unit)? = null,
    var centerSubListener: ((v: View) -> Unit)? = null,
    var isSoftInputKeyBoard: Boolean = false,
    var centerSearchHint: CharSequence = "",
    var centerSearchText: CharSequence = "",
    var centerSearchHintColor: Int = Color.WHITE,
    var centerSearchTextSize: Float = 0F,
    var centerSearchTextColor: Int = Color.WHITE,
    var centerSearchLeftIcon: Int = -1,
    var templateSearchDrawable: TemplateDrawable = TemplateDrawable(),
    var layoutChange: LayoutChange? = null

) : BuiltInImpl {
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
    val centerSearchView: HeadEditTextView by lazy {
        HeadEditTextView(mContext!!).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )

            imeOptions = EditorInfo.IME_ACTION_SEARCH

            setHeadEditTextClear(true)

            setPadding(
                resources.getDimension(R.dimen.head_center_search_padding_horizontal).toInt(),
                resources.getDimension(R.dimen.head_center_search_padding_vertical).toInt(),
                resources.getDimension(R.dimen.head_center_search_padding_horizontal).toInt(),
                resources.getDimension(R.dimen.head_center_search_padding_vertical).toInt(),
            )
            ellipsize = null
            isSingleLine = true
        }
    }

    override fun leftText(text: CharSequence): BuiltInImpl {
        leftText = text
        leftTextView.text = leftText
        return this
    }

    override fun leftIcon(icon: Int): BuiltInImpl {
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

    override fun leftTextSize(size: Float): BuiltInImpl {
        leftTextSize = size
        leftTextView.paint.textSize = if (leftTextSize == 0F) mContext!!
            .resources.getDimension(R.dimen.head_view_default_size)
        else leftTextSize
        return this
    }

    override fun leftTextColor(color: Int): BuiltInImpl {
        leftTextColor = color
        leftTextView.setTextColor(leftTextColor)
        return this
    }

    override fun setOnLeftListener(listener: (v: View) -> Unit): BuiltInImpl {
        this.leftListener = listener
        leftTextView.setOnClickListener(leftListener)
        return this
    }

    override fun rightText(text: CharSequence): BuiltInImpl {
        rightText = text
        rightTextView.text = rightText
        return this
    }

    override fun rightIcon(icon: Int): BuiltInImpl {
        rightIcon = icon
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

    override fun rightTextSize(size: Float): BuiltInImpl {
        rightTextSize = size
        rightTextView.paint.textSize =
            if (rightTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_view_default_size) else rightTextSize

        return this
    }

    override fun rightTextColor(color: Int): BuiltInImpl {
        rightTextColor = color
        rightTextView.setTextColor(rightTextColor)
        return this
    }

    override fun setOnRightListener(listener: (v: View) -> Unit): BuiltInImpl {
        this.rightListener = listener
        rightTextView.setOnClickListener(rightListener)
        return this
    }

    override fun setOnRightSearchListener(listener: (v: View, text: String) -> Unit): BuiltInImpl {
        this.rightTextListener = listener
        rightTextView.setOnClickListener {
            rightTextListener?.invoke(it, centerSearchView.text.toString())
        }
        return this
    }


    override fun centerMainText(text: CharSequence): BuiltInImpl {
        centerMainTitleText = text
        centerMainTitleTextView.text = centerMainTitleText
        return this
    }

    override fun centerMainTextSize(size: Float): BuiltInImpl {
        centerMainTitleTextSize = size
        centerMainTitleTextView.paint.textSize =
            if (centerMainTitleTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_view_default_size) else centerMainTitleTextSize

        return this

    }

    override fun centerMainTextColor(color: Int): BuiltInImpl {
        centerMainTitleTextColor = color
        centerMainTitleTextView.setTextColor(centerMainTitleTextColor)

        return this

    }

    override fun centerMainMarquee(isMarquee: Boolean): BuiltInImpl {
        isCenterMainTitleMarquee = isMarquee
        if (isCenterMainTitleMarquee) {
            centerMainTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerMainTitleTextView.marqueeRepeatLimit = -1
            centerMainTitleTextView.requestFocus()
            centerMainTitleTextView.isSelected = true
        }

        return this

    }

    override fun setOnCenterMainListener(listener: (v: View) -> Unit): BuiltInImpl {
        this.centerMainListener = listener
        centerMainTitleTextView.setOnClickListener(centerMainListener)
        return this
    }

    override fun centerSubText(text: CharSequence): BuiltInImpl {
        centerSubTitleText = text
        centerSubTitleTextView.text = centerSubTitleText
        return this
    }

    override fun centerSubTextSize(size: Float): BuiltInImpl {
        centerSubTitleTextSize = size
        centerSubTitleTextView.paint.textSize =
            if (centerSubTitleTextSize == 0F) mContext!!.resources.getDimension(R.dimen.head_center_sub_textview_size) else centerSubTitleTextSize

        return this

    }

    override fun centerSubTextColor(color: Int): BuiltInImpl {
        centerSubTitleTextColor = color
        centerSubTitleTextView.setTextColor(centerSubTitleTextColor)

        return this

    }

    override fun centerSubMarquee(isMarquee: Boolean): BuiltInImpl {
        isCenterSubTitleMarquee = isMarquee
        if (isCenterSubTitleMarquee) {
            centerSubTitleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            centerSubTitleTextView.marqueeRepeatLimit = -1
            centerSubTitleTextView.requestFocus()
            centerSubTitleTextView.isSelected = true
        }
        return this

    }

    override fun setOnCenterSubListener(listener: (v: View) -> Unit): BuiltInImpl {
        this.centerSubListener = listener
        centerSubTitleTextView.setOnClickListener(centerSubListener)
        return this
    }

    override fun setOnSearchEditorActionListener(listener: (v: View, text: String) -> Unit): BuiltInImpl {
        this.onEditorActionListener = listener
        centerSearchView.setOnEditorActionListener { _, actionId, keyEvent ->
            if ((actionId == EditorInfo.IME_ACTION_UNSPECIFIED || actionId == EditorInfo.IME_ACTION_SEARCH) && keyEvent != null) {
                //点击搜索要做的操作
                onEditorActionListener?.invoke(centerSearchView, centerSearchView.text.toString())
                true
            }
            false
        }
        return this
    }

    override fun setOnSearchClearListener(listener: (v: View) -> Unit): BuiltInImpl {
        this.onSearchClearListener = listener
        centerSearchView.setOnRightDrawableClickListener {
            onSearchClearListener?.invoke(it)
        }
        return this
    }


    override fun setSearchSoftInputKeyBoard(isShow: Boolean): BuiltInImpl {
        this.isSoftInputKeyBoard = isShow
        if (isSoftInputKeyBoard) {
            centerSearchView.postDelayed({
                centerSearchView.isFocusable = true
                centerSearchView.isFocusableInTouchMode = true
                centerSearchView.requestFocus()
                ScreenUtil.showSoftInputKeyBoard(mContext!!, centerSearchView)
            }, 300)
        }
        return this
    }

    override fun setSearchLeftIcon(iconRes: Int): BuiltInImpl {
        centerSearchLeftIcon = iconRes
        if (centerSearchLeftIcon != -1) {
            var drawable = ContextCompat.getDrawable(mContext!!, centerSearchLeftIcon)
            drawable?.let {
                it.setBounds(
                    0,
                    0,
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight
                )
            }
            centerSearchView.setCompoundDrawables(drawable, null, null, null)
        }
        return this
    }

    override fun setSearchHint(text: CharSequence): BuiltInImpl {
        this.centerSearchHint = text
        centerSearchView.hint = centerSearchHint
        return this
    }

    override fun setSearchText(text: CharSequence): BuiltInImpl {
        this.centerSearchText = text
        centerSearchView.setText(centerSearchText)
        centerSearchView.setSelection(centerSearchText.length)
        return this
    }

    override fun setSearchHintColor(color: Int): BuiltInImpl {
        this.centerSearchHintColor = color
        centerSearchView.setHintTextColor(centerSearchHintColor)

        return this
    }

    override fun setSearchTextColor(color: Int): BuiltInImpl {
        this.centerSearchTextColor = color
        centerSearchView.setTextColor(this.centerSearchTextColor)
        return this
    }

    override fun setSearchTextSize(size: Float): BuiltInImpl {
        this.centerSearchTextSize = size
        centerSearchView.paint.textSize = if (centerSearchTextSize == 0F) mContext!!
            .resources.getDimension(R.dimen.head_view_default_size)
        else centerSearchTextSize
        return this
    }

    override fun setSearchAttribute(drawable: TemplateDrawable): BuiltInImpl {
        centerSearchView.background = drawable
        return this
    }
}

fun builderTitle(context: Context?, builder: BuiltInStyle.() -> BuiltInStyle) =
    BuiltInStyle().apply {
        mContext = context
        builder(this)
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

        if (style == HeadTitleBar.HeadTitleStyle.GENERAL.ordinal) {
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
        }
        if (style == HeadTitleBar.HeadTitleStyle.SEARCH.ordinal) {
            this.setSearchSoftInputKeyBoard(isSoftInputKeyBoard)
                .setSearchHint(centerSearchHint)
                .setSearchText(centerSearchText)
                .setSearchHintColor(centerSearchHintColor)
                .setSearchLeftIcon(centerSearchLeftIcon)
                .setSearchAttribute(templateSearchDrawable)
                .setSearchTextColor(centerSearchTextColor)
                .setSearchTextSize(centerSearchTextSize)
        }


    }

fun modifyTitle(
    builtIn: BuiltInStyle,
    modify: BuiltInStyle.() -> BuiltInStyle
) = builtIn.apply {
    centerLinearLayout.removeAllViews()
    if (style == HeadTitleBar.HeadTitleStyle.GENERAL.ordinal) {
        measureGeneral()(modify(builtIn))
        centerLinearLayout.addView(centerMainTitleTextView)
        if (centerSubTitleText != "") centerLinearLayout.addView(centerSubTitleTextView)
    }
    if (style == HeadTitleBar.HeadTitleStyle.SEARCH.ordinal) {
        measureSearch()(modify(builtIn))
        centerLinearLayout.addView(centerSearchView)
    }

}

private fun measureSearch(): BuiltInStyle.() -> Unit {
    return {
        layoutChange?.let {
            val barWidth: Int = it.right - it.left
            centerSearchView.maxWidth = barWidth
            centerSearchView.minWidth = barWidth
            var centerLayoutPaddingLeft = 0
            var centerLayoutPaddingRight = 0
            //如果左侧的view小于1/5则宽度取本身的width，如果大于

            if (leftTextView.width <= barWidth / 5) {
                centerLayoutPaddingLeft = leftTextView.width
                leftTextView.maxWidth = leftTextView.width
            } else {
                centerLayoutPaddingLeft = barWidth / 5
                leftTextView.maxWidth = barWidth / 5
            }
            if (rightTextView.width <= barWidth / 5) {
                centerLayoutPaddingRight = rightTextView.width
                rightTextView.maxWidth = rightTextView.width
            } else {
                centerLayoutPaddingRight = barWidth / 5
                rightTextView.maxWidth = barWidth / 5
            }
            centerLinearLayout.setPadding(
                centerLayoutPaddingLeft + it.paddingLeft,
                0,
                centerLayoutPaddingRight + it.paddingRight,
                0
            )

        }
    }
}

private fun measureGeneral(): BuiltInStyle.() -> Unit {
    return {
        //测量左中右的位置
        layoutChange?.let {
            centerLinearLayout.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    centerLinearLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    //屏幕后重新测量
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
                    val barWidth: Int = it.right - it.left
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
                                barWidth / 2 - it.paddingLeft - it.paddingRight
                            centerSubTitleTextView.maxWidth =
                                barWidth / 2 - it.paddingLeft - it.paddingRight
                            rightTextView.maxWidth = barWidth / 4
                        } else {
                            // 如果是标题项太长，那么就进行动态计算
                            leftTextView.maxWidth = sideWidth
                            centerMainTitleTextView.maxWidth =
                                barWidth - sideWidth * 2 - it.paddingLeft - it.paddingRight
                            centerSubTitleTextView.maxWidth =
                                barWidth - sideWidth * 2 - it.paddingLeft - it.paddingRight
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

