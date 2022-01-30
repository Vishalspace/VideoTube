package com.chat.myapplication

import android.app.Application
import com.chat.myapplication.di.*

internal class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this.applicationContext))
            .netModule(NetModule())
            .databaseModule(DatabaseModule())
            .build()
    }

}