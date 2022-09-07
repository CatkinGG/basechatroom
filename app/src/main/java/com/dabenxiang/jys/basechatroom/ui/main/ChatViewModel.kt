package com.dabenxiang.jys.basechatroom.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.withTransaction
import com.apollographql.apollo.api.Response
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.model.db.ChatRoomDatabase
import com.dabenxiang.jys.basechatroom.model.manager.RepositoryManager
import com.dabenxiang.jys.basechatroom.ui.main.ChatContentPagingSource.Companion.NETWORK_PAGE_SIZE
import com.dabenxiang.jys.basechatroom.ui.main.enums.ChatMessageType
import com.hasura.todo.AddMessageListMutation
import com.hasura.todo.GetAllMessageListQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class ChatViewModel @ViewModelInject constructor(
    private val repositoryManager: RepositoryManager,
    private val chatRoomDatabase: ChatRoomDatabase
): ViewModel() {
    private val _addMessageListResult = MutableLiveData< Response<AddMessageListMutation.Data>>()
    val addMessageListResult: LiveData<Response<AddMessageListMutation.Data>> = _addMessageListResult

    private val _pagingDataViewStates by lazy {
        Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            ChatContentPagingSource(repositoryManager)
        }.flow
            .cachedIn(viewModelScope)
            .asLiveData()
            .let { it as MutableLiveData<PagingData<ChatRoomMessage>> }
    }
    val pagingDataViewStates: LiveData<PagingData<ChatRoomMessage>> = _pagingDataViewStates

    fun addMessage(chatRoomMessage: ChatRoomMessage){
        viewModelScope.launch {
            repositoryManager.chatMessageRepository.addMessageList(
                chatRoomMessage.userID,
                chatRoomMessage.message,
                chatRoomMessage.messageType?.value)
                .catch { e -> Timber.d("catkingg e $e") }
                .collect {
                    Timber.d("catkingg $it")
                    _addMessageListResult.postValue(it)
                }
        }
    }
}