package com.mma.orbankmamtest.presentation.transactions

import com.mma.orbankmamtest.domain.models.*
import com.mma.orbankmamtest.extensions.toYearMonthDayServerFormat
import com.mma.orbankmamtest.extensions.toCurrency

object TransactionsModelTransformer {

    fun transformToTransactionsDataDisplayModel(model: List<Transaction>): List<TransactionsDisplayModel> =
        model.map { transaction ->
            with(transaction) {
                TransactionsDisplayModel(
                    transactionId = transactionId,
                    transactionReference = transactionReference,
                    amount = with(amount) {
                        AmountDisplayModel(
                            amount = amount.toBigDecimal().toCurrency(),
                            currency = currency,
                        )
                    },
                    creditDebitIndicator = creditDebitIndicator,
                    status = status,
                    valueDateTime = valueDateTime.toYearMonthDayServerFormat().toString(),
                    transactionInformation = transactionInformation,
                    bankTransactionCode = with(bankTransactionCode) {
                        BankTransactionCodeDisplayModel(
                            code = code,
                            subCode = subCode,
                        )
                    },
                    proprietaryBankTransactionCode = with(proprietaryBankTransactionCode) {
                        ProprietaryBankTransactionCodeDisplayModel(
                            code = code,
                            issuer = issuer,
                        )
                    },
                    address = address
                )
            }
        }
}