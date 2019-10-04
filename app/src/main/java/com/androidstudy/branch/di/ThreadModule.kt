package com.androidstudy.branch.di

import com.androidstudy.branch.data.repository.ChatRepository
import com.androidstudy.branch.ui.adapter.ChatDataSourceFactory
import com.androidstudy.branch.ui.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val threadModule = module {
    single { provideChatDataSourceFactory(get()) }
    viewModel { provideChatViewModel(get()) }
}


fun provideChatDataSourceFactory(repository: ChatRepository) =
    ChatDataSourceFactory(repository)

fun provideChatViewModel(dataSourceFactory: ChatDataSourceFactory) =
    ChatViewModel(dataSourceFactory)