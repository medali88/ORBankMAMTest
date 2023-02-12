package com.mma.orbankmamtest.data.source.transaction

import com.mma.orbankmamtest.open.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TransactionService @Inject constructor(private val transactionEndPoint: TransactionEndPoint) {
    suspend fun getTransactionData(transactionsUrl: String) =
        transactionEndPoint.getTransaction(transactionsUrl = transactionsUrl)
}