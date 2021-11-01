package com.dabenxiang.jys.chat.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dabenxiang.jys.basechat.R
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.view.custom.ChatIconDrawable
import com.dabenxiang.jys.chat.widget.utility.GeneralUtils
import com.dabenxiang.jys.view.exchange.chat.base.style.MessagesListStyle

/**
 * Base view holder for incoming message
 */
open class BaseReceivedTextViewHolder(itemView: View) : MessageViewHolder(itemView),
    DefaultMessageViewHolder {
    protected val tvTime: TextView? = itemView.findViewById(R.id.tv_time)
    protected val ivAvatar: ImageView? = itemView.findViewById(R.id.iv_avatar)
    protected val tvContent: TextView? = itemView.findViewById(R.id.tv_content)

    override fun onBind(item: ChatMessage, contactRecordFuncItem: ChatContentFuncItem) {
        ivAvatar?.setImageDrawable(ChatIconDrawable(ivAvatar, item.userID.toString().first().toString()))
        tvContent?.text = item.message
        tvTime?.text = GeneralUtils.convertTimestampToString(item.createdAt.toString().toLongOrNull()
                ?: 0)
    }

    override fun applyStyle(style: MessagesListStyle?) {

    }
}

