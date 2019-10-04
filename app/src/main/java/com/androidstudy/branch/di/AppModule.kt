package com.androidstudy.branch.di

import com.androidstudy.branch.BuildConfig
import com.androidstudy.branch.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

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