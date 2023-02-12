package com.mma.orbankmamtest.presentation.transactions

sealed interface TransactionsFetchUiState {
    object LoadingTransactionsUiState : TransactionsFetchUiState
    data class SuccessTransactionsUiState(val credit: List<TransactionsDisplayModel>, val debit: List<TransactionsDisplayModel>) : TransactionsFetchUiState
    object FailureTransactionsUiState : TransactionsFetchUiState
}