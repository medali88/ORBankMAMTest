package com.mma.orbankmamtest.domain.models

data class Transaction(
    val transactionId: String,
    val transactionReference: String,
    val amount: Amount,
    val creditDebitIndicator: String,
    val status: String,
    val valueDateTime: String,
    val transactionInformation: String,
    val bankTransactionCode: BankTransactionCode,
    val proprietaryBankTransactionCode: ProprietaryBankTransactionCode,
)

data class Amount(
    val amount: String,
    val currency: String
)

data class BankTransactionCode(
    val code: String,
    val subCode: String
)

data class ProprietaryBankTransactionCode(
    val code: String,
    val issuer: String
)

