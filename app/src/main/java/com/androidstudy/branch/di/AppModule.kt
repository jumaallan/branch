package com.androidstudy.branch.di

import com.androidstudy.branch.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val baseUrl = ""

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        } else {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.NONE }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Branch-Auth-Token", "")
                    .build()
                chain.proceed(request)
            }.addInterceptor(interceptor).build()

        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}