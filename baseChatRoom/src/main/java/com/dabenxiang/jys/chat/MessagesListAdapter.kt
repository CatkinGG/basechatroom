package com.dabenxiang.jys.chat

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.DiffUtil
import com.dabenxiang.jys.chat.holder.MessageViewHolder
import com.dabenxiang.jys.view.exchange.chat.base.style.MessagesListStyle

open class MessagesListAdapter(
    diffCallbackObject: DiffUtil.ItemCallback<ChatMessage>,
    private val holders: MessageHolders,
    private val chatContentFuncItem: ChatContentFuncItem,
    private val myID: Int
) : PagingDataAdapter<ChatMessage, MessageViewHolder>(diffCallbackObject) {

    protected val messagesListStyle: MessagesListStyle? = null

    constructor(
        diffCallbackObject: DiffUtil.ItemCallback<ChatMessage>,
        chatContentFuncItem: ChatContentFuncItem,
        senderId: Int
    ): this(diffCallbackObject, MessageHolders(), chatContentFuncItem, senderId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return holders.getHolder(parent, viewType, messagesListStyle)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        getItem(position)?.run {
            holder.onBind(this, chatContentFuncItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.run {
            holders.getViewType(this, myID)
        }?: -1
    }

    open fun isDataEmpty(): Boolean {
        return itemCount == 0
    }
}