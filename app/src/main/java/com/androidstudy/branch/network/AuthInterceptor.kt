package com.androidstudy.branch.network

import com.androidstudy.branch.Branch
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private var app: Branch = Branch.appContext as Branch

    override fun intercept(chain: Interceptor.Chain): Response {
        var chainRequest = chain.request()
        chainRequest = if (chainRequest.headers.names().contains("No-Auth")) {
            chainRequest.newBuilder().removeHeader("No-Auth").build()
        } else {
            chainRequest.newBuilder()
                .addHeader("X-Branch-Auth-Token", app.settings.getBranchAuthToken().toString())
                .build()
        }
        return chain.proceed(chainRequest)
    }

}