package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.data.model.JsonAccountResponse
import com.mma.orbankmamtest.data.source.account.AccountService
import com.mma.orbankmamtest.data.AccountDataState.*
import com.mma.orbankmamtest.domain.models.Account
import com.mma.orbankmamtest.domain.models.AccountInfo
import com.mma.orbankmamtest.open.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class AccountDataRepository @Inject constructor(private val accountService: AccountService) {

    suspend fun fetchAccounts() = try {
        transformToEntity(response = accountService.getAccountsData())
    } catch (e: Exception) {
        AccountFailure
    }

    private fun transformToEntity(response: JsonAccountResponse) =
        AccountSuccess(accounts = response.toEntity())

    private fun JsonAccountResponse.toEntity() =
        data.account.map { account ->
            with(account) {
                Account(
                    accountId = accountId,
                    status = status,
                    statusUpdateDateTime = statusUpdateDateTime,
                    currency = currency,
                    accountType = accountType,
                    accountSubType = accountId,
                    nickname = nickname,
                    openingDate = openingDate,
                    transactionsUrl = transactionsUrl,
                    accountInfo = accountInfo.map { info ->
                        with(info) {
                            AccountInfo(
                                schemeName = schemeName,
                                identification = identification,
                                name = name,
                                secondaryIdentification = secondaryIdentification,
                            )
                        }
                    },
                )
            }
        }
}