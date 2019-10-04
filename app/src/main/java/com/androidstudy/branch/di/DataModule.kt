package com.androidstudy.branch.di

import com.androidstudy.branch.data.remote.ChatAPI
import com.androidstudy.branch.data.repository.ChatRepository
import com.androidstudy.branch.data.repository.ChatRepositoryImpl
import org.koin.dsl.module.module

val dataModule = module {
    single<ChatRepository> { provideThreadRepository(get()) }
}

fun provideThreadRepository(chatAPI: ChatAPI) =
    ChatRepositoryImpl(chatAPI)