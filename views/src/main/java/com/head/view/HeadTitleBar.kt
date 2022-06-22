package com.head.view

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.google.android.material.shape.MaterialShapeDrawable
import com.head.view.style.*
import com.head.view.utils.StatusBarUtil
import com.head.view.utils.TemplateDrawable
import com.head.view.utils.builderDrawable
import com.head.view.utils.modifyDrawable


/**
 *
 * 类名称：HeadTitleBar.kt <br/>
 * 类描述：自定义标题栏 <br/>
 * 创建人：shuwen <br/>
 * 创建时间：2022/6/14 10:47 <br/>
 * @version
 */
class HeadTitleBar : FrameLayout, View.OnLayoutChangeListener {

    enum class HeadTitleStyle {
        GENERAL,
        SEARCH,
        CUSTOM
    }

    enum class Theme {
        LIGHT, DARK

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

    private var headTitleSearchTextSize: Int = 0
    private var headTitleSearchTextColor: Int = Color.WHITE
    private var headTitleSearchLeftIcon: Int = -1
    private var headTitleSearchHint: String = ""
    private var headTitleSearchHintColor: Int = Color.WHITE
    private var headTitleSearchSoftInputKeyBoard: Boolean = false
    private var headTitleBarTheme: Int = -1
    private var headTitleBarFillColorAlpha: Int = 1
    private var headTitleBarFillColor: Int = -1
    private var headTitleBarFill: Boolean = false
    private var headTitleBarTransparent: Boolean = false
    private var headTitleGeneralCenterMainTextSize: Int = 0
    private var headTitleGeneralCenterMainTextColor: Int = Color.WHITE
    private var headTitleGeneralCenterMainMarquee: Boolean = false
    private var headTitleGeneralCenterMainText: String = ""


    private var headTitleGeneralCenterSubTextSize: Int = 0
    private var headTitleGeneralCenterSubTextColor: Int = Color.WHITE
    private var headTitleGeneralCenterSubMarquee: Boolean = false
    private var headTitleGeneralCenterSubText: String = ""

    private var headTitleGeneralLeftTextSize: Int = 0
    private var headTitleGeneralLeftTextColor: Int = Color.WHITE
    private var headTitleGeneralLeftIcon: Int = 0
    private var headTitleGeneralLeftText: String = ""

    private var headTitleGeneralRightTextSize: Int = 0
    private var headTitleGeneralRightTextColor: Int = Color.WHITE
    private var headTitleGeneralRightIcon: Int = 0
    private var headTitleGeneralRightText: String = ""


    private lateinit var builtInTitle: BuiltInStyle
    private var headTitleBarGradientFrom: Int = Color.WHITE
    private var headTitleBarGradientTo: Int = Color.WHITE
    private var headTitleBarSupportGradient: Boolean = false
    private var headTitleBarBackgroundColor: Int = Color.WHITE
    private var headTitleBarCustomViewRes: Int = 0
    private var headTitleStyle: Int = HeadTitleStyle.GENERAL.ordinal

    @ColorInt
    private var headTitleSearchBackgroundColor: Int = Color.TRANSPARENT
    private var headTitleSearchSupportGradient: Boolean = false
    @ColorInt
    private var headTitleSearchGradientFrom: Int = Color.TRANSPARENT
    @ColorInt
    private var headTitleSearchGradientTo: Int = Color.TRANSPARENT
    private var headTitleSearchRadians: Int = 0
    private var headTitleSearchRadianLeftTop: Int = 0
    private var headTitleSearchRadianRightTop: Int = 0
    private var headTitleSearchRadianLeftBottom: Int = 0
    private var headTitleSearchRadianRightBottom: Int = 0
    @ColorInt
    private var headTitleSearchStrokeColor: Int = -1
    private var headTitleSearchStrokeWidth: Int = 0
    private var headTitleSearchStrokeDashWidth: Float = 0F
    private var headTitleSearchStrokeDashGap: Float = 0F

    private var customView: View? = null

    private lateinit var templateDrawable:TemplateDrawable

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int? = null) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeadTitleBar)
        headTitleBarCustomViewRes =
            typedArray.getResourceId(R.styleable.HeadTitleBar_headTitleBarCustomView, 0)

        headTitleBarBackgroundColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarBackgroundColor,
            Color.WHITE
        )
        headTitleBarSupportGradient = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleBarSupportGradient,
            false
        )
        headTitleBarGradientFrom = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarGradientFrom,
            Color.WHITE
        )
        headTitleBarGradientTo = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarGradientTo,
            Color.WHITE
        )
        headTitleStyle = typedArray.getInt(
            R.styleable.HeadTitleBar_headTitleStyle,
            HeadTitleStyle.GENERAL.ordinal
        )
        headTitleBarTransparent = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleBarTransparent,
            false
        )
        headTitleBarFill = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleBarFill,
            false
        )

        headTitleBarFillColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleBarFillColor,
            -1
        )
        headTitleBarFillColorAlpha = typedArray.getInt(
            R.styleable.HeadTitleBar_headTitleBarFillColorAlpha,
            1
        )

        headTitleBarTheme = typedArray.getInt(R.styleable.HeadTitleBar_headTitleBarTheme, -1)

        headTitleGeneralLeftText = typedArray.getString(
            R.styleable.HeadTitleBar_headTitleGeneralLeftText,
        ) ?: ""
        headTitleGeneralLeftIcon = typedArray.getResourceId(
            R.styleable.HeadTitleBar_headTitleGeneralLeftIcon,
            0
        )
        headTitleGeneralLeftTextColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleGeneralLeftTextColor,
            Color.WHITE
        )
        headTitleGeneralLeftTextSize = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleGeneralLeftTextSize,
            context.resources.getDimension(R.dimen.head_left_textview_size).toInt()
        )


        headTitleGeneralRightText = typedArray.getString(
            R.styleable.HeadTitleBar_headTitleGeneralRightText
        ) ?: ""
        headTitleGeneralRightIcon = typedArray.getResourceId(
            R.styleable.HeadTitleBar_headTitleGeneralRightIcon,
            0
        )
        headTitleGeneralRightTextColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleGeneralRightTextColor,
            Color.WHITE
        )
        headTitleGeneralRightTextSize = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleGeneralRightTextSize,
            context.resources.getDimension(R.dimen.head_right_textview_size).toInt()
        )

        headTitleGeneralCenterMainText = typedArray.getString(
            R.styleable.HeadTitleBar_headTitleGeneralCenterMainText
        ) ?: ""
        headTitleGeneralCenterMainMarquee = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleGeneralCenterMainMarquee,
            false
        )
        headTitleGeneralCenterMainTextColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleGeneralCenterMainTextColor,
            Color.WHITE
        )
        headTitleGeneralCenterMainTextSize = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleGeneralCenterMainTextSize,
            context.resources.getDimension(R.dimen.head_right_textview_size).toInt()
        )

        headTitleGeneralCenterSubText = typedArray.getString(
            R.styleable.HeadTitleBar_headTitleGeneralCenterSubText,
        ) ?: ""
        headTitleGeneralCenterSubMarquee = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleGeneralCenterSubMarquee,
            false
        )
        headTitleGeneralCenterSubTextColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleGeneralCenterSubTextColor,
            Color.WHITE
        )
        headTitleGeneralCenterSubTextSize = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleGeneralCenterSubTextSize,
            context.resources.getDimension(R.dimen.head_right_textview_size).toInt()
        )
        headTitleSearchSoftInputKeyBoard = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleSearchSoftInputKeyBoard,
            false
        )
        headTitleSearchHintColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchHintColor,
            Color.WHITE
        )
        headTitleSearchHint = typedArray.getString(
            R.styleable.HeadTitleBar_headTitleSearchHint,
        ) ?: ""
        headTitleSearchLeftIcon = typedArray.getResourceId(
            R.styleable.HeadTitleBar_headTitleSearchLeftIcon,
            -1
        )
        headTitleSearchTextColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchTextColor,
            Color.WHITE
        )
        headTitleSearchTextSize = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchTextSize,
            context.resources.getDimension(R.dimen.head_right_textview_size).toInt()
        )

        headTitleSearchBackgroundColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchBackgroundColor,
            Color.TRANSPARENT
        )
        headTitleSearchSupportGradient = typedArray.getBoolean(
            R.styleable.HeadTitleBar_headTitleSearchSupportGradient,
            false
        )
        headTitleSearchGradientFrom = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchGradientFrom,
            Color.TRANSPARENT
        )
        headTitleSearchGradientTo = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchGradientTo,
            Color.TRANSPARENT
        )
        headTitleSearchRadians = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchRadians,
            0
        )
        headTitleSearchRadianLeftTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchRadianLeftTop,
            0
        )
        headTitleSearchRadianRightTop = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchRadianRightTop,
            0
        )
        headTitleSearchRadianLeftBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchRadianLeftBottom,
            0
        )
        headTitleSearchRadianRightBottom = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchRadianRightBottom,
            0
        )
        headTitleSearchStrokeColor = typedArray.getColor(
            R.styleable.HeadTitleBar_headTitleSearchStrokeColor,
            -1
        )
        headTitleSearchStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.HeadTitleBar_headTitleSearchStrokeWidth,
            0
        )
        headTitleSearchStrokeDashWidth = typedArray.getFloat(
            R.styleable.HeadTitleBar_headTitleSearchStrokeDashWidth,
            0F
        )
        headTitleSearchStrokeDashGap = typedArray.getFloat(
            R.styleable.HeadTitleBar_headTitleSearchStrokeDashGap,
            0F
        )
        //通用的标题模版
        if (headTitleStyle == HeadTitleStyle.GENERAL.ordinal || headTitleStyle == HeadTitleStyle.SEARCH.ordinal) {
            builtInTitle = builderTitle(context) {
                style = headTitleStyle
                leftText = headTitleGeneralLeftText
                leftIcon = headTitleGeneralLeftIcon
                leftTextColor = headTitleGeneralLeftTextColor
                leftTextSize = headTitleGeneralLeftTextSize.toFloat()

                rightText = headTitleGeneralRightText
                rightIcon = headTitleGeneralRightIcon
                rightTextColor = headTitleGeneralRightTextColor
                rightTextSize = headTitleGeneralRightTextSize.toFloat()

                centerMainTitleText = headTitleGeneralCenterMainText
                centerMainTitleTextColor = headTitleGeneralCenterMainTextColor
                centerMainTitleTextSize = headTitleGeneralCenterMainTextSize.toFloat()
                isCenterMainTitleMarquee = headTitleGeneralCenterMainMarquee

                centerSubTitleText = headTitleGeneralCenterSubText
                centerSubTitleTextColor = headTitleGeneralCenterSubTextColor
                centerSubTitleTextSize = headTitleGeneralCenterSubTextSize.toFloat()
                isCenterSubTitleMarquee = headTitleGeneralCenterSubMarquee

                isSoftInputKeyBoard = headTitleSearchSoftInputKeyBoard

                centerSearchHint = headTitleSearchHint
                centerSearchHintColor = headTitleSearchHintColor
                centerSearchLeftIcon = headTitleSearchLeftIcon

                centerSearchTextSize =headTitleSearchTextSize.toFloat()
                centerSearchTextColor = headTitleSearchTextColor
                this.templateSearchDrawable = builderDrawable {
                    supportGradient = headTitleSearchSupportGradient
                    gradientFrom = headTitleSearchGradientFrom
                    gradientTo = headTitleSearchGradientTo
                    backgroundColor = headTitleSearchBackgroundColor
                    radianLeftTop = headTitleSearchRadianLeftTop.toFloat()
                    radianLeftBottom =headTitleSearchRadianLeftBottom.toFloat()
                    radianRightTop =headTitleSearchRadianRightTop.toFloat()
                    radianRightBottom =headTitleSearchRadianRightBottom.toFloat()
                    radians =headTitleSearchRadians.toFloat()
                    strokeWidth = headTitleSearchStrokeWidth
                    strokeColor = headTitleSearchStrokeColor
                    strokeDashWidth = headTitleSearchStrokeDashWidth
                    strokeDashGap = headTitleSearchStrokeDashGap
                    this
                }
                this@HeadTitleBar.addView(leftTextView)
                this@HeadTitleBar.addView(rightTextView)
                this@HeadTitleBar.addView(centerLinearLayout)
                this
            }
            addOnLayoutChangeListener(this)
        }


        //自定义模版
        if (headTitleStyle == HeadTitleStyle.CUSTOM.ordinal && headTitleBarCustomViewRes != 0) {
            customView =
                LayoutInflater.from(context).inflate(headTitleBarCustomViewRes, null, false)
            removeAllViews()
            addView(customView, 0)
        }

        //是否支持透明状态栏
        if (headTitleBarFill) {
            setPadding(
                paddingLeft,
                paddingTop + StatusBarUtil.getStatusBarHeight(context),
                paddingLeft,
                paddingBottom
            )
        }

        templateDrawable=builderDrawable {
            supportGradient = headTitleBarSupportGradient
            gradientFrom = headTitleBarGradientFrom
            gradientTo = headTitleBarGradientTo
            backgroundColor = headTitleBarBackgroundColor
            this
        }
        background =  templateDrawable
        typedArray.recycle()

    }



    override fun onLayoutChange(
        v: View?,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        oldLeft: Int,
        oldTop: Int,
        oldRight: Int,
        oldBottom: Int
    ) {
        // 先移除当前的监听，因为子View在setMaxWidth时候会重新触发监听，解决递归
        removeOnLayoutChangeListener(this)
        if (builtInTitle != null) {
            modifyTitle(builtInTitle) {
                layoutChange = LayoutChange(left, right, paddingLeft, paddingRight)
                this
            }
        }
//        post {
//            // 这里再次监听需要延迟，否则会导致递归的情况发生
//            addOnLayoutChangeListener(this@HeadTitleBar)
//        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (headTitleBarTransparent && StatusBarUtil.supportTransparentStatusBar() && headTitleBarFillColor == -1) {
            StatusBarUtil.transparentStatusBar(context as Activity)
        } else if (headTitleBarFillColor != -1) {
            StatusBarUtil.setStatusBarColor(
                context as Activity,
                headTitleBarFillColor,
                headTitleBarFillColorAlpha
            )
        }
        //开启这个后标题栏会被遮挡，建议把状态栏填充也开着
        if (headTitleBarTheme == Theme.LIGHT.ordinal) {
            StatusBarUtil.setLightMode(context as Activity)
        } else if (headTitleBarTheme == Theme.DARK.ordinal) {
            StatusBarUtil.setDarkMode(context as Activity)
        }
    }

    fun getCustomView(): View? {
        return customView
    }

    fun addCustomView(headTitleBarCustomViewRes: Int, onBind: (view: View?) -> Unit) {
        this.headTitleBarCustomViewRes = headTitleBarCustomViewRes
        customView = LayoutInflater.from(context).inflate(headTitleBarCustomViewRes, null, false)
        removeAllViews()
        addView(customView)
        onBind(customView)
    }

    fun onBindViewClick(onBind: (view: View?) -> Unit) {
        if (customView != null)
            onBind(customView)
    }

    fun setHeadTitleBarBackgroundColor(@ColorInt headTitleBarBackgroundColor: Int) {
        this.headTitleBarBackgroundColor = headTitleBarBackgroundColor
        background = modifyDrawable(templateDrawable){
            setBackgroundColor(this@HeadTitleBar.headTitleBarBackgroundColor)
            this
        }
    }

    fun setHeadTitleBarSupportGradient(headTitleBarSupportGradient: Boolean) {
        this.headTitleBarSupportGradient = headTitleBarSupportGradient
        background = modifyDrawable(templateDrawable){
            setSupportGradient(this@HeadTitleBar.headTitleBarSupportGradient)
            this
        }
    }

    fun setHeadTitleBarGradientFrom(@ColorInt headTitleBarGradientFrom: Int) {
        this.headTitleBarGradientFrom = headTitleBarGradientFrom
        background = modifyDrawable(templateDrawable){
            setGradientFrom(this@HeadTitleBar.headTitleBarGradientFrom)
            this
        }
    }

    fun setHeadTitleBarGradientTo(@ColorInt headTitleBarGradientTo: Int) {
        this.headTitleBarGradientTo = headTitleBarGradientTo
        background = modifyDrawable(templateDrawable){
            setGradientTo(this@HeadTitleBar.headTitleBarGradientTo)
            this
        }
    }

    fun setHeadTitleStyle(style: HeadTitleStyle) {
        this.headTitleStyle = style.ordinal
    }

    fun getBuiltInTitle(): BuiltInImpl {
        if (this::builtInTitle.isInitialized){
            return builtInTitle
        }else{
           throw UninitializedPropertyAccessException("内置风格类型的标题未初始化！")
        }
    }

    fun setHeadTitleBarTransparent() {
        this.headTitleBarTransparent = true
        if (headTitleBarTransparent && StatusBarUtil.supportTransparentStatusBar() && headTitleBarFillColor == -1) {
            StatusBarUtil.transparentStatusBar(context as Activity)
        }
    }

    fun setHeadTitleBarFill(headTitleBarFill: Boolean) {
        this.headTitleBarFill = headTitleBarFill
        if (headTitleBarFill) {
            setPadding(
                paddingLeft,
                paddingTop + StatusBarUtil.getStatusBarHeight(context),
                paddingLeft,
                paddingBottom
            )
        } else {
            setPadding(
                paddingLeft,
                paddingTop - StatusBarUtil.getStatusBarHeight(context),
                paddingLeft,
                paddingBottom
            )

        }
    }

    fun setHeadTitleBarFillColor(@ColorInt headTitleBarFillColor: Int) {
        this.headTitleBarFillColor = headTitleBarFillColor
        StatusBarUtil.setStatusBarColor(
            context as Activity,
            headTitleBarFillColor,
            this.headTitleBarFillColorAlpha
        )

    }

    fun setHeadTitleBarFillColorAlpha(headTitleBarFillColorAlpha: Int) {
        this.headTitleBarFillColorAlpha = headTitleBarFillColorAlpha
        StatusBarUtil.setStatusBarColor(
            context as Activity,
            headTitleBarFillColor,
            this.headTitleBarFillColorAlpha
        )

    }

    fun setHeadTitleBarTheme(theme: Theme) {
        this.headTitleBarTheme = theme.ordinal
        //开启这个后标题栏会被遮挡，建议把状态栏填充也开着
        if (headTitleBarTheme == Theme.LIGHT.ordinal) {
            StatusBarUtil.setLightMode(context as Activity)
        } else if (headTitleBarTheme == Theme.DARK.ordinal) {
            StatusBarUtil.setDarkMode(context as Activity)
        }
    }
}