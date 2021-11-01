package com.dabenxiang.jys.view.exchange.chat.enums

enum class ChatMessageType(val value: Int) {
    TEXT(2), //use default: VIEW_TYPE_TEXT_MESSAGE
    PICTURE(3), //use default: VIEW_TYPE_IMAGE_MESSAGE
    NOTIFICATION(4),
    TRADE_RULE(5),
    DIVIDER(6),
    UNKNOW(7)
}