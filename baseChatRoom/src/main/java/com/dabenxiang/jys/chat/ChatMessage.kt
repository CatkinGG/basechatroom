package com.dabenxiang.jys.chat

abstract class ChatMessage(
        /**
         * ID
         */
        val id: Int = -1,
    /**
     * 發送訊息的用戶ID
     */
    val userID: Int = -1,
    /**
     * 訊息內容
     */
    var message: String = "",
    /**
     * 建立時間
     */
    val createdAt: Any = (System.currentTimeMillis() / 1000).toString()
){
    abstract fun getImageUrl(): String?
}
