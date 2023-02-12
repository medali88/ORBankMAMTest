package com.mma.orbankmamtest.presentation.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mma.orbankmamtest.presentation.transactions.TransactionsFetchUiState.*
import com.mma.orbankmamtest.domain.TransactionsState.*
import com.mma.orbankmamtest.domain.GetTransactionsUseCase
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
class TransactionsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _transactionsData =
        MutableStateFlow<TransactionsFetchUiState>(LoadingTransactionsUiState)
    val transactionsData: StateFlow<TransactionsFetchUiState> = _transactionsData

    private val _isRefreshingTransactions = MutableStateFlow(false)
    val isRefreshingTransactions = _isRefreshingTransactions.asStateFlow()

    fun getTransactions(transactionsUrl: String) {
        viewModelScope.launch(context = dispatcher) {
            _transactionsData.value =
                when (val result = getTransactionsUseCase.getTransactions(transactionsUrl = transactionsUrl)) {
                    is ValidTransactions -> {
                        SuccessTransactionsUiState(
                            credit = TransactionsModelTransformer.transformToTransactionsDataDisplayModel(
                                model = result.credit
                            ),
                            debit = TransactionsModelTransformer.transformToTransactionsDataDisplayModel(
                                model = result.debit
                            )
                        )
                    }
                    InvalidTransactions -> FailureTransactionsUiState
                }
        }
    }

    fun refreshTransactions(transactionsUrl: String) = viewModelScope.launch {
        _isRefreshingTransactions.update { true }
        delay(2000)
        getTransactions(transactionsUrl)
        _isRefreshingTransactions.update { false }
    }
}