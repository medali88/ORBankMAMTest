package com.mma.orbankmamtest.data.source.transaction

import com.mma.orbankmamtest.data.model.JsonTransactionResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface TransactionEndPoint {

    @Headers("accept: application/json")
    @GET
    suspend fun getTransaction(
        @Url transactionsUrl: String
    ): JsonTransactionResponse
}