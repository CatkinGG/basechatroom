/*******************************************************************************
 * Copyright 2016 stfalcon.com
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dabenxiang.jys.view.exchange.chat.base.style

import android.R
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Base class for chat component styles
 */
abstract class Style protected constructor(protected var context: Context, attrs: AttributeSet) {
    protected var resources: Resources
    protected var attrs: AttributeSet
    protected val systemAccentColor: Int
        protected get() = getSystemColor(R.attr.colorAccent)
    protected val systemPrimaryColor: Int
        protected get() = getSystemColor(R.attr.colorPrimary)
    protected val systemPrimaryDarkColor: Int
        protected get() = getSystemColor(R.attr.colorPrimaryDark)
    protected val systemPrimaryTextColor: Int
        protected get() = getSystemColor(R.attr.textColorPrimary)
    protected val systemHintColor: Int
        protected get() = getSystemColor(R.attr.textColorHint)

    protected fun getSystemColor(@AttrRes attr: Int): Int {
        val typedValue = TypedValue()
        val a: TypedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(attr))
        val color: Int = a.getColor(0, 0)
        a.recycle()
        return color
    }

    protected fun getDimension(@DimenRes dimen: Int): Int {
        return resources.getDimensionPixelSize(dimen)
    }

    protected fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    protected fun getDrawable(@DrawableRes drawable: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawable)
    }

    protected fun getVectorDrawable(@DrawableRes drawable: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawable)
    }

    init {
        resources = context.resources
        this.attrs = attrs
    }
}