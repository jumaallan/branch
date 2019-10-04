package com.androidstudy.branch.di

import com.androidstudy.branch.data.repository.ThreadRepository
import com.androidstudy.branch.ui.adapter.ThreadDataSourceFactory
import com.androidstudy.branch.ui.viewmodel.ThreadViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val threadModule = module {
    single { provideThreadDataSourceFactory(get()) }
    viewModel { provideThreadViewModel(get()) }
}


fun provideThreadDataSourceFactory(repository: ThreadRepository) =
    ThreadDataSourceFactory(repository)

fun provideThreadViewModel(dataSourceFactory: ThreadDataSourceFactory) =
    ThreadViewModel(dataSourceFactory)