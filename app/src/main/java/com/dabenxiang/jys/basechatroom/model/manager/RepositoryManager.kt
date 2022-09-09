package com.dabenxiang.jys.basechatroom.model.manager

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.CustomTypeAdapter
import com.apollographql.apollo.api.CustomTypeValue
import com.dabenxiang.jys.basechatroom.model.repository.ChatMessageRepository
import com.hasura.chat.type.CustomType

import okhttp3.OkHttpClient
import java.text.ParseException
import java.text.SimpleDateFormat


class RepositoryManager(
    private val graphqlOkHttpClient: OkHttpClient
) {
    val domain = "https://special-louse-50.hasura.app/v1/graphql"

    val chatMessageRepository by lazy { ChatMessageRepository(apolloClient) }

    val dateCustomTypeAdapter = object : CustomTypeAdapter<String> {
        var ISO8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")

        override fun decode(value: CustomTypeValue<*>): String {
            try {
                return ISO8601.parse(value.value.toString()).toString()
            } catch (e: ParseException) {
                throw RuntimeException(e)
            }

        }

        override fun encode(value: String): CustomTypeValue<*> {
            return CustomTypeValue.GraphQLString(value)
        }
    }

    private val apolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(domain)
            .okHttpClient(graphqlOkHttpClient)
            .addCustomTypeAdapter(CustomType.TIMESTAMPTZ, dateCustomTypeAdapter)
            .build()
    }
}