package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter


/**
 *
 * 类名称：HeadEditTextView.kt <br/>
 * 类描述：自定义的文本编辑框有如下功能
 * ·支持设置背景颜色,支持渐变色
 * ·支持设置控件弧度,可单独设置每个角的弧度（全部设置的权重大于单独设置）
 * ·支持EditText和TextView切换
 * ·支持左侧图片自定义和左侧图片的点击事件
 * ·支持上侧图片自定义和上侧图片的点击事件
 * ·支持下侧图片自定义和下侧图片的点击事件
 * ·支持右侧图片自定义和右侧图片的点击事件
 * ·支持自定义边框颜色和宽度,设置虚线的长度（以像素为单位），设置为0可禁用虚线，虚线之间的像素间距
 * ·右侧图标按钮默认删除文本模式，当开启后右侧原有的自定义图标权重下降
 *
 * <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/5/30 17:54 <br/>
 * @version
 */
class HeadEditTextView :
    AppCompatEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }


    /**
     * 背景色
     */
    @ColorInt
    private var headBackgroundColor: Int = Color.TRANSPARENT

    /**
     * 是否支持渐变色
     */
    private var headSupportGradient: Boolean = false

    /**
     * 渐变色的起始色
     */
    @ColorInt
    private var headGradientFrom: Int = Color.TRANSPARENT

    /**
     * 渐变色的结束色
     */
    @ColorInt
    private var headGradientTo: Int = Color.TRANSPARENT

    /**
     * 设置角的弧度数
     */
    private var headRadians: Int = 0

    /**
     * 设置左上角的弧度
     */
    private var headRadianLeftTop: Int = 0

    /**
     * 设置右上角的弧度
     */
    private var headRadianRightTop: Int = 0

    /**
     * 设置左下角的弧度
     */
    private var headRadianLeftBottom: Int = 0

    /**
     * 设置右下角的弧度
     */
    private var headRadianRightBottom: Int = 0

    /**
     * 是否开启清空文本的功能，默认关闭
     */
    private var headClear: Boolean = false

    /**
     * 设置右侧的清空内容按钮是否根据焦点来控制显示还是不显示，默认关闭这个开关
     */
    private var headClearIcon: Boolean = false


    /**
     * 控件格式：文本-2或编辑框-1，默认为编辑框 -1，
     */
    private var headType: Int = -1

    /**
     * 设置边框
     */
    @ColorInt
    private var headStrokeColor: Int = -1

    /**
     * 设置边框宽度
     */
    private var headStrokeWidth: Int = 0

    /**
     * 设置边框为虚线的长度
     */
    private var headStrokeDashWidth: Float = 0F

    /**
     * 设置虚线之间的像素间距
     */
    private var headStrokeDashGap: Float = 0F

    /**
     * 左边图标的点击事件
     */
    private var onLeftDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null

    /**
     * 右边图标的点击事件
     */
    private var onRightDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null

    /**
     * 上边图标的点击事件
     */
    private var onTopDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null

    /**
     * 下边图标的点击事件
     */
    private var onBottomDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null

    /**
     * 文本区域的点击事件
     */
    private var onTextareaClickListener: ((v: HeadEditTextView) -> Unit)? = null

    /**
     * 删除按钮
     */
    private var drawableClear: Drawable? = null

    /**
     * 初始的右侧的图标，为后面设置删除按钮的时候使用
     */
    private var drawableInitRight: Drawable? = null


    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {

        this.drawableInitRight = compoundDrawables[2]

        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.HeadEditTextView)
        
        headBackgroundColor = typedArray.getColor(
            R.styleable.HeadEditTextView_headBackgroundColor,
            Color.TRANSPARENT
        )
        headSupportGradient = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headSupportGradient,
            false
        )
        headGradientFrom = typedArray.getColor(
            R.styleable.HeadEditTextView_headGradientFrom,
            Color.TRANSPARENT
        )
        headGradientTo = typedArray.getColor(
            R.styleable.HeadEditTextView_headGradientTo,
            Color.TRANSPARENT
        )
        headRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headRadians,
            0
        )
        headRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headRadianLeftTop,
            0
        )
        headRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headRadianRightTop,
            0
        )
        headRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headRadianLeftBottom,
            0
        )
        headRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headRadianRightBottom,
            0
        )
        headType = typedArray.getInt(
            R.styleable.HeadEditTextView_headType,
            -1
        )
        headStrokeColor = typedArray.getColor(
            R.styleable.HeadEditTextView_headStrokeColor,
            -1
        )
        headStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadEditTextView_headStrokeWidth,
            0
        )
        headStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadEditTextView_headStrokeDashWidth,
            0F
        )
        headStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadEditTextView_headStrokeDashGap,
            0F
        )
        headClear = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headClear,
            false
        )
        headClearIcon = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headClearIcon,
            false
        )

        isOpenClear()
        background = getDrawable()
        getViewType()
        typedArray.recycle()

    }

    /**
     * 右边删除按钮
     */
    private fun isOpenClear() {
        if (headClear) {
            this.drawableClear = if (drawableInitRight == null) ContextCompat.getDrawable(
                context,
                R.drawable.head_edit_text_clear
            ) else drawableInitRight

            drawableClear!!.setBounds(
                0,
                0,
                drawableClear!!.intrinsicWidth,
                drawableClear!!.intrinsicHeight
            )
            this.onRightDrawableClickListener = if (this.onRightDrawableClickListener!=null) this.onRightDrawableClickListener else {{}}
            setRightClearIcon(hasFocus() && length() != 0)
            if (headClearIcon) {
                setRightClearIcon(true)
            }

        } else {
            this.drawableClear = drawableInitRight
            setRightClearIcon(true)
        }
    }


    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (headClear) {
            setRightClearIcon(hasFocus() && length() != 0)
        }
        if (headClearIcon) {
            setRightClearIcon(true && length() != 0)
        }

    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (headClear) {
            setRightClearIcon(hasFocus() && length() != 0)
        }
        if (headClearIcon) {
            setRightClearIcon(true)
        }

    }

    /**
     * 焦点不再或者文本为空时候不显示右侧删除按钮
     */
    private fun setRightClearIcon(isRight: Boolean) {
        setCompoundDrawables(
            compoundDrawables[0],
            compoundDrawables[1],
            if (isRight) drawableClear else null,
            compoundDrawables[3]
        )
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            //0左边; 1上边; 2右边; 3下边
            val drawableLeft = compoundDrawables[0]
            val drawableTop = compoundDrawables[1]
            val drawableRight = compoundDrawables[2]
            val drawableBottom = compoundDrawables[3]
            //以下位置都是相对位置，相对于控件边框的位置
            val eventX = event.x.toInt()
            val eventY = event.y.toInt()
            //控件的Y轴中心点
            val xCenterPoint = width / 2
            //控件的X轴中心点
            val yCenterPoint = height / 2
            //左右两个图标据Y轴中心点的偏移量
            val deviationY = ((drawableTop?.intrinsicHeight
                ?: 0) + (if (drawableTop == null) 0 else compoundDrawablePadding)
                    - (if (drawableBottom == null) 0 else compoundDrawablePadding) - (drawableBottom?.intrinsicHeight
                ?: 0)) / 2
            //上下两个图标据Y轴中心点的偏移量
            val deviationX = ((drawableLeft?.intrinsicWidth
                ?: 0) + (if (drawableLeft == null) 0 else compoundDrawablePadding)
                    - (if (drawableRight == null) 0 else compoundDrawablePadding) - (drawableRight?.intrinsicHeight
                ?: 0)) / 2


            if (onLeftDrawableClickListener != null && drawableLeft != null) {
                //左边图标x轴方向的区间
                val rangeLeftX = paddingLeft..paddingLeft + drawableLeft.intrinsicWidth
                //左边图标y轴方向的区间
                val rangeLeftY =
                    yCenterPoint + deviationY - drawableLeft.intrinsicHeight / 2..yCenterPoint + deviationY + drawableLeft.intrinsicHeight / 2
                if (eventX in rangeLeftX && eventY in rangeLeftY) {
                    onLeftDrawableClickListener?.invoke(this)
                    return true
                }

            }

            if (onRightDrawableClickListener != null && drawableRight != null) {
                //右边图标x轴方向的区间
                val rangeRightX =
                    (width - paddingRight - drawableRight.intrinsicWidth)..(width - paddingRight)
                //右边图标y轴方向的区间
                val rangeRightY =
                    yCenterPoint + deviationY - drawableRight.intrinsicHeight / 2..yCenterPoint + deviationY + drawableRight.intrinsicHeight / 2
                if (eventX in rangeRightX && eventY in rangeRightY) {
                    if (headClear) {
                        setText("")
                    }
                    onRightDrawableClickListener?.invoke(this)
                    return true

                }
            }
            if (onTopDrawableClickListener != null && drawableTop != null) {
                //上边图标x轴方向的区间
                val rangeTopX =
                    xCenterPoint + deviationX - drawableTop.intrinsicWidth / 2..xCenterPoint + deviationX + drawableTop.intrinsicWidth / 2
                //下边图标x轴方向的区间
                val rangeTopY = paddingTop..paddingTop + drawableTop.intrinsicHeight
                if (eventX in rangeTopX && eventY in rangeTopY) {
                    onTopDrawableClickListener?.invoke(this)
                    return true
                }
            }
            if (onBottomDrawableClickListener != null && drawableBottom != null) {
                //右边图标x轴方向的区间
                val rangeBottomX =
                    xCenterPoint + deviationX - drawableBottom.intrinsicHeight / 2..xCenterPoint + deviationX + drawableBottom.intrinsicHeight / 2
                //右边图标y轴方向的区间
                val rangeBottomY =
                    height - paddingBottom - drawableBottom.intrinsicHeight..height - paddingBottom
                if (eventX in rangeBottomX && eventY in rangeBottomY) {
                    onBottomDrawableClickListener?.invoke(this)
                    return true
                }
            }

            //文本区域x轴方向，当不是编辑器时才触发
            if (onTextareaClickListener != null && headType != -1) {
                val rangeCenterX = paddingLeft + (drawableLeft?.intrinsicWidth
                    ?: 0) + (if (drawableLeft == null) 0 else compoundDrawablePadding)..width - paddingRight - (drawableRight?.intrinsicWidth
                    ?: 0) - (if (drawableRight == null) 0 else compoundDrawablePadding)
                //文本区域y轴方向
                val rangeCenterY = paddingTop + (drawableTop?.intrinsicHeight
                    ?: 0) + (if (drawableTop == null) 0 else compoundDrawablePadding)..height - paddingBottom - (drawableBottom?.intrinsicHeight
                    ?: 0) - (if (drawableBottom == null) 0 else compoundDrawablePadding)


                if (eventX in rangeCenterX && eventY in rangeCenterY) {
                    onTextareaClickListener?.invoke(this)
                    return true
                }
            }


        }

        return super.onTouchEvent(event)

    }


    /**
     * 设置当前控件是文本框还是编辑框，默认为编辑框
     */
    private fun getViewType() {
        if (headType == -1) {
            isClickable = false
            isFocusableInTouchMode = true
            isEnabled = true
        } else {
            isClickable = true
            isFocusableInTouchMode = false
            isEnabled = false
        }
    }

    /**
     * 根据背景色，渐变色，圆角弧度创建一个符合条件的背景
     */
    private fun getDrawable(): TemplateDrawable = TemplateDrawable(
        context,
        headSupportGradient,
        headGradientFrom,
        headGradientTo,
        headBackgroundColor,
        headRadianLeftTop,
        headRadianLeftBottom,
        headRadianRightTop,
        headRadianRightBottom,
        headRadians,
        headStrokeWidth,
        headStrokeColor,
        headStrokeDashWidth,
        headStrokeDashGap
    ).apply {
        this@HeadEditTextView.invalidate()
    }


        /**
         * 左边图标的点击事件
         */
        fun setOnLeftDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
            this.onLeftDrawableClickListener = onPositionDrawableClickListener
        }


    /**
     * 右边图标的点击事件
     */
    fun setOnRightDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onRightDrawableClickListener = onPositionDrawableClickListener
    }

    /**
     * 上边图标的点击事件
     */
    fun setOnTopDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onTopDrawableClickListener = onPositionDrawableClickListener
    }

    /**
     * 下边图标的点击事件
     */
    fun setOnBottomDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onBottomDrawableClickListener = onPositionDrawableClickListener
    }

    /**
     * 文本区域的点击事件
     */
    fun setOnTextareaClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onTextareaClickListener = onPositionDrawableClickListener
    }

    /**
     * 设置背景色
     */
    fun setHeadBackgroundColor(@ColorInt color: Int) {
        headBackgroundColor = color
        background = getDrawable()
    }

    /**
     * 渐变支持
     */
    fun setHeadSupportGradient(support: Boolean) {
        headSupportGradient = support
        background = getDrawable()
    }

    /**
     * 渐变支持 起始色
     */
    fun setHeadGradientFrom(@ColorInt color: Int) {
        headGradientFrom = color
        background = getDrawable()
    }

    /**
     * 渐变支持 结束色
     */
    fun setHeadGradientTo(@ColorInt color: Int) {
        headGradientTo = color
        background = getDrawable()
    }

    /**
     * 设置圆弧角度
     */
    fun setHeadRadians(radian: Int) {
        headRadians = radian
        background = getDrawable()
    }

    /**
     * 设置圆弧LeftTop角度
     */
    fun setHeadRadianLeftTop(radian: Int) {
        headRadianLeftTop = radian
        background = getDrawable()
    }

    /**
     * 设置圆弧LeftBottom角度
     */
    fun setHeadRadianLeftBottom(radian: Int) {
        headRadianLeftBottom = radian
        background = getDrawable()
    }

    /**
     * 设置圆弧RightTop角度
     */
    fun setHeadRadianRightTop(radian: Int) {
        headRadianRightTop = radian
        background = getDrawable()
    }

    /**
     * 设置圆弧RightBottom角度
     */
    fun setHeadRadianRightBottom(radian: Int) {
        headRadianRightBottom = radian
        background = getDrawable()
    }

    /**
     * 控件类型
     */
    fun setHeadType(type: Int) {
        headType = type
        getViewType()
        invalidate()
    }

    /**
     * 边框颜色
     */
    fun setHeadStrokeColor(@ColorInt color: Int) {
        headStrokeColor = color
        background = getDrawable()
    }


    /**
     * 设置边框宽度
     */
    fun setHeadStrokeWidth(width: Int) {
        headStrokeWidth = width
        background = getDrawable()
    }

    /**
     * 设置边框为虚线的长度
     */
    fun setHeadStrokeDashWidth(width: Float) {
        headStrokeDashWidth = width
        background = getDrawable()
    }

    /**
     * 设置边框为虚线的长度
     */
    fun setHeadStrokeDashGap(gap: Float) {
        headStrokeDashGap = gap
        background = getDrawable()
    }

    /**
     * 右侧删除功能是否开启
     */
    fun setHeadClear(clear: Boolean) {
        headClear = clear
        isOpenClear()
    }

    /**
     * 右侧删除功能的图标是否常驻显示
     */
    fun setHeadClearIcon(clear: Boolean) {
        headClearIcon = clear
        isOpenClear()
    }

    /**
     * 获取当前文本是否为空
     */
    fun getTextEmpty(): Boolean {
        return text != null && text?.length!! > 0
    }
}
