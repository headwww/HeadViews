package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.head.view.utils.TemplateDrawable
import com.head.view.utils.builderDrawable
import com.head.view.utils.modifyDrawable


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

    enum class EditTextType {
        EDITTEXT,
        TEXTVIEW
    }

    /**
     * 背景色
     */
    @ColorInt
    private var headEditTextBackgroundColor: Int = Color.TRANSPARENT

    /**
     * 是否支持渐变色
     */
    private var headEditTextSupportGradient: Boolean = false

    /**
     * 渐变色的起始色
     */
    @ColorInt
    private var headEditTextGradientFrom: Int = Color.TRANSPARENT

    /**
     * 渐变色的结束色
     */
    @ColorInt
    private var headEditTextGradientTo: Int = Color.TRANSPARENT

    /**
     * 设置角的弧度数
     */
    private var headEditTextRadians: Float = 0F

    /**
     * 设置左上角的弧度
     */
    private var headEditTextRadianLeftTop: Float = 0F

    /**
     * 设置右上角的弧度
     */
    private var headEditTextRadianRightTop: Float = 0F

    /**
     * 设置左下角的弧度
     */
    private var headEditTextRadianLeftBottom: Float = 0F

    /**
     * 设置右下角的弧度
     */
    private var headEditTextRadianRightBottom: Float = 0F

    /**
     * 是否开启清空文本的功能，默认关闭
     */
    private var headEditTextClear: Boolean = false

    /**
     * 设置右侧的清空内容按钮是否根据焦点来控制显示还是不显示，默认关闭这个开关
     */
    private var headEditTextClearIcon: Boolean = false


    /**
     * 控件格式：文本-2或编辑框-1，默认为编辑框 -1，
     */
    private var headEditTextType: Int = EditTextType.EDITTEXT.ordinal

    /**
     * 设置边框
     */
    @ColorInt
    private var headEditTextStrokeColor: Int = -1

    /**
     * 设置边框宽度
     */
    private var headEditTextStrokeWidth: Float = 0F

    /**
     * 设置边框为虚线的长度
     */
    private var headEditTextStrokeDashWidth: Float = 0F

    /**
     * 设置虚线之间的像素间距
     */
    private var headEditTextStrokeDashGap: Float = 0F

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

    private lateinit var templateDrawable:TemplateDrawable

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {

        this.drawableInitRight = compoundDrawables[2]

        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.HeadEditTextView)

        headEditTextBackgroundColor = typedArray.getColor(
            R.styleable.HeadEditTextView_headEditTextBackgroundColor,
            Color.TRANSPARENT
        )
        headEditTextSupportGradient = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headEditTextSupportGradient,
            false
        )
        headEditTextGradientFrom = typedArray.getColor(
            R.styleable.HeadEditTextView_headEditTextGradientFrom,
            Color.TRANSPARENT
        )
        headEditTextGradientTo = typedArray.getColor(
            R.styleable.HeadEditTextView_headEditTextGradientTo,
            Color.TRANSPARENT
        )
        headEditTextRadians = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextRadians,
            0F
        )
        headEditTextRadianLeftTop = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextRadianLeftTop,
            0F
        )
        headEditTextRadianRightTop = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextRadianRightTop,
            0F
        )
        headEditTextRadianLeftBottom = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextRadianLeftBottom,
            0F
        )
        headEditTextRadianRightBottom = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextRadianRightBottom,
            0F
        )
        headEditTextStrokeColor = typedArray.getColor(
            R.styleable.HeadEditTextView_headEditTextStrokeColor,
            -1
        )
        headEditTextStrokeWidth = typedArray.getDimension(
            R.styleable.HeadEditTextView_headEditTextStrokeWidth,
            0F
        )
        headEditTextStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadEditTextView_headEditTextStrokeDashWidth,
            0F
        )
        headEditTextStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadEditTextView_headEditTextStrokeDashGap,
            0F
        )
        headEditTextClear = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headEditTextClear,
            false
        )
        headEditTextClearIcon = typedArray.getBoolean(
            R.styleable.HeadEditTextView_headEditTextClearIcon,
            false
        )
        headEditTextType = typedArray.getInt(
            R.styleable.HeadEditTextView_headEditTextType,
            EditTextType.EDITTEXT.ordinal
        )
        isOpenClear()
        templateDrawable =  builderDrawable {
            supportGradient = headEditTextSupportGradient
            gradientFrom = headEditTextGradientFrom
            gradientTo = headEditTextGradientTo
            backgroundColor = headEditTextBackgroundColor
            radianLeftTop = headEditTextRadianLeftTop.toFloat()
            radianLeftBottom =headEditTextRadianLeftBottom.toFloat()
            radianRightTop =headEditTextRadianRightTop.toFloat()
            radianRightBottom =headEditTextRadianRightBottom.toFloat()
            radians =headEditTextRadians.toFloat()
            strokeWidth = headEditTextStrokeWidth
            strokeColor = headEditTextStrokeColor
            strokeDashWidth = headEditTextStrokeDashWidth
            strokeDashGap = headEditTextStrokeDashGap
            this
        }
        background = templateDrawable
        getViewType()
        typedArray.recycle()

    }

    /**
     * 右边删除按钮
     */
    private fun isOpenClear() {
        if (headEditTextClear) {
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
            this.onRightDrawableClickListener =
                if (this.onRightDrawableClickListener != null) this.onRightDrawableClickListener else {
                    {}
                }
            setRightClearIcon(hasFocus() && length() != 0)
            if (headEditTextClearIcon) {
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
        if (headEditTextClear) {
            setRightClearIcon(hasFocus() && length() != 0)
        }
        if (headEditTextClearIcon) {
            setRightClearIcon(length() != 0)
        }

    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (headEditTextClear) {
            setRightClearIcon(hasFocus() && length() != 0)
        }
        if (headEditTextClearIcon) {
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
                    if (headEditTextClear) {
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
            if (onTextareaClickListener != null && headEditTextType != 0) {
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
        if (headEditTextType == 0) {
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
    fun setHeadEditTextBackgroundColor(@ColorInt color: Int) {
        headEditTextBackgroundColor = color
        background = modifyDrawable(templateDrawable){
            setBackgroundColor(headEditTextBackgroundColor)
            this
        }
    }

    /**
     * 渐变支持
     */
    fun setHeadEditTextSupportGradient(support: Boolean) {
        headEditTextSupportGradient = support
        background = modifyDrawable(templateDrawable){
            setSupportGradient(headEditTextSupportGradient)
            this
        }
    }

    /**
     * 渐变支持 起始色
     */
    fun setHeadEditTextGradientFrom(@ColorInt color: Int) {
        headEditTextGradientFrom = color
        background = modifyDrawable(templateDrawable){
            setGradientFrom(headEditTextGradientFrom)
            this
        }
    }

    /**
     * 渐变支持 结束色
     */
    fun setHeadEditTextGradientTo(@ColorInt color: Int) {
        headEditTextGradientTo = color
        background = modifyDrawable(templateDrawable){
            setGradientTo(headEditTextGradientTo)
            this
        }
    }

    /**
     * 设置圆弧角度
     */
    fun setHeadEditTextRadians(radian: Float) {
        headEditTextRadians = radian
        background = modifyDrawable(templateDrawable){
            setRadians(headEditTextRadians.toFloat())
            this
        }
    }

    /**
     * 设置圆弧LeftTop角度
     */
    fun setHeadEditTextRadianLeftTop(radian: Float) {
        headEditTextRadianLeftTop = radian
        background = modifyDrawable(templateDrawable){
            setRadianLeftTop(headEditTextRadianLeftTop.toFloat())
            this
        }
    }

    /**
     * 设置圆弧LeftBottom角度
     */
    fun setHeadEditTextRadianLeftBottom(radian: Float) {
        headEditTextRadianLeftBottom = radian
        background = modifyDrawable(templateDrawable){
            setRadianLeftBottom(headEditTextRadianLeftBottom.toFloat())
            this
        }
    }

    /**
     * 设置圆弧RightTop角度
     */
    fun setHeadEditTextRadianRightTop(radian: Float) {
        headEditTextRadianRightTop = radian
        background = modifyDrawable(templateDrawable){
            setRadianRightTop(headEditTextRadianRightTop.toFloat())
            this
        }
    }

    /**
     * 设置圆弧RightBottom角度
     */
    fun setHeadEditTextRadianRightBottom(radian: Float) {
        headEditTextRadianRightBottom = radian
        background = modifyDrawable(templateDrawable){
            setRadianRightBottom(headEditTextRadianRightBottom.toFloat())
            this
        }
    }

    /**
     * 控件类型
     */
    fun setHeadEditTextType(type: EditTextType) {
        headEditTextType = type.ordinal
        getViewType()
        invalidate()
    }

    /**
     * 边框颜色
     */
    fun setHeadEditTextStrokeColor(@ColorInt color: Int) {
        headEditTextStrokeColor = color
        background = modifyDrawable(templateDrawable){
            setStrokeColor(headEditTextStrokeColor)
            this
        }
    }


    /**
     * 设置边框宽度
     */
    fun setHeadEditTextStrokeWidth(width: Float) {
        headEditTextStrokeWidth = width
        background = modifyDrawable(templateDrawable){
            setStrokeWidth(headEditTextStrokeWidth)
            this
        }
    }

    /**
     * 设置边框为虚线的长度
     */
    fun setHeadEditTextStrokeDashWidth(width: Float) {
        headEditTextStrokeDashWidth = width
        background = modifyDrawable(templateDrawable){
            setStrokeDashWidth(headEditTextStrokeDashWidth)
            this
        }
    }

    /**
     * 设置边框为虚线的长度
     */
    fun setHeadEditTextStrokeDashGap(gap: Float) {
        headEditTextStrokeDashGap = gap
        background = modifyDrawable(templateDrawable){
            setStrokeDashGap(headEditTextStrokeDashGap)
            this
        }
    }

    /**
     * 右侧删除功能是否开启
     */
    fun setHeadEditTextClear(clear: Boolean) {
        headEditTextClear = clear
        isOpenClear()
    }

    /**
     * 右侧删除功能的图标是否常驻显示
     */
    fun setHeadEditTextClearIcon(clear: Boolean) {
        headEditTextClearIcon = clear
        isOpenClear()
    }

    /**
     * 获取当前文本是否为空
     */
    fun getTextEmpty(): Boolean {
        return text != null && text?.length!! > 0
    }
}
