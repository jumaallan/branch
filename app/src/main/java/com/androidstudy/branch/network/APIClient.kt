package com.androidstudy.branch.network

import com.androidstudy.branch.BuildConfig
import com.androidstudy.branch.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    fun getClient(token: String): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        } else {
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.NONE }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Branch-Auth-Token", token)
                    .build()
                chain.proceed(request)
            }.addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}