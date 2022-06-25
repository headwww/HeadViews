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
import com.head.view.drawable.TemplateDrawable
import com.head.view.drawable.builderTemplateDrawable
import com.head.view.drawable.modifyTemplateDrawable


/**
 *
 * 类名称：HeadEditTextView.kt <br/>
 * 类描述：自定义的文本编辑框有如下功能
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

    @ColorInt
    private var headEditTextBackgroundColor: Int = Color.TRANSPARENT
    private var headEditTextSupportGradient: Boolean = false

    @ColorInt
    private var headEditTextGradientFrom: Int = Color.TRANSPARENT

    @ColorInt
    private var headEditTextGradientTo: Int = Color.TRANSPARENT
    private var headEditTextRadians: Float = 0F
    private var headEditTextRadianLeftTop: Float = 0F
    private var headEditTextRadianRightTop: Float = 0F
    private var headEditTextRadianLeftBottom: Float = 0F
    private var headEditTextRadianRightBottom: Float = 0F
    private var headEditTextClear: Boolean = false
    private var headEditTextClearIcon: Boolean = false
    private var headEditTextType: Int = EditTextType.EDITTEXT.ordinal

    @ColorInt
    private var headEditTextStrokeColor: Int = -1
    private var headEditTextStrokeWidth: Float = 0F
    private var headEditTextStrokeDashWidth: Float = 0F
    private var headEditTextStrokeDashGap: Float = 0F
    private var onLeftDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null
    private var onRightDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null
    private var onTopDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null
    private var onBottomDrawableClickListener: ((v: HeadEditTextView) -> Unit)? = null
    private var onTextareaClickListener: ((v: HeadEditTextView) -> Unit)? = null
    private var drawableClear: Drawable? = null
    private var drawableInitRight: Drawable? = null
    private lateinit var templateDrawable: TemplateDrawable

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
        templateDrawable = builderTemplateDrawable {
            supportGradient = headEditTextSupportGradient
            gradientFrom = headEditTextGradientFrom
            gradientTo = headEditTextGradientTo
            backgroundColor = headEditTextBackgroundColor
            radianLeftTop = headEditTextRadianLeftTop.toFloat()
            radianLeftBottom = headEditTextRadianLeftBottom.toFloat()
            radianRightTop = headEditTextRadianRightTop.toFloat()
            radianRightBottom = headEditTextRadianRightBottom.toFloat()
            radians = headEditTextRadians.toFloat()
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
            setRightClearIcon(true)
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

    fun setOnLeftDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onLeftDrawableClickListener = onPositionDrawableClickListener
    }

    fun setOnRightDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onRightDrawableClickListener = onPositionDrawableClickListener
    }

    fun setOnTopDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onTopDrawableClickListener = onPositionDrawableClickListener
    }

    fun setOnBottomDrawableClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onBottomDrawableClickListener = onPositionDrawableClickListener
    }

    fun setOnTextareaClickListener(onPositionDrawableClickListener: (v: HeadEditTextView) -> Unit) {
        this.onTextareaClickListener = onPositionDrawableClickListener
    }

    fun setHeadEditTextBackgroundColor(@ColorInt color: Int) {
        headEditTextBackgroundColor = color
        background = modifyTemplateDrawable(templateDrawable) {
            setBackgroundColor(headEditTextBackgroundColor)
            this
        }
    }

    fun setHeadEditTextSupportGradient(support: Boolean) {
        headEditTextSupportGradient = support
        background = modifyTemplateDrawable(templateDrawable) {
            setSupportGradient(headEditTextSupportGradient)
            this
        }
    }

    fun setHeadEditTextGradientFrom(@ColorInt color: Int) {
        headEditTextGradientFrom = color
        background = modifyTemplateDrawable(templateDrawable) {
            setGradientFrom(headEditTextGradientFrom)
            this
        }
    }

    fun setHeadEditTextGradientTo(@ColorInt color: Int) {
        headEditTextGradientTo = color
        background = modifyTemplateDrawable(templateDrawable) {
            setGradientTo(headEditTextGradientTo)
            this
        }
    }

    fun setHeadEditTextRadians(radian: Float) {
        headEditTextRadians = radian
        background = modifyTemplateDrawable(templateDrawable) {
            setRadians(headEditTextRadians.toFloat())
            this
        }
    }

    fun setHeadEditTextRadianLeftTop(radian: Float) {
        headEditTextRadianLeftTop = radian
        background = modifyTemplateDrawable(templateDrawable) {
            setRadianLeftTop(headEditTextRadianLeftTop.toFloat())
            this
        }
    }

    fun setHeadEditTextRadianLeftBottom(radian: Float) {
        headEditTextRadianLeftBottom = radian
        background = modifyTemplateDrawable(templateDrawable) {
            setRadianLeftBottom(headEditTextRadianLeftBottom.toFloat())
            this
        }
    }

    fun setHeadEditTextRadianRightTop(radian: Float) {
        headEditTextRadianRightTop = radian
        background = modifyTemplateDrawable(templateDrawable) {
            setRadianRightTop(headEditTextRadianRightTop.toFloat())
            this
        }
    }

    fun setHeadEditTextRadianRightBottom(radian: Float) {
        headEditTextRadianRightBottom = radian
        background = modifyTemplateDrawable(templateDrawable) {
            setRadianRightBottom(headEditTextRadianRightBottom.toFloat())
            this
        }
    }

    fun setHeadEditTextType(type: EditTextType) {
        headEditTextType = type.ordinal
        getViewType()
        invalidate()
    }

    fun setHeadEditTextStrokeColor(@ColorInt color: Int) {
        headEditTextStrokeColor = color
        background = modifyTemplateDrawable(templateDrawable) {
            setStrokeColor(headEditTextStrokeColor)
            this
        }
    }


    fun setHeadEditTextStrokeWidth(width: Float) {
        headEditTextStrokeWidth = width
        background = modifyTemplateDrawable(templateDrawable) {
            setStrokeWidth(headEditTextStrokeWidth)
            this
        }
    }

    fun setHeadEditTextStrokeDashWidth(width: Float) {
        headEditTextStrokeDashWidth = width
        background = modifyTemplateDrawable(templateDrawable) {
            setStrokeDashWidth(headEditTextStrokeDashWidth)
            this
        }
    }

    fun setHeadEditTextStrokeDashGap(gap: Float) {
        headEditTextStrokeDashGap = gap
        background = modifyTemplateDrawable(templateDrawable) {
            setStrokeDashGap(headEditTextStrokeDashGap)
            this
        }
    }

    fun setHeadEditTextClear(clear: Boolean) {
        headEditTextClear = clear
        isOpenClear()
    }

    fun setHeadEditTextClearIcon(clear: Boolean) {
        headEditTextClearIcon = clear
        isOpenClear()
    }

    fun getTextEmpty(): Boolean {
        return text != null && text?.length!! > 0
    }
}
