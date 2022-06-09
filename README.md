<div align=center>    
    <img src="https://github.com/headwww/HeadViews/blob/main/app/src/main/res/drawable/app_logo.png" width="150">    
    <center><h1>HeadViews</h1></center> 
</div>

# 预览
千言和万语不如一张图，请看下面的效果图
<div align=center>  
<img src="https://github.com/headwww/HeadViews/blob/main/screenshot/20220609_162323.gif?raw=true">    
</div>

# 介绍
HeadViews控件库包含以下控件： 
- HeadEditText
- HeadButton

# 使用
## HeadEditText

### Usage

用法非常简单。将控件添加到XML布局中：
```xml
<com.head.view.HeadEditTextView
    android:id="@+id/headEditTextView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="com.head.view.HeadEditTextView" />
```

### Listeners
当我们给控件设置`android:drawableLeft=""` `android:drawableTop=""` `android:drawableRight=""` `android:drawableBottom=""`后android原生的是不带点击事件的，HeadViews框架特意增加了四周图标的点击事件如下：
```kotlin
binding.headEditTextView.setOnLeftDrawableClickListener {
    Log.d(TAG, "onCreateView: Left" )
}
binding.headEditTextView.setOnRightDrawableClickListener {
    Log.d(TAG, "onCreateView: Right" )
}
binding.headEditTextView.setOnTopDrawableClickListener {
    Log.d(TAG, "onCreateView: Top" )
}
binding.headEditTextView.setOnBottomDrawableClickListener {
    Log.i(TAG, "onCreateView: Bottom" )
}
```

### Attributes
添加属性以自定义视图。可用属性：

| attr                  | info                                                         | format    |
| :-------------------- | :----------------------------------------------------------- | :-------- |
| headBackgroundColor   | 背景色                                                       | color     |
| headSupportGradient   | 是否支持渐变背景色                                           | boolean   |
| headGradientFrom      | 渐变起始色                                                   | color     |
| headGradientTo        | 渐变结束色                                                   | color     |
| headRadians           | 设置四个角的弧度                                             | dimension |
| headRadianLeftTop     | 设置左上角的弧度                                             | dimension |
| headRadianRightTop    | 设置右上角的弧度                                             | dimension |
| headRadianLeftBottom  | 设置左下角的弧度                                             | dimension |
| headRadianRightBottom | 设置右下角的弧度                                             | dimension |
| headStrokeColor       | 边框颜色                                                     | color     |
| headStrokeWidth       | 设置边框宽度                                                 | dimension |
| headStrokeDashWidth   | 设置虚线边框宽度                                             | float     |
| headStrokeDashGap     | 设置虚线边框虚线的间隙                                       | float     |
| headClear             | 开启右侧图标的清空内容功能 默认关闭这个功能                  | boolean   |
| headClearIcon         | 设置开启右侧图标的清空功能按钮是否根据焦点来控制显示/隐藏 默认false | boolean   |
| headType              | 控件类型(支持editText和textView，默认editText)               | enum      |

## HeadButton
该控件解决了按钮按下时候状态切换需要引入的大量的drawable，直接通过属性控制默认、按下、禁止三种状态的颜色、形状等样式

### Usage

首先将控件添加到XML布局中：
```xml
<com.head.view.HeadButton
        android:id="@+id/headButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="com.head.view.HeadButton" />
```




