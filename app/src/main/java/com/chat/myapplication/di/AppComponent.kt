package com.chat.myapplication.di

import com.chat.myapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetModule::class,DatabaseModule::class])
interface AppComponent{
    //Activities where we need to inject
    fun inject(m: MainActivity)
}