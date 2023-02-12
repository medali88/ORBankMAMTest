package com.mma.orbankmamtest.presentation.accounts

data class AccountsDisplayModel (
    val accountId: String,
    val status: String,
    val statusUpdateDateTime: String,
    val currency: String,
    val accountType: String,
    val accountSubType: String,
    val nickname: String,
    val openingDate: String?,
    val transactionsUrl: String,
    val accountInfo: List<AccountInfoDisplayModel>
)

data class AccountInfoDisplayModel (
    val schemeName: String,
    val identification: String,
    val name: String,
    val secondaryIdentification: String?,
)
