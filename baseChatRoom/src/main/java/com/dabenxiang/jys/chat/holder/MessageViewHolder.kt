package com.dabenxiang.jys.chat.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.view.exchange.chat.base.style.MessagesListStyle

abstract class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: ChatMessage, contactRecordFuncItem: ChatContentFuncItem)
}

/*
   * DEFAULTS
   * */
internal interface DefaultMessageViewHolder {
    fun applyStyle(style: MessagesListStyle?)
}

