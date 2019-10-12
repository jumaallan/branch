package com.androidstudy.branch.data.remote

import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.model.UserLogin
import com.androidstudy.branch.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BranchAPI {

    @POST("login")
    fun loginUser(@Body userLogin: UserLogin): Call<UserResponse>

    @GET("api/messages")
    fun getMessages(): List<Chat>
}