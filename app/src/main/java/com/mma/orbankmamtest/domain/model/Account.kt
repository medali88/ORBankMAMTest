package com.mma.orbankmamtest.domain.model

data class Account (
    val accountId: String,
    val status: String,
    val statusUpdateDateTime: String,
    val currency: String,
    val accountType: String,
    val accountSubType: String,
    val nickname: String,
    val openingDate: String,
    val transactionsUrl: String,
    val accountInfo: List<AccountInfo>
)

data class AccountInfo (
    val schemeName: String,
    val identification: String,
    val name: String,
    val secondaryIdentification: String,
)
