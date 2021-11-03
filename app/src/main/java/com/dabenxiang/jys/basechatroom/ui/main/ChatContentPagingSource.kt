package com.dabenxiang.jys.basechatroom.ui.main

import androidx.paging.PagingSource
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import timber.log.Timber
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatContentPagingSource(
) : PagingSource<Int, ChatRoomMessage>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatRoomMessage> {
        return try {
            val page = params.key ?: 1

            val processedData: ArrayList<ChatRoomMessage> = arrayListOf(
                ChatRoomMessage(1,2,"u catkingg?",202110281700, ChatMessageType.TEXT, true),
                ChatRoomMessage(2,2,"hihi",202111031900, ChatMessageType.TEXT, true),
                ChatRoomMessage(3,0,"catkingg is me",202111032000, ChatMessageType.TEXT, true)
            )

            val nextKey = when {
                processedData.size ?: 0 >= NETWORK_PAGE_SIZE -> page + 1
                else -> null
            }

            return LoadResult.Page(
                data = processedData,
                prevKey = null,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }
}