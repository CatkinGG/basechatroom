package com.dabenxiang.jys.basechatroom.model.db

import androidx.room.*
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType

@Entity
data class ChatMsg(
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
interface ChatMsgDao {
    @Query("SELECT * FROM chatmsg")
    fun getAll(): List<ChatMsg>

    @Query("SELECT * FROM chatmsg WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ChatMsg>

    @Query("SELECT * FROM chatmsg WHERE message LIKE '%' || :text || '%'")
    fun findMessageByText(text: String): ChatMsg

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg chatMsg: ChatMsg)

    @Delete
    suspend fun delete(chatMsg: ChatMsg)
}
