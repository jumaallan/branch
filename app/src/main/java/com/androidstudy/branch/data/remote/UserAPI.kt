package com.androidstudy.branch.data.remote

import com.androidstudy.branch.data.model.UserLogin
import com.androidstudy.branch.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("login")
    fun loginUser(@Body userLogin: UserLogin): Call<UserResponse>
}
