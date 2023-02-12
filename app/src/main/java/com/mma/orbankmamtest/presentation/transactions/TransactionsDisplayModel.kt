package com.mma.orbankmamtest.presentation.transactions

data class TransactionsDisplayModel (
    val transactionReference: String,
    val amount: AmountDisplayModel,
    val creditDebitIndicator: String,
    val status: String,
    val valueDateTime: String,
    val transactionInformation: String,
    val address: String?,
)

data class AmountDisplayModel(
    val amount: String,
    val currency: String
)



