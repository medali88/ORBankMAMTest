package com.mma.orbankmamtest.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mma.orbankmamtest.di.MainDispatcher
import com.mma.orbankmamtest.presentation.transactions.TransactionsDisplayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _transactionDetailsData =
        MutableStateFlow<TransactionsDisplayModel?>(null)
    val transactionDetailsData: StateFlow<TransactionsDisplayModel?> = _transactionDetailsData

    fun getTransactionsInformation(transactionsDisplayModel: TransactionsDisplayModel) =
        viewModelScope.launch(context = dispatcher) {
            _transactionDetailsData.value = transactionsDisplayModel
        }
}