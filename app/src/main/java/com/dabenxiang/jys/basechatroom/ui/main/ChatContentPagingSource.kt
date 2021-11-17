package com.dabenxiang.jys.basechatroom.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.dabenxiang.jys.basechatroom.model.db.ChatRoomDatabase
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatContentPagingSource  (
    private val chatRoomDatabase: ChatRoomDatabase
) : PagingSource<Int, ChatRoomMessage>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatRoomMessage> {
        return try {
            val page = params.key ?: 1

            val data = GlobalScope.async {
                chatRoomDatabase.chatMessageDao().getAll().map {
                    Timber.d("message: ${it.message}")
                    ChatRoomMessage.build(it)
                }
            }.await()

            val nextKey = when {
                data.size ?: 0 >= NETWORK_PAGE_SIZE -> page + 1
                else -> null
            }

            return LoadResult.Page(
                data = data.reversed(),
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}