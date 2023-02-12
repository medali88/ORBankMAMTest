package com.mma.orbankmamtest.domain

import com.mma.orbankmamtest.data.TransactionDataRepository
import com.mma.orbankmamtest.data.TransactionDataState.*
import com.mma.orbankmamtest.domain.TransactionsState.*
import com.mma.orbankmamtest.domain.models.Amount
import com.mma.orbankmamtest.domain.models.BankTransactionCode
import com.mma.orbankmamtest.domain.models.ProprietaryBankTransactionCode
import com.mma.orbankmamtest.domain.models.Transaction
import com.mma.orbankmamtest.extensions.toYearMonthDayServerFormat
import com.mma.orbankmamtest.open.OpenForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OpenForTesting
class GetTransactionsUseCase @Inject constructor(
    private val transactionDataRepository: TransactionDataRepository,
    private val dispatcher: CoroutineDispatcher
) {

    companion object {
        private const val CANCELLED_OPERATION_STATUS = "Cancelled"
    }

    suspend fun getTransactions(transactionsUrl: String) = withContext(dispatcher) {
        when (val result =
            transactionDataRepository.fetchTransactions(transactionsUrl = transactionsUrl)) {
            is TransactionSuccess -> {
                try {
                    val listOfTransactions =
                        getTransactionsGroupByCreditDebitIndicator(result.transactions).map {
                                transformToListOfTransactions(it)
                            }
                    val creditList =
                        getFinalListOfTransaction(listOfTransactions = listOfTransactions[0])
                    val debitList =
                        getFinalListOfTransaction(listOfTransactions = listOfTransactions[1])
                    ValidTransactions(credit = creditList, debit = debitList)
                } catch (e: Exception) {
                    InvalidTransactions
                }
            }
            TransactionFailure -> InvalidTransactions
        }
    }

    private fun getFinalListOfTransaction(listOfTransactions: List<Transaction>) =
        listOfTransactions.filter { it.status != CANCELLED_OPERATION_STATUS }.takeLast(2)
            .sortedByDescending { it.valueDateTime.toYearMonthDayServerFormat() }


    private fun transformToListOfTransactions(it: Map.Entry<String, List<Transaction>>): List<Transaction> {
        return it.value.map { transaction ->
            with(transaction) {
                Transaction(
                    transactionId = transactionId,
                    transactionReference = transactionReference,
                    amount = with(amount) {
                        Amount(
                            amount = amount,
                            currency = currency,
                        )
                    },
                    creditDebitIndicator = creditDebitIndicator,
                    status = status,
                    valueDateTime = valueDateTime,
                    transactionInformation = transactionInformation,
                    bankTransactionCode = with(bankTransactionCode) {
                        BankTransactionCode(
                            code = code,
                            subCode = subCode,
                        )
                    },
                    proprietaryBankTransactionCode = with(proprietaryBankTransactionCode) {
                        ProprietaryBankTransactionCode(
                            code = code,
                            issuer = issuer,
                        )
                    },
                )
            }
        }
    }

    private fun getTransactionsGroupByCreditDebitIndicator(operationList: List<Transaction>): Map<String, List<Transaction>> =
        operationList.groupBy { it.creditDebitIndicator }
}

sealed class TransactionsState {
    data class ValidTransactions(val credit: List<Transaction>, val debit: List<Transaction>) :
        TransactionsState()

    object InvalidTransactions : TransactionsState()
}