package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.dabenxiang.jys.basechatroom.ui.main.ChatContentAdapter.Companion.UNREAD
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.MessageViewHolder
import kotlinx.android.synthetic.main.item_chat_divider.view.*

class DividerViewHolder(itemView: View): MessageViewHolder(itemView) {
    private val tvUnread: TextView = itemView.tv_unread_message
    private val leftLine: View = itemView.left_line
    private val rightLine: View = itemView.right_line
    private val tvTime: TextView = itemView.tv_time

    override fun onBind(item: ChatMessage, chatContentFuncItem: ChatContentFuncItem) {
        tvTime.text = item.createdAt.toString()
        if(item.message == UNREAD) {
            tvUnread.visibility = VISIBLE
            leftLine.visibility = VISIBLE
            rightLine.visibility = VISIBLE
            if(item.createdAt.toString() == "")
                tvTime.visibility = GONE
        } else {
            tvUnread.visibility = GONE
            leftLine.visibility = GONE
            rightLine.visibility = GONE
            tvTime.visibility = VISIBLE
        }
    }
}