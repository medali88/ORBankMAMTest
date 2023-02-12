package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.domain.models.Transaction

sealed class TransactionDataState {

    data class TransactionSuccess(val transactions: List<Transaction>) :
        TransactionDataState()
    object TransactionFailure : TransactionDataState()
}
