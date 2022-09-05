package com.dabenxiang.jys.basechatroom.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.withTransaction
import com.dabenxiang.jys.basechatroom.model.db.ChatMsg
import com.dabenxiang.jys.basechatroom.model.db.ChatRoomDatabase
import com.dabenxiang.jys.basechatroom.model.manager.RepositoryManager
import com.dabenxiang.jys.basechatroom.ui.main.ChatContentPagingSource.Companion.NETWORK_PAGE_SIZE
import com.dabenxiang.jys.chat.ChatMessage
import com.hasura.todo.GetAllTodoListQuery
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
    private val _readMessageDaoResult = MutableLiveData<List<ChatMsg>>()
    val readMessageDaoResult: LiveData<List<ChatMsg>> =
        _readMessageDaoResult

    private val _getTodoListResult = MutableLiveData<GetAllTodoListQuery.Data?>()
    val getTodoListResult: LiveData<GetAllTodoListQuery.Data?> = _getTodoListResult

    private val _pagingDataViewStates by lazy {
        Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            ChatContentPagingSource(chatRoomDatabase)
        }.flow
            .cachedIn(viewModelScope)
            .asLiveData()
            .let { it as MutableLiveData<PagingData<ChatRoomMessage>> }
    }
    val pagingDataViewStates: LiveData<PagingData<ChatRoomMessage>> = _pagingDataViewStates

    fun read() {
        viewModelScope.launch {
            flow{
                val list = chatRoomDatabase.chatMessageDao().getAll()
                emit(
                    list
                )
            }.flowOn(Dispatchers.IO)
                .collect {
                    _readMessageDaoResult.value = it
                }
        }
    }

    fun insert(vararg messages: ChatMsg){
        viewModelScope.launch {
            chatRoomDatabase.withTransaction {
                chatRoomDatabase.chatMessageDao().insertAll(*messages)
            }
        }
    }

    fun getMyTodos() {
        viewModelScope.launch {
            repositoryManager.todoRepository.getTodoList()
                .catch { e -> Timber.d("catkingg e $e") }
                .collect {
                    Timber.d("catkingg $it")
                    _getTodoListResult.postValue(it)
                }
        }
    }
}