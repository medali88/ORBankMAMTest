package com.mma.orbankmamtest.presentation.transactions

import com.mma.orbankmamtest.domain.models.*
import com.mma.orbankmamtest.extensions.toYearMonthDayServerFormat
import com.mma.orbankmamtest.extensions.toCurrency

object TransactionsModelTransformer {

    fun transformToTransactionsDataDisplayModel(model: List<Transaction>): List<TransactionsDisplayModel> =
        model.map { transaction ->
            with(transaction) {
                TransactionsDisplayModel(
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
                    address = address
                )
            }
        }
}