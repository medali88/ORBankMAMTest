package com.mma.orbankmamtest.presentation.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mma.orbankmamtest.di.IoDispatcher
import com.mma.orbankmamtest.domain.AccountsState.*
import com.mma.orbankmamtest.domain.GetAccountsUseCase
import com.mma.orbankmamtest.presentation.accounts.AccountsFetchUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getAccountsUseCase: GetAccountsUseCase
) : ViewModel() {

    private val _accountsData = MutableStateFlow<AccountsFetchUiState>(LoadingAccountsUiState)
    val accountsData: StateFlow<AccountsFetchUiState> = _accountsData

    private val _isRefreshingAccounts = MutableStateFlow(false)
    val isRefreshingAccounts = _isRefreshingAccounts.asStateFlow()

    fun getAccounts() = viewModelScope.launch(context = dispatcher) {
        _accountsData.value = when (val result = getAccountsUseCase.getAccounts()) {
            is ValidAccounts -> {
                SuccessAccountsUiState(
                    AccountsModelTransformer.transformToAccountDataDisplayModel(model = result.account)
                )
            }
            InvalidAccounts -> FailureAccountsUiState
        }
    }


    fun refreshAccounts() = viewModelScope.launch {
        _isRefreshingAccounts.update { true }
        delay(2000)
        getAccounts()
        _isRefreshingAccounts.update { false }
    }
}