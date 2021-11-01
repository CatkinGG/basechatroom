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
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Style for MessagesListStyle customization by xml attributes
 */
class MessagesListStyle private constructor(context: Context, attrs: AttributeSet) : Style(context, attrs) {
    protected var textAutoLinkMask = 0
        private set
    protected var incomingTextLinkColor = 0
        private set
    protected var outcomingTextLinkColor = 0
        private set
    protected var incomingAvatarWidth = 0
        private set
    protected var incomingAvatarHeight = 0
        private set
    private var incomingBubbleDrawable = 0
    private var incomingDefaultBubbleColor = 0
    private var incomingDefaultBubblePressedColor = 0
    private var incomingDefaultBubbleSelectedColor = 0
    private var incomingImageOverlayDrawable = 0
    private var incomingDefaultImageOverlayPressedColor = 0
    private var incomingDefaultImageOverlaySelectedColor = 0
    protected var incomingDefaultBubblePaddingLeft = 0
        private set
    protected var incomingDefaultBubblePaddingRight = 0
        private set
    protected var incomingDefaultBubblePaddingTop = 0
        private set
    protected var incomingDefaultBubblePaddingBottom = 0
        private set
    protected var incomingTextColor = 0
        private set
    protected var incomingTextSize = 0
        private set
    protected var incomingTextStyle = 0
        private set
    protected var incomingTimeTextColor = 0
        private set
    protected var incomingTimeTextSize = 0
        private set
    protected var incomingTimeTextStyle = 0
        private set
    protected var incomingImageTimeTextColor = 0
        private set
    protected var incomingImageTimeTextSize = 0
        private set
    protected var incomingImageTimeTextStyle = 0
        private set
    private var outcomingBubbleDrawable = 0
    private var outcomingDefaultBubbleColor = 0
    private var outcomingDefaultBubblePressedColor = 0
    private var outcomingDefaultBubbleSelectedColor = 0
    private var outcomingImageOverlayDrawable = 0
    private var outcomingDefaultImageOverlayPressedColor = 0
    private var outcomingDefaultImageOverlaySelectedColor = 0
    protected var outcomingDefaultBubblePaddingLeft = 0
        private set
    protected var outcomingDefaultBubblePaddingRight = 0
        private set
    protected var outcomingDefaultBubblePaddingTop = 0
        private set
    protected var outcomingDefaultBubblePaddingBottom = 0
        private set
    protected var outcomingTextColor = 0
        private set
    protected var outcomingTextSize = 0
        private set
    protected var outcomingTextStyle = 0
        private set
    protected var outcomingTimeTextColor = 0
        private set
    protected var outcomingTimeTextSize = 0
        private set
    protected var outcomingTimeTextStyle = 0
        private set
    protected var outcomingImageTimeTextColor = 0
        private set
    protected var outcomingImageTimeTextSize = 0
        private set
    protected var outcomingImageTimeTextStyle = 0
        private set
    protected var dateHeaderPadding = 0
        private set
    protected var dateHeaderFormat: String? = null
        private set
    protected var dateHeaderTextColor = 0
        private set
    protected var dateHeaderTextSize = 0
        private set
    protected var dateHeaderTextStyle = 0
        private set

    private fun getMessageSelector(@ColorInt normalColor: Int, @ColorInt selectedColor: Int,
                                   @ColorInt pressedColor: Int, @DrawableRes shape: Int): Drawable? {
        return getVectorDrawable(shape)
                ?.apply {
                    DrawableCompat.wrap(this).mutate()
                }?.apply {
                    DrawableCompat.setTintList(
                        this,
                        ColorStateList(arrayOf(intArrayOf(R.attr.state_selected), intArrayOf(R.attr.state_pressed), intArrayOf(-R.attr.state_pressed, -R.attr.state_selected)), intArrayOf(selectedColor, pressedColor, normalColor)))
                }
    }

//    protected fun getOutcomingBubbleDrawable(): Drawable? {
//        return if (outcomingBubbleDrawable == -1) {
//            getMessageSelector(outcomingDefaultBubbleColor, outcomingDefaultBubbleSelectedColor,
//                    outcomingDefaultBubblePressedColor, R.drawable.shape_outcoming_message)
//        } else {
//            getDrawable(outcomingBubbleDrawable)
//        }
//    }
//
//    protected fun getOutcomingImageOverlayDrawable(): Drawable? {
//        return if (outcomingImageOverlayDrawable == -1) {
//            getMessageSelector(Color.TRANSPARENT, outcomingDefaultImageOverlaySelectedColor,
//                    outcomingDefaultImageOverlayPressedColor, R.drawable.shape_outcoming_message)
//        } else {
//            getDrawable(outcomingImageOverlayDrawable)
//        }
//    }
//
//    protected fun getIncomingBubbleDrawable(): Drawable? {
//        return if (incomingBubbleDrawable == -1) {
//            getMessageSelector(incomingDefaultBubbleColor, incomingDefaultBubbleSelectedColor,
//                    incomingDefaultBubblePressedColor, R.drawable.shape_incoming_message)
//        } else {
//            getDrawable(incomingBubbleDrawable)
//        }
//    }
//
//    protected fun getIncomingImageOverlayDrawable(): Drawable? {
//        return if (incomingImageOverlayDrawable == -1) {
//            getMessageSelector(Color.TRANSPARENT, incomingDefaultImageOverlaySelectedColor,
//                    incomingDefaultImageOverlayPressedColor, R.drawable.shape_incoming_message)
//        } else {
//            getDrawable(incomingImageOverlayDrawable)
//        }
//    }

    companion object {
        fun parse(context: Context, attrs: AttributeSet): MessagesListStyle {
            val style = MessagesListStyle(context, attrs)
//            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MessagesList)
//            style.textAutoLinkMask = typedArray.getInt(R.styleable.MessagesList_textAutoLink, 0)
//            style.incomingTextLinkColor = typedArray.getColor(R.styleable.MessagesList_incomingTextLinkColor,
//                    style.getSystemAccentColor())
//            style.outcomingTextLinkColor = typedArray.getColor(R.styleable.MessagesList_outcomingTextLinkColor,
//                    style.getSystemAccentColor())
//            style.incomingAvatarWidth = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarWidth,
//                    style.getDimension(R.dimen.message_avatar_width))
//            style.incomingAvatarHeight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarHeight,
//                    style.getDimension(R.dimen.message_avatar_height))
//            style.incomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_incomingBubbleDrawable, -1)
//            style.incomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleColor,
//                    style.getColor(R.color.white_two))
//            style.incomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubblePressedColor,
//                    style.getColor(R.color.white_two))
//            style.incomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleSelectedColor,
//                    style.getColor(R.color.cornflower_blue_two_24))
//            style.incomingImageOverlayDrawable = typedArray.getResourceId(R.styleable.MessagesList_incomingImageOverlayDrawable, -1)
//            style.incomingDefaultImageOverlayPressedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultImageOverlayPressedColor,
//                    style.getColor(R.color.transparent))
//            style.incomingDefaultImageOverlaySelectedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultImageOverlaySelectedColor,
//                    style.getColor(R.color.cornflower_blue_light_40))
//            style.incomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingLeft,
//                    style.getDimension(R.dimen.message_padding_left))
//            style.incomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingRight,
//                    style.getDimension(R.dimen.message_padding_right))
//            style.incomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingTop,
//                    style.getDimension(R.dimen.message_padding_top))
//            style.incomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingBottom,
//                    style.getDimension(R.dimen.message_padding_bottom))
//            style.incomingTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTextColor,
//                    style.getColor(R.color.dark_grey_two))
//            style.incomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTextSize,
//                    style.getDimension(R.dimen.message_text_size))
//            style.incomingTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingTextStyle, Typeface.NORMAL)
//            style.incomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTimeTextColor,
//                    style.getColor(R.color.warm_grey_four))
//            style.incomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTimeTextSize,
//                    style.getDimension(R.dimen.message_time_text_size))
//            style.incomingTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingTimeTextStyle, Typeface.NORMAL)
//            style.incomingImageTimeTextColor = typedArray.getColor(R.styleable.MessagesList_incomingImageTimeTextColor,
//                    style.getColor(R.color.warm_grey_four))
//            style.incomingImageTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingImageTimeTextSize,
//                    style.getDimension(R.dimen.message_time_text_size))
//            style.incomingImageTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_incomingImageTimeTextStyle, Typeface.NORMAL)
//            style.outcomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_outcomingBubbleDrawable, -1)
//            style.outcomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleColor,
//                    style.getColor(R.color.cornflower_blue_two))
//            style.outcomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubblePressedColor,
//                    style.getColor(R.color.cornflower_blue_two))
//            style.outcomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleSelectedColor,
//                    style.getColor(R.color.cornflower_blue_two_24))
//            style.outcomingImageOverlayDrawable = typedArray.getResourceId(R.styleable.MessagesList_outcomingImageOverlayDrawable, -1)
//            style.outcomingDefaultImageOverlayPressedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultImageOverlayPressedColor,
//                    style.getColor(R.color.transparent))
//            style.outcomingDefaultImageOverlaySelectedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultImageOverlaySelectedColor,
//                    style.getColor(R.color.cornflower_blue_light_40))
//            style.outcomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingLeft,
//                    style.getDimension(R.dimen.message_padding_left))
//            style.outcomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingRight,
//                    style.getDimension(R.dimen.message_padding_right))
//            style.outcomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingTop,
//                    style.getDimension(R.dimen.message_padding_top))
//            style.outcomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingBottom,
//                    style.getDimension(R.dimen.message_padding_bottom))
//            style.outcomingTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTextColor,
//                    style.getColor(R.color.white))
//            style.outcomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTextSize,
//                    style.getDimension(R.dimen.message_text_size))
//            style.outcomingTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingTextStyle, Typeface.NORMAL)
//            style.outcomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTimeTextColor,
//                    style.getColor(R.color.white60))
//            style.outcomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTimeTextSize,
//                    style.getDimension(R.dimen.message_time_text_size))
//            style.outcomingTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingTimeTextStyle, Typeface.NORMAL)
//            style.outcomingImageTimeTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingImageTimeTextColor,
//                    style.getColor(R.color.warm_grey_four))
//            style.outcomingImageTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingImageTimeTextSize,
//                    style.getDimension(R.dimen.message_time_text_size))
//            style.outcomingImageTimeTextStyle = typedArray.getInt(R.styleable.MessagesList_outcomingImageTimeTextStyle, Typeface.NORMAL)
//            style.dateHeaderPadding = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderPadding,
//                    style.getDimension(R.dimen.message_date_header_padding))
//            style.dateHeaderFormat = typedArray.getString(R.styleable.MessagesList_dateHeaderFormat)
//            style.dateHeaderTextColor = typedArray.getColor(R.styleable.MessagesList_dateHeaderTextColor,
//                    style.getColor(R.color.warm_grey_two))
//            style.dateHeaderTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderTextSize,
//                    style.getDimension(R.dimen.message_date_header_text_size))
//            style.dateHeaderTextStyle = typedArray.getInt(R.styleable.MessagesList_dateHeaderTextStyle, Typeface.NORMAL)
//            typedArray.recycle()
            return style
        }
    }
}