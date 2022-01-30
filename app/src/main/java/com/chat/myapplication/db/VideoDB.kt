package com.chat.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chat.myapplication.model.Video

@Database(entities = [Video::class], version = 1)
abstract class VideoDB : RoomDatabase() {
    abstract fun videoDao() : VideoDao
}