package com.dabenxiang.jys.basechatroom.ui.main

import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import com.dabenxiang.jys.chat.ChatMessage

class ChatRoomMessage(
    /**
         * ID
         */
        id: Int = -1,
    /**
         * 發送訊息的用戶ID
         */
        userID: Int = -1,
    /**
         * 訊息內容
         */
        message: String = "",
    /**
         * 建立時間
         */
        createdAt: Any = (System.currentTimeMillis() / 1000).toString(),
    /**
         * 訊息類型
         */
        val messageType: ChatMessageType = ChatMessageType.TEXT,

    /**
         * 是否已讀
         */
        var isRead: Boolean = false
): ChatMessage(id, userID, message, createdAt)
{
    companion object {
        fun build(origin: ChatMsg): ChatRoomMessage {
            return ChatRoomMessage(
                id = origin.id?:-1,
                userID = origin.userID?:-1,
                messageType = origin.messageType?: ChatMessageType.TEXT,
                message = origin.message?: "",
                isRead = origin.isRead?: true,
                createdAt = origin.createdAt?:""
            )
        }
    }

    override fun getImageByteArray(): String? {
        if(messageType == ChatMessageType.PICTURE)
            return message
        else
            return null
    }
}
