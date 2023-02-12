package com.mma.orbankmamtest.data.source.account

import com.mma.orbankmamtest.open.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class AccountService @Inject constructor(private val accountEndPoint: AccountEndPoint) {
    suspend fun getAccountsData() = accountEndPoint.getAccounts()
}