package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.model.UserLogin
import com.androidstudy.branch.data.model.UserResponse

interface BranchRepository {

    suspend fun loginUser(userLogin: UserLogin): UserResponse?
}