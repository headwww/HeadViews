package com.head.view.menu

import androidx.annotation.RawRes

data class MenuItemImpl(var checked:Boolean = false,var itemView: ItemView = ItemView(),var ripples:Boolean = true,@RawRes var rawRes:Int = 0)
//
//setAnimation(@RawRes final int rawRes)
//setAnimation(final String assetName)
//setAnimationFromJson(String jsonString) //不建议使用
//setAnimationFromJson(String jsonString, @Nullable String cacheKey)
//setAnimation(JsonReader reader, @Nullable String cacheKey)
//setAnimationFromUrl(String url)