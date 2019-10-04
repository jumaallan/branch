package com.androidstudy.branch.di

import com.androidstudy.branch.data.remote.ThreadAPI
import com.androidstudy.branch.data.repository.ThreadRepository
import com.androidstudy.branch.data.repository.ThreadRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<ThreadRepository> { provideThreadRepository(get()) }
}

fun provideThreadRepository(threadAPI: ThreadAPI) =
    ThreadRepositoryImpl(threadAPI)