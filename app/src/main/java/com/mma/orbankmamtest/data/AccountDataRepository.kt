package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.data.model.JsonAccountResponse
import com.mma.orbankmamtest.data.source.AccountService
import com.mma.orbankmamtest.domain.model.Account
import com.mma.orbankmamtest.domain.model.AccountInfo
import com.mma.orbankmamtest.open.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class AccountDataRepository @Inject constructor(private val accountService: AccountService) {

   suspend fun fetchAccounts() = try {
        transformToEntity(accountService.getAccountsData())
    } catch (e: java.lang.Exception) {
        AccountDataResponse.Failure
    }

    private fun transformToEntity(response: JsonAccountResponse) =
        AccountDataResponse.Success(response.toEntity())

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