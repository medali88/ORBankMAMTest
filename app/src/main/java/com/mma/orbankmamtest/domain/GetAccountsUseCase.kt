package com.mma.orbankmamtest.domain

import com.mma.orbankmamtest.data.AccountDataRepository
import com.mma.orbankmamtest.data.AccountDataResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountDataRepository: AccountDataRepository,
    private val dispatcher: CoroutineDispatcher
    ) {

    suspend fun getAccounts() = withContext(dispatcher) {
        when (val result = accountDataRepository.fetchAccounts()) {
            is AccountDataResponse.Success -> {
                result.account
            }
            AccountDataResponse.Failure -> null
        }
    }

}