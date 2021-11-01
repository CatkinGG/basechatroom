package com.dabenxiang.jys.basechat.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dabenxiang.jys.basechat.model.db.ChatRoomDatabase
import androidx.room.withTransaction
import com.dabenxiang.jys.basechat.model.db.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ChatViewModel @ViewModelInject constructor(
    private val chatRoomDatabase: ChatRoomDatabase
): ViewModel() {
    private val _readMessageDaoResult = MutableLiveData<List<ChatMessage>>()
    val readMessageDaoResult: LiveData<List<ChatMessage>> =
        _readMessageDaoResult

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

    fun insert(vararg messages: ChatMessage){
        viewModelScope.launch {
            chatRoomDatabase.withTransaction {
                chatRoomDatabase.chatMessageDao().insertAll(*messages)
            }
        }
    }
}