package com.head.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.head.view.adapter.HeadSpinnerAdapter
import com.head.view.utils.TemplateDrawable


/**
 *
 * 类名称：HeadSpinner.kt <br/>
 * 类描述：下拉选择框 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/10 10:54 <br/>
 * @version
 */
class HeadSpinner<T> : AppCompatTextView, AdapterView.OnItemClickListener {

    enum class Arrow {
        VISIBLE,
        GONE
    }

    enum class Gravity {
        LEFT,
        RIGHT,
        CENTER
    }

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


    @ColorInt
    private var headSpinnerStrokeColor: Int = -1

    @ColorInt
    private var headSpinnerTextColor: Int = Color.WHITE

    @ColorInt
    private var headSpinnerArrowColor: Int = Color.BLACK

    @ColorInt
    private var headSpinnerBackgroundColor: Int = Color.WHITE

    @ColorInt
    private var headSpinnerGradientTo: Int = Color.TRANSPARENT

    @ColorInt
    private var headSpinnerGradientFrom: Int = Color.TRANSPARENT

    private var headSpinnerWidth: Int = ListPopupWindow.WRAP_CONTENT
    private var headSpinnerHeight: Int = ListPopupWindow.WRAP_CONTENT

    private var headSpinnerDropDownGravity: Int = Gravity.CENTER.ordinal

    private var headSpinnerRadians: Int = 0
    private var headSpinnerStrokeWidth: Int = 0
    private var headSpinnerRadianLeftTop: Int = 0
    private var headSpinnerRadianRightTop: Int = 0
    private var headSpinnerVerticalOffset: Int = 0
    private var headSpinnerArrowVisibility: Int = 0
    private var headSpinnerHorizontalOffset: Int = 0
    private var headSpinnerRadianLeftBottom: Int = 0
    private var headSpinnerRadianRightBottom: Int = 0

    private var headSpinnerTextSize: Float = 0F
    private var headSpinnerStrokeDashGap: Float = 0F
    private var headSpinnerStrokeDashWidth: Float = 0F

    private var headSpinnerSupportGradient: Boolean = false

    private var position: Int = -1

    private var dataSource: List<T> = arrayListOf()

    private val adapter: HeadSpinnerAdapter<T> by lazy {
        HeadSpinnerAdapter(context, dataSource, null)
    }

    /**
     * Item显示的格式
     */
    private var format: ((t: T) -> String)? = null

    /**
     * Spinner弹出窗
     */
    private val popupWindow: ListPopupWindow by lazy { ListPopupWindow(context) }

    /**
     * Spinner点击事件
     */
    private var onItemClick: ((item: T, position: Int) -> Unit)? = null

    /**
     * Spinner点击事件
     */
    private var onDismiss: (() -> Unit)? = null


    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadSpinner)
        headSpinnerWidth = typedArray.getLayoutDimension(
            R.styleable.HeadSpinner_headSpinnerWidth,
            ListPopupWindow.WRAP_CONTENT
        )
        headSpinnerHeight = typedArray.getLayoutDimension(
            R.styleable.HeadSpinner_headSpinnerHeight,
            ListPopupWindow.WRAP_CONTENT
        )
        if (headSpinnerWidth == -1) {
            headSpinnerWidth = ListPopupWindow.MATCH_PARENT
        } else if (headSpinnerWidth == -2) {
            headSpinnerWidth = ListPopupWindow.WRAP_CONTENT
        }
        if (headSpinnerHeight == -1) {
            headSpinnerHeight = ListPopupWindow.MATCH_PARENT
        } else if (headSpinnerHeight == -2) {
            headSpinnerHeight = ListPopupWindow.WRAP_CONTENT
        }
        headSpinnerDropDownGravity = typedArray.getInt(
            R.styleable.HeadSpinner_headSpinnerDropDownGravity,
            Gravity.CENTER.ordinal
        )
        headSpinnerArrowVisibility = typedArray.getInt(
            R.styleable.HeadSpinner_headSpinnerArrowVisibility,
            Arrow.VISIBLE.ordinal
        )
        headSpinnerBackgroundColor = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerBackgroundColor,
            Color.WHITE
        )
        headSpinnerSupportGradient = typedArray.getBoolean(
            R.styleable.HeadSpinner_headSpinnerSupportGradient,
            false
        )
        headSpinnerGradientFrom = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerGradientFrom,
            Color.TRANSPARENT
        )
        headSpinnerGradientTo = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerGradientTo,
            Color.TRANSPARENT
        )
        headSpinnerRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerRadians,
            0
        )
        headSpinnerRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerRadianLeftTop,
            0
        )
        headSpinnerRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerRadianRightTop,
            0
        )
        headSpinnerRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerRadianLeftBottom,
            0
        )
        headSpinnerRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerRadianRightBottom,
            0
        )
        headSpinnerStrokeColor = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerStrokeColor,
            -1
        )
        headSpinnerStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerStrokeWidth,
            0
        )
        headSpinnerStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadSpinner_headSpinnerStrokeDashWidth,
            0F
        )
        headSpinnerStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadSpinner_headSpinnerStrokeDashGap,
            0F
        )
        headSpinnerTextColor = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerTextColor,
            ContextCompat.getColor(context, R.color.spinner_text_default_color)
        )

        headSpinnerTextSize = typedArray.getDimension(
            R.styleable.HeadSpinner_headSpinnerTextSize,
            context.resources.getDimension(R.dimen.spinner_item_default_text_size)
        )
        headSpinnerArrowColor = typedArray.getColor(
            R.styleable.HeadSpinner_headSpinnerArrowColor,
            Color.BLACK
        )
        headSpinnerHorizontalOffset = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerHorizontalOffset,
            0
        )
        headSpinnerVerticalOffset = typedArray.getDimensionPixelSize(
            R.styleable.HeadSpinner_headSpinnerVerticalOffset,
            0
        )

        initPopupWindow()
        typedArray.recycle()
    }

    private fun initPopupWindow() {
        popupWindow.setAdapter(adapter)
        adapter.setDropDownGravity(headSpinnerDropDownGravity)
        adapter.setTextColor(headSpinnerTextColor)
        adapter.setTextSize(headSpinnerTextSize)
        popupWindow.width = headSpinnerWidth
        popupWindow.height = headSpinnerHeight
        popupWindow.horizontalOffset= headSpinnerHorizontalOffset
        popupWindow.verticalOffset = headSpinnerVerticalOffset
        popupWindow.isModal = true
        popupWindow.setBackgroundDrawable(getDrawable())
        popupWindow.setOnItemClickListener(this)
        popupWindow.setOnDismissListener {
            onDismiss?.let {
                it.invoke()
            }
            if (headSpinnerArrowVisibility == 0) setArrowAnimation(
                R.drawable.head_anim_arrow_from_up_to_down,
                headSpinnerArrowColor
            ).start()
        }
        if (headSpinnerArrowVisibility == 0) setArrowAnimation(R.drawable.head_anim_arrow_from_down_to_up,headSpinnerArrowColor)

    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        val parcelable = super.onSaveInstanceState()
        bundle.putParcelable(HEAD_SPINNER, parcelable)
        bundle.putSerializable(SAVE_SPINNER_TEXT, text.toString())
        bundle.putSerializable(SAVE_SPINNER_SELECTION, position)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val bundle = (state as Bundle)
        val parcelable: Parcelable? = bundle.getParcelable(HEAD_SPINNER)
        text = bundle.getSerializable(SAVE_SPINNER_TEXT).toString()
        position = bundle.getSerializable(SAVE_SPINNER_SELECTION) as Int
        super.onRestoreInstanceState(parcelable)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.position = position
        onItemClick?.let {
            it.invoke(dataSource[position], position)
        }
        text = (view as TextView).text
        popupWindow.dismiss()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isEnabled && event!!.action == MotionEvent.ACTION_UP && dataSource.size > 0) {
            if (!popupWindow.isShowing) showDropDown() else dismissDropDown()
        }
        return true
    }

    private fun showDropDown() {
        popupWindow.anchorView = this
        popupWindow.show()
        if (headSpinnerArrowVisibility == 0) setArrowAnimation(
            R.drawable.head_anim_arrow_from_down_to_up,
            headSpinnerArrowColor
        ).start()
    }

    private fun dismissDropDown() {
        popupWindow.dismiss()
    }

    fun setData(array: List<T>) {
        dataSource = array
        adapter.setData(array)
        setSelection(position)
    }

    fun setData(array: ArrayList<T>, format: (t: T) -> String) {
        this.format = format
        this.dataSource = array
        adapter.setData(array)
        adapter.setItemFormat(format)
        setSelection(position)
    }

    fun setItemFormat(format: (t: T) -> String) {
        this.format = format
        adapter.setItemFormat(format)
    }

    fun setSelection(position: Int) {
        this.position = position
        if (0 < position && position < dataSource.size) {
            text =
                if (format == null) dataSource[position].toString() else dataSource[position]?.let {
                    format!!.invoke(it)
                }
        }
    }

    fun getSelection(): Int {
        return position
    }

    /**
     * 右侧图标动画
     */
    private fun setArrowAnimation(
        @DrawableRes id: Int,
        @ColorInt color: Int = Color.BLACK
    ): AnimationDrawable {
        var drawable = ContextCompat.getDrawable(context, id)
        drawable = DrawableCompat.wrap(drawable!!)
        DrawableCompat.setTint(drawable, color)
        drawable?.let {
            it.setBounds(
                0,
                0,
                drawable.intrinsicWidth,
                drawable.intrinsicHeight
            )
        }
        setCompoundDrawables(null, null, drawable, null)
        return compoundDrawables[2] as AnimationDrawable
    }

    fun setOnItemClickListener(onItemClick: ((item: T, position: Int) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    fun setOnDismissListener(onDismiss: (() -> Unit)?) {
        this.onDismiss = onDismiss
    }

    fun setHeadSpinnerWidth(width: Int) {
        this.headSpinnerWidth = width
        popupWindow.width = headSpinnerWidth
        invalidate()
    }

    fun setHeadSpinnerHeight(height: Int) {
        this.headSpinnerHeight = height
        popupWindow.height = headSpinnerHeight
        invalidate()
    }

    fun setHeadSpinnerHorizontalOffset(horizontalOffset: Int) {
        this.headSpinnerHorizontalOffset = horizontalOffset
        popupWindow.horizontalOffset = headSpinnerHorizontalOffset
        invalidate()
    }
    fun setHeadSpinnerVerticalOffset(verticalOffset: Int) {
        this.headSpinnerVerticalOffset = verticalOffset
        popupWindow.verticalOffset = headSpinnerVerticalOffset
        invalidate()
    }

    fun setHeadSpinnerTextColor(@ColorInt color: Int) {
        this.headSpinnerTextColor = color
        adapter.setTextColor(headSpinnerTextColor)
    }

    fun setHeadSpinnerTextSize(size: Float) {
        this.headSpinnerTextSize = size
        adapter.setTextSize(headSpinnerTextSize)
    }

    fun setHeadSpinnerDropDownGravity(gravity: Gravity) {
        this.headSpinnerDropDownGravity = gravity.ordinal
        adapter.setDropDownGravity(headSpinnerDropDownGravity)
    }

    fun setHeadSpinnerBackgroundColor(@ColorInt color: Int) {
        headSpinnerBackgroundColor = color
        background = getDrawable()
    }

    fun setHeadSpinnerSupportGradient(support: Boolean) {
        headSpinnerSupportGradient = support
        background = getDrawable()
    }

    fun setHeadSpinnerGradientFrom(@ColorInt color: Int) {
        headSpinnerGradientFrom = color
        background = getDrawable()
    }

    fun setHeadSpinnerGradientTo(@ColorInt color: Int) {
        headSpinnerGradientTo = color
        background = getDrawable()
    }

    fun setHeadSpinnerRadians(radian: Int) {
        headSpinnerRadians = radian
        background = getDrawable()
    }

    fun setHeadSpinnerRadianLeftTop(radian: Int) {
        headSpinnerRadianLeftTop = radian
        background = getDrawable()
    }

    fun setHeadSpinnerRadianLeftBottom(radian: Int) {
        headSpinnerRadianLeftBottom = radian
        background = getDrawable()
    }

    fun setHeadSpinnerRadianRightTop(radian: Int) {
        headSpinnerRadianRightTop = radian
        background = getDrawable()
    }

    fun setHeadSpinnerRadianRightBottom(radian: Int) {
        headSpinnerRadianRightBottom = radian
        background = getDrawable()
    }

    fun setHeadSpinnerStrokeColor(@ColorInt color: Int) {
        headSpinnerStrokeColor = color
        background = getDrawable()
    }

    fun setHeadSpinnerStrokeWidth(width: Int) {
        headSpinnerStrokeWidth = width
        background = getDrawable()
    }

    fun setHeadSpinnerStrokeDashWidth(width: Float) {
        headSpinnerStrokeDashWidth = width
        background = getDrawable()
    }

    fun setHeadSpinnerStrokeDashGap(gap: Float) {
        headSpinnerStrokeDashGap = gap
        background = getDrawable()
    }

    fun setHeadSpinnerArrowVisibility(arrow: Arrow) {
        headSpinnerArrowVisibility = if (arrow == Arrow.VISIBLE) {
            Arrow.VISIBLE.ordinal
        } else {
            Arrow.GONE.ordinal
        }
        if (headSpinnerArrowVisibility == 0) setArrowAnimation(
            R.drawable.head_anim_arrow_from_down_to_up,
            headSpinnerArrowColor
        ) else setCompoundDrawables(
            null,
            null,
            null,
            null
        )
    }

    fun setHeadSpinnerArrowColor(@ColorInt color: Int) {
        headSpinnerArrowColor = color
        if (headSpinnerArrowVisibility == 0) setArrowAnimation(
            R.drawable.head_anim_arrow_from_down_to_up,
            headSpinnerArrowColor
        ).start()
    }

    fun setHeadSpinnerItemIcon(drawables: ArrayList<Int>) {
        val list = arrayListOf<Drawable?>()
        for (drawable in drawables) {
            var d = try {
                ContextCompat.getDrawable(context, drawable)
            } catch (e: Exception) {
                null
            }
            list.add(d)
        }
        adapter.seItemIcon(list)
    }

    private fun getDrawable(): TemplateDrawable = TemplateDrawable(
        context,
        headSpinnerSupportGradient,
        headSpinnerGradientFrom,
        headSpinnerGradientTo,
        headSpinnerBackgroundColor,
        headSpinnerRadianLeftTop,
        headSpinnerRadianLeftBottom,
        headSpinnerRadianRightTop,
        headSpinnerRadianRightBottom,
        headSpinnerRadians,
        headSpinnerStrokeWidth,
        headSpinnerStrokeColor,
        headSpinnerStrokeDashWidth,
        headSpinnerStrokeDashGap
    ).apply {
        this@HeadSpinner.invalidate()
    }

}

private const val HEAD_SPINNER = "head_spinner"

private const val SAVE_SPINNER_TEXT = "save_head_spinner_text"

private const val SAVE_SPINNER_SELECTION = "save_head_spinner_selection"