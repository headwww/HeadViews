package com.head.view.utils

interface TemplateImpl {
    fun setSupportGradient( supportGradient: Boolean):TemplateImpl;
    fun setGradientFrom(gradientFrom: Int):TemplateImpl;
    fun setGradientTo(gradientTo : Int):TemplateImpl;
    fun setBackgroundColor(backgroundColor:Int):TemplateImpl;
    fun setRadianLeftTop( radianLeftTop: Float):TemplateImpl;
    fun setRadianLeftBottom(radianLeftBottom:Float):TemplateImpl;
    fun setRadianRightTop(radianRightTop: Float):TemplateImpl;
    fun setRadianRightBottom(radianRightBottom:Float):TemplateImpl;
    fun setRadians(radians:Float):TemplateImpl;
    fun setStrokeWidth(strokeWidth:Int):TemplateImpl;
    fun setStrokeColor(strokeColor:Int):TemplateImpl;
    fun setStrokeDashWidth(strokeDashWidth:Float):TemplateImpl;
    fun setStrokeDashGap( strokeDashGap: Float):TemplateImpl;
}