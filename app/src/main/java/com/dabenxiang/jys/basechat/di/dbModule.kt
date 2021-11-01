package com.dabenxiang.jys.basechat.di

import android.content.Context
import androidx.room.Room
import com.dabenxiang.jys.basechat.model.db.ChatRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dbModule {

    @Singleton
    @Provides
    fun provideChatRoomDatabase(@ApplicationContext context: Context): ChatRoomDatabase {
        val db = Room.databaseBuilder(
            context,
            ChatRoomDatabase::class.java, "chat_room.db"
        ).build()
        return db
    }
}
