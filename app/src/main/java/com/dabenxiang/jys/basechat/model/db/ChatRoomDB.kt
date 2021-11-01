package com.dabenxiang.jys.basechat.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChatMessage::class], version = 1, exportSchema = false)
abstract class ChatRoomDatabase : RoomDatabase() {
    companion object {
//        fun create(context: Context): ChatRoomDatabase {
//            val databaseBuilder =
//                Room.databaseBuilder(context, ChatRoomDatabase::class.java, "chat_room.db")
//
//            return databaseBuilder
//                .addMigrations(MIGRATION_1_2)
//                .build()
//        }
    }

    abstract fun chatMessageDao(): ChatMessageDao
}