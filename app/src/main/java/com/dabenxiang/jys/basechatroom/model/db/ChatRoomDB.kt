package com.dabenxiang.jys.basechatroom.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChatMsg::class], version = 1, exportSchema = false)
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

    abstract fun chatMessageDao(): ChatMsgDao
}