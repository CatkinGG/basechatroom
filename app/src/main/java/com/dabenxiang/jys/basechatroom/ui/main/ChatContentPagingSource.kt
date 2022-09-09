package com.dabenxiang.jys.basechatroom.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.model.db.ChatRoomDatabase
import com.dabenxiang.jys.basechatroom.model.manager.RepositoryManager
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.sql.Timestamp

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatContentPagingSource  (
    private val repositoryManager: RepositoryManager
) : PagingSource<Int, ChatRoomMessage>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatRoomMessage> {
        return try {
            val page = params.key ?: 0

            val result = repositoryManager.chatMessageRepository.getMessageList(NETWORK_PAGE_SIZE, page)
            val data = result?.data?.chat_message()?.map {
                ChatRoomMessage(it.id(), it.user_id(), it.message(),
                    it.create_at()?:(System.currentTimeMillis() / 1000).toString(), ChatMessageType.fromInt(it.message_type()), it.is_read
                )
            }

            val nextKey = when {
                data?.size ?: 0 >= NETWORK_PAGE_SIZE -> page + 1
                else -> null
            }

            return LoadResult.Page(
                data = data?:arrayListOf(),
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}