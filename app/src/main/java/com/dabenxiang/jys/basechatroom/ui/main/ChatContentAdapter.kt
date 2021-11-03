package com.dabenxiang.jys.basechatroom.ui.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.dabenxiang.jys.basechatroom.R
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg

import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import com.dabenxiang.jys.basechatroom.ui.main.holder.*
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.MessageHolders
import com.dabenxiang.jys.chat.MessagesListAdapter
import com.dabenxiang.jys.chat.holder.MessageViewHolder
import kotlin.math.abs

class ChatContentAdapter(
    private val userID: Int?,
    private val chatContentFuncItem: ChatContentFuncItem
) : MessagesListAdapter(
    diffCallback,
    MessageHolders().also {
        it.setSentTextConfig(SentTextViewHolder::class.java, R.layout.item_chat_sent_text)
        it.setReceivedTextConfig(ReceivedTextViewHolder::class.java, R.layout.item_chat_received_text, arrayOf("they"))
        it.setSentImageConfig(SentPicViewHolder::class.java, R.layout.item_chat_sent_pic)
        it.setReceivedImageConfig(ReceivedPicViewHolder::class.java, R.layout.item_chat_received_pic, arrayOf("they"))
    }.also {
        it.registerContentType(
                ChatMessageType.TRADE_RULE.value,
                ReceivedTextViewHolder::class.java,
                arrayOf(String::class.java),
                arrayOf("nickname"),
                R.layout.item_chat_received_text,
                SentTextViewHolder::class.java,
                arrayOf(),
                arrayOf(),
                R.layout.item_chat_sent_text,
                hasContentFor
        )
    }.also {
        it.registerContentType(
                ChatMessageType.DIVIDER.value,
                DividerViewHolder::class.java,
                arrayOf(),
                arrayOf(),
                R.layout.item_chat_divider,
                DividerViewHolder::class.java,
                arrayOf(),
                arrayOf(),
                R.layout.item_chat_divider,
                hasContentFor
        )
    }
        ,
    chatContentFuncItem,
    userID?:-1) {

    companion object {
        const val TRADE_RULE = "TRADE_RULE"
        const val DATE_DIVIDER = "DATE_DIVIDER"
        const val UNREAD_DIVIDER = "UNREAD_DIVIDER"
        const val UNREAD = "unread"
        private val diffCallback = object : DiffUtil.ItemCallback<ChatMessage>() {
            override fun areItemsTheSame(
                oldItem: ChatMessage,
                newItem: ChatMessage
            ): Boolean {
                return oldItem.id == newItem.id && oldItem.message.length == newItem.message.length
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ChatMessage,
                newItem: ChatMessage
            ): Boolean {
                return oldItem == newItem
            }
        }

        private val hasContentFor: ((ChatMessage, Int) -> Boolean) = { message: ChatMessage, type: Int ->
            when(type){
                ChatMessageType.NOTIFICATION.value -> (message as ChatRoomMessage).messageType == ChatMessageType.NOTIFICATION
                ChatMessageType.TRADE_RULE.value -> (message as ChatRoomMessage).messageType == ChatMessageType.TRADE_RULE
                ChatMessageType.DIVIDER.value -> (message as ChatRoomMessage).messageType == ChatMessageType.DIVIDER
                else -> false
            }
        }
    }

    var isForceRefresh = false
    var latestReadedMsgId = -1

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        getItem(position)?.run {
            if(this.id > latestReadedMsgId)
                latestReadedMsgId = this.id
            holder.onBind(this, chatContentFuncItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewType = super.getItemViewType(position)
        if (abs(viewType) == ChatMessageType.TRADE_RULE.value)
            viewType = if(0 == userID) -1 * viewType else viewType
        return viewType
    }
}