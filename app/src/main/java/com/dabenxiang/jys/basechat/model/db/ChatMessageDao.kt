package com.dabenxiang.jys.basechat.model.db

import androidx.room.*
import com.dabenxiang.jys.view.exchange.chat.enums.ChatMessageType

@Entity
data class ChatMessage(
    @PrimaryKey val id: Int?,
    /**
     * 發送訊息的用戶ID
     */
    @ColumnInfo(name = "user_id")val userID: Int?,
    /**
     * 訊息內容
     */
    @ColumnInfo(name = "message")var message: String?,
    /**
     * 建立時間
     */
    @ColumnInfo(name = "create_at")val createdAt: Long?,
    /**
     * 訊息類型
     */
    @ColumnInfo(name = "message_type")val messageType: ChatMessageType?,
    /**
     * 是否已讀
     */
    @ColumnInfo(name = "is_read")var isRead: Boolean?
)

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chatmessage")
    fun getAll(): List<ChatMessage>

    @Query("SELECT * FROM chatmessage WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ChatMessage>

    @Query("SELECT * FROM chatmessage WHERE message LIKE '%' || :text || '%'")
    fun findMessageByText(text: String): ChatMessage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg chatMessage: ChatMessage)

    @Delete
    suspend fun delete(chatMessage: ChatMessage)
}
