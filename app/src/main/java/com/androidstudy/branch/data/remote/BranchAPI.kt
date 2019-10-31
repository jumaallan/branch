package com.androidstudy.branch.data.remote

import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.model.UserLogin
import com.androidstudy.branch.data.model.UserResponse
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
}