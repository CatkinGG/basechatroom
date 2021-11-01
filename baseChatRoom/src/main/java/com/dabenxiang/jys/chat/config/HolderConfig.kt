package com.dabenxiang.jys.chat.config

import com.dabenxiang.jys.chat.holder.MessageViewHolder

class HolderConfig(
        var holder: Class<out MessageViewHolder>,
        var layout: Int,
        var argTypes: Array<Class<*>> = arrayOf(),
        var payload: Array<Any> = arrayOf()
) {
}