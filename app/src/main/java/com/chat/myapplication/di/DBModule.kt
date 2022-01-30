package com.chat.myapplication.di

import android.content.Context
import androidx.room.Room
import com.chat.myapplication.db.VideoDB
import com.chat.myapplication.db.VideoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context) : VideoDB{
        return Room.databaseBuilder(context, VideoDB::class.java, "video.db").build()
    }
    @Provides
    @Singleton
    fun provideVideoDao(videoDB: VideoDB) : VideoDao{
        return videoDB.videoDao()
    }
}