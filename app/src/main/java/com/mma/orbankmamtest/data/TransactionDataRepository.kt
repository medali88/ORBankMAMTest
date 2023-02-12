package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.data.model.JsonTransactionResponse
import com.mma.orbankmamtest.data.TransactionDataState.*
import com.mma.orbankmamtest.data.source.transaction.TransactionService
import com.mma.orbankmamtest.domain.models.*
import com.mma.orbankmamtest.open.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class TransactionDataRepository @Inject constructor(private val transactionService: TransactionService) {

    suspend fun fetchTransactions(transactionsUrl: String) = try {
        transformToEntity(response = transactionService.getTransactionData(transactionsUrl = transactionsUrl))
    } catch (e: Exception) {
        TransactionFailure
    }

    private fun transformToEntity(response: JsonTransactionResponse) =
        TransactionSuccess(transactions = response.toTransactionEntity())

    private fun JsonTransactionResponse.toTransactionEntity() =
        data.transaction.map { transaction ->
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