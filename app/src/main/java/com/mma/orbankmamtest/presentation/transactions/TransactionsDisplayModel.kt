package com.mma.orbankmamtest.presentation.transactions

data class TransactionsDisplayModel (
    val transactionId: String,
    val transactionReference: String,
    val amount: AmountDisplayModel,
    val creditDebitIndicator: String,
    val status: String,
    val valueDateTime: String,
    val transactionInformation: String,
    val bankTransactionCode: BankTransactionCodeDisplayModel,
    val proprietaryBankTransactionCode: ProprietaryBankTransactionCodeDisplayModel,
    val address: String?,
)

data class AmountDisplayModel(
    val amount: String,
    val currency: String
)

data class BankTransactionCodeDisplayModel(
    val code: String,
    val subCode: String
)

data class ProprietaryBankTransactionCodeDisplayModel(
    val code: String,
    val issuer: String
)

