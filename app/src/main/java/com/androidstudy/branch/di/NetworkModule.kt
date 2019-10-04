package com.androidstudy.branch.di

import com.androidstudy.branch.data.remote.ThreadAPI
import org.koin.dsl.module.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideThreadAPI(get()) }
}

fun provideThreadAPI(retrofit: Retrofit): ThreadAPI = retrofit.create(ThreadAPI::class.java)
