package com.androidstudy.branch.data.remote

import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BranchAPI {

    @POST("api/login")
    fun loginUser(@Body userLogin: UserLogin): Call<UserResponse>

    @GET("api/messages")
    suspend fun getMessages(): Response<List<Chat>>

    @POST("api/messages")
    suspend fun sendMessage(@Body chatRequest: ChatRequest): Response<ChatResponse>

    @GET("api/stock_messages")
    suspend fun getStockMessages(): Response<List<StockMessage>>
}