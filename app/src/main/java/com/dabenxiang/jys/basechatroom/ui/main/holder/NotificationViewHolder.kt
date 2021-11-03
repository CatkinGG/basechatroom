package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.view.View
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.BaseNotificationViewHolder
import com.dabenxiang.jys.chat.widget.utility.GeneralUtils

class NotificationViewHolder(itemView: View): BaseNotificationViewHolder(itemView) {

    override fun onBind(item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        tvNotificationMessage.text = "notification"

        tvTime.text = GeneralUtils.convertTimestampToString(item.createdAt.toString().toLongOrNull() ?: 0)
    }
}