package com.mma.orbankmamtest.data.source.account

import com.mma.orbankmamtest.data.model.JsonAccountResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface AccountEndPoint {

    @Headers("accept: application/json")
    @GET("/v3/ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
    suspend fun getAccounts(): JsonAccountResponse
}