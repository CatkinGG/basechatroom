package com.dabenxiang.jys.basechatroom.model.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toFlow
import com.apollographql.apollo.exception.ApolloException
import com.hasura.chat.AddMessageListMutation
import com.hasura.chat.GetAllMessageListQuery
import com.hasura.chat.GetMessageListQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class ChatMessageRepository constructor(private val apolloClient: ApolloClient) {

    fun getMessageList(): Flow<GetAllMessageListQuery.Data?> {
        return flow {
            val getAllMessageListQuery = GetAllMessageListQuery()
            val result = apolloClient.query(getAllMessageListQuery)?.await()?.data
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMessageList(
        limit: Int,
        page: Int
    ): Response<GetMessageListQuery.Data>? {
        val getMessageListQuery = GetMessageListQuery(Input.fromNullable(limit),Input.fromNullable(limit*page))
        return apolloClient.query(getMessageListQuery)?.await()
    }

//    fun removeTodoList(id: Int): Flow<Response<RemoveTodoListMutation.Data>> {
//        val mutation = RemoveTodoListMutation(id)
//        return apolloClient.mutate(mutation).toFlow()
//            .flowOn(Dispatchers.IO)
//            .map {
//                if (it.hasErrors()) {
//                    throw ApolloException(it.errors?.get(0)?.message ?: "Error is Empty")
//                } else {
//                    return@map it
//                }
//            }
//    }
//
    fun addMessageList(user_id: Int, message: String, message_type: Int): Flow<Response<AddMessageListMutation.Data>> {
        val mutation = AddMessageListMutation(user_id, message, message_type)
        return apolloClient.mutate(mutation).toFlow()
            .flowOn(Dispatchers.IO)
            .map {
                if (it.hasErrors()) {
                    throw ApolloException(it.errors?.get(0)?.message ?: "Error is Empty")
                } else {
                    return@map it
                }
            }
    }

//    fun updateTodoList(id: Int, assignee: String, task: String): Flow<Response<UpdateTodoListMutation.Data>> {
//        val mutation = UpdateTodoListMutation(id, assignee, task)
//        return apolloClient.mutate(mutation).toFlow()
//            .flowOn(Dispatchers.IO)
//            .map {
//                if (it.hasErrors()) {
//                    throw ApolloException(it.errors?.get(0)?.message ?: "Error is Empty")
//                } else {
//                    return@map it
//                }
//            }
//    }

}