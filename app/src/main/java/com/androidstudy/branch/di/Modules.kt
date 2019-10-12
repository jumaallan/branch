package com.androidstudy.branch.di

import androidx.room.Room
import com.androidstudy.branch.BuildConfig
import com.androidstudy.branch.data.BranchDatabase
import com.androidstudy.branch.data.repository.ChatRepository
import com.androidstudy.branch.ui.viewmodel.ChatViewModel
import com.androidstudy.branch.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module(override = true) {
    single {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        } else {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.NONE }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Branch-Auth-Token", "ekql7AWB4s6FXVnDD5DCqg")
                    .build()
                chain.proceed(request)
            }.addInterceptor(interceptor).build()

        Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), BranchDatabase::class.java, "branch")
            .allowMainThreadQueries().build()
    }
}

val daoModule = module {
    single { get<BranchDatabase>().chatDao() }
}

val repositoryModule = module {
    single { ChatRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { ChatViewModel(get()) }
}