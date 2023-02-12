package com.mma.orbankmamtest.presentation.accounts

sealed interface AccountsFetchUiState {
    object LoadingAccountsUiState : AccountsFetchUiState
    data class SuccessAccountsUiState(val accounts: List<AccountsDisplayModel>) : AccountsFetchUiState
    object FailureAccountsUiState : AccountsFetchUiState
}