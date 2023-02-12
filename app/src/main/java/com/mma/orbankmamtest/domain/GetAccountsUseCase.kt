package com.mma.orbankmamtest.domain

import com.mma.orbankmamtest.data.AccountDataRepository
import com.mma.orbankmamtest.data.AccountDataState.*
import com.mma.orbankmamtest.domain.models.Account
import com.mma.orbankmamtest.domain.AccountsState.*
import com.mma.orbankmamtest.open.OpenForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OpenForTesting
class GetAccountsUseCase @Inject constructor(
    private val accountDataRepository: AccountDataRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAccounts() = withContext(dispatcher) {
        when (val result = accountDataRepository.fetchAccounts()) {
            is AccountSuccess -> {
                ValidAccounts(account = result.accounts)
            }
            AccountFailure -> InvalidAccounts
        }
    }
}

sealed class AccountsState {
    data class ValidAccounts(val account: List<Account>) : AccountsState()
    object InvalidAccounts : AccountsState()
}