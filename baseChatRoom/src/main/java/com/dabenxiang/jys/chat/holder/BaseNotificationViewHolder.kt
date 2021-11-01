package com.dabenxiang.jys.chat.holder

import android.view.View
import android.widget.TextView
import com.dabenxiang.jys.basechat.R
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.widget.utility.GeneralUtils

open class BaseNotificationViewHolder(itemView: View): MessageViewHolder(itemView) {
    protected val tvNotificationMessage: TextView = itemView.findViewById(R.id.tv_notification_message)
    protected val tvTime: TextView = itemView.findViewById(R.id.tv_time)

    override fun onBind(item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        tvNotificationMessage.text = item.message
        tvTime.text = GeneralUtils.convertTimestampToString(item.createdAt.toString().toLongOrNull() ?: 0)
    }
}