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

该控件解决了传统的EditText设置样式繁琐的步骤，直接通过属性来设置，同时可以随意切换文本和编辑框两个种模式，还解决了传统的四周图标无法点击的问题可以为其设置点击事件。

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

该控件解决了按钮按下时候状态切换需要引入的大量的drawable，直接通过属性控制默认、按下、禁止三种状态的颜色、形状等样式，同时支持该控件设置为圆形按钮。

### Usage

首先将控件添加到XML布局中：
```xml
<com.head.view.HeadButton
        android:id="@+id/headButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="com.head.view.HeadButton" />
```

### Attributes
注意：`/*··········*/`可用`Normal` `Pressed` `Enable`三个单词替换

| attr                                | info                                                 | format    |
| :---------------------------------- | :--------------------------------------------------- | :-------- |
| head/*··········*/BackgroundColor   | 背景色                                               | color     |
| head/*··········*/SupportGradient   | 是否支持渐变                                         | boolean   |
| head/*··········*/GradientFrom      | 渐变起始色                                           | color     |
| head/*··········*/GradientTo        | 渐变结束色                                           | color     |
| head/*··········*/Radians           | 圆角弧度                                             | dimension |
| head/*··········*/RadianLeftTop     | 设置左上角的弧度                                     | dimension |
| head/*··········*/RadianRightTop    | 设置右上角的弧度                                     | dimension |
| head/*··········*/RadianLeftBottom  | 设置左下角的弧度                                     | dimension |
| head/*··········*/RadianRightBottom | 设置右下角的弧度                                     | dimension |
| head/*··········*/StrokeColor       | 边框颜色                                             | color     |
| head/*··········*/StrokeWidth       | 设置边框宽度                                         | dimension |
| head/*··········*/StrokeDashWidth   | 设置虚线边框宽度                                     | float     |
| head/*··········*/StrokeDashGap     | 设置虚线边框虚线的间隙                               | float     |
| headButtonShape                     | 设置按钮是矩形还是椭圆形 默认矩形（rectangle，oval） | enum      |

# 引入

在你的`build.gradle`中添加如下代码：



