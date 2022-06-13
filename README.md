<div align=center>    
    <img src="https://github.com/headwww/HeadViews/blob/main/app/src/main/res/drawable/app_logo.png" width="150">    
    <center><h1>HeadViews</h1></center> 
</div>

[![](https://jitpack.io/v/headwww/HeadViews.svg)](https://jitpack.io/#headwww/HeadViews)

# 预览
千言和万语不如一张图，请看下面的效果图
<div align=center>  
<img src="https://github.com/headwww/HeadViews/blob/main/screenshot/20220613_191516.gif?raw=true">    
</div>

# 介绍
HeadViews控件库包含以下控件： 
- HeadEditText
- HeadButton
- HeadSpinner

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
| headEditTextBackgroundColor   | 背景色                                                       | color     |
| headEditTextSupportGradient   | 是否支持渐变背景色                                           | boolean   |
| headEditTextGradientFrom      | 渐变起始色                                                   | color     |
| headEditTextGradientTo        | 渐变结束色                                                   | color     |
| headEditTextRadians           | 设置四个角的弧度                                             | dimension |
| headEditTextRadianLeftTop     | 设置左上角的弧度                                             | dimension |
| headEditTextRadianRightTop    | 设置右上角的弧度                                             | dimension |
| headEditTextRadianLeftBottom  | 设置左下角的弧度                                             | dimension |
| headEditTextRadianRightBottom | 设置右下角的弧度                                             | dimension |
| headEditTextStrokeColor       | 边框颜色                                                     | color     |
| headEditTextStrokeWidth       | 设置边框宽度                                                 | dimension |
| headEditTextStrokeDashWidth   | 设置虚线边框宽度                                             | float     |
| headEditTextStrokeDashGap     | 设置虚线边框虚线的间隙                                       | float     |
| headEditTextClear             | 开启右侧图标的清空内容功能 默认关闭这个功能                  | boolean   |
| headEditTextClearIcon         | 设置开启右侧图标的清空功能按钮是否根据焦点来控制显示/隐藏 默认false | boolean   |
| headEditTextType              | 控件类型(支持editText和textView，默认editText)               | enum      |

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
| headButton/*··········*/BackgroundColor   | 背景色                                               | color     |
| headButton/*··········*/SupportGradient   | 是否支持渐变                                         | boolean   |
| headButton/*··········*/GradientFrom      | 渐变起始色                                           | color     |
| headButton/*··········*/GradientTo        | 渐变结束色                                           | color     |
| headButton/*··········*/Radians           | 圆角弧度                                             | dimension |
| headButton/*··········*/RadianLeftTop     | 设置左上角的弧度                                     | dimension |
| headButton/*··········*/RadianRightTop    | 设置右上角的弧度                                     | dimension |
| headButton/*··········*/RadianLeftBottom  | 设置左下角的弧度                                     | dimension |
| headButton/*··········*/RadianRightBottom | 设置右下角的弧度                                     | dimension |
| headButton/*··········*/StrokeColor       | 边框颜色                                             | color     |
| headButton/*··········*/StrokeWidth       | 设置边框宽度                                         | dimension |
| headButton/*··········*/StrokeDashWidth   | 设置虚线边框宽度                                     | float     |
| headButton/*··········*/StrokeDashGap     | 设置虚线边框虚线的间隙                               | float     |
| headButtonShape                     | 设置按钮是矩形还是椭圆形 默认矩形（rectangle，oval） | enum      |

## HeadSpinner
利用ListPopupWindow特性进行封装的一个Spinner控件，丰富了传统Spinner的功能，下面我们看看怎么使用。

### Usage

首先将控件添加到XML布局中：
```xml
<com.head.view.HeadSpinner
        android:id="@+id/headSpinner"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

### Attributes
| attr                         | info                                     | format         |
| ---------------------------- | ---------------------------------------- | -------------- |
| headSpinnerWidth             | 设置弹窗的宽度，默认跟随锚点的宽度       | dimension/enum |
| headSpinnerHeight            | 设置弹窗的高度，默认显示全不得Item       | dimension/enum |
| headSpinnerDropDownGravity   | 设置Item的对齐方式（左对齐/右对齐/居中） | enum           |
| headSpinnerHorizontalOffset  | 设置弹窗相对锚点偏移值，正值暗示向右偏移 | dimension      |
| headSpinnerVerticalOffset    | 设置弹窗相对锚点偏移值，正值暗示向下偏移 | dimension      |
| headSpinnerSupportGradient   | 是否支持渐变背景色                       | boolean        |
| headSpinnerGradientFrom      | 渐变起始色                               | color          |
| headSpinnerGradientTo        | 渐变结束色                               | color          |
| headSpinnerRadians           | 设置四个角的弧度                         | dimension      |
| headSpinnerRadianLeftTop     | 设置左上角的弧度                         | dimension      |
| headSpinnerRadianRightTop    | 设置右上角的弧度                         | dimension      |
| headSpinnerRadianLeftBottom  | 设置左下角的弧度                         | dimension      |
| headSpinnerRadianRightBottom | 设置右下角的弧度                         | dimension      |
| headSpinnerStrokeColor       | 边框颜色                                 | color          |
| headSpinnerStrokeWidth       | 设置边框宽度                             | dimension      |
| headSpinnerStrokeDashWidth   | 设置虚线边框宽度                         | float          |
| headSpinnerStrokeDashGap     | 设置虚线边框虚线的间隙                   | float          |
| headSpinnerTextColor         | 设置Item的文本颜色                       | color          |
| headSpinnerTextSize          | 设置Item的文本大小                       | dimension      |
| headSpinnerArrowColor        | 设置角标的颜色                           | color          |
| headSpinnerArrowVisibility   | 设置角标是否显示                         | enum           |

1、HeSpinner支持通过编码的方式设置默认选择的Item：`headSpinner.setSelection(index)`
2、HeSpinner支持通过编码的方式设置Item的显示格式：
```kotlin
binding.headSpinner6.setData(List<Person>(4) { Person("Item$it",it) })
    binding.headSpinner6.setItemFormat {
        it as Person
        "姓名：${it.name},年龄${it.age}"
    }
```
3、HeSpinner支持通过编码的方式设置Item的图标，并且图标，图标的数量和Item数据无需对应，根据index依次显示，如果想有的不显示可以设置为null：
```kotlin
binding.headSpinner4.setData(List<String>(4) { "Item$it" })
binding.headSpinner4.setHeadSpinnerItemIcon(
    arrayListOf(
        R.drawable.ic_baseline_search_24,
        R.drawable.ic_baseline_mic_24,
        R.drawable.ic_baseline_cancel_24,
        null,
        R.drawable.ic_baseline_cancel_24,
        R.drawable.ic_baseline_cancel_24,
        R.drawable.ic_baseline_cancel_24,
     )
)
```

### Listeners
HeSpinner支持监听每个Item的点击事件：
```kotlin
binding.headSpinner5.setOnItemClickListener{
    item,position->
    Toast.makeText(requireActivity(), "$item 点击了:$position", Toast.LENGTH_SHORT).show()
}
```
HeSpinner支持监听Dismiss事件：

```kotlin
binding.headSpinner5.setOnDismissListener {
    Toast.makeText(requireActivity(), "OnDismiss", Toast.LENGTH_SHORT).show()
}

```

# How to

在你的`build.gradle`中添加如下代码：

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
dependencies {
    implementation 'com.github.headwww:HeadViews:0.0.01.beta01'
}

```
# 开源协议

HeadViews遵循 Apache License 2.0 开源协议。
```
                    Copyright headwww HeadViews

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```




