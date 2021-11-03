package com.dabenxiang.jys.basechatroom.ui.main.holder

import android.view.View
import com.dabenxiang.jys.chat.ChatContentFuncItem
import com.dabenxiang.jys.chat.ChatMessage
import com.dabenxiang.jys.chat.holder.BaseReceivedImageViewHolder
import com.dabenxiang.jys.chat.view.custom.ChatIconDrawable

class ReceivedPicViewHolder (itemView: View, private val nickName: String) : BaseReceivedImageViewHolder(itemView) {
    override fun onBind(item: ChatMessage, contactRecordFuncItem: ChatContentFuncItem) {
        super.onBind(item, contactRecordFuncItem)
        ivAvatar?.setImageDrawable(ChatIconDrawable(ivAvatar, nickName.first().toString()))
    }
}