package com.androidstudy.branch.di

import com.androidstudy.branch.data.remote.ChatAPI
import org.koin.dsl.module.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideChatAPI(get()) }
}

fun provideChatAPI(retrofit: Retrofit): ChatAPI = retrofit.create(ChatAPI::class.java)
