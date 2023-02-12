package com.mma.orbankmamtest.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mma.orbankmamtest.R
import com.mma.orbankmamtest.presentation.transactions.AmountDisplayModel
import com.mma.orbankmamtest.presentation.transactions.TransactionsDisplayModel
import com.mma.orbankmamtest.presentation.widgets.TransactionDetailCard

@Composable
fun TransactionDetailsScreen(
    transactionDisplayModel: TransactionsDisplayModel,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TransactionDetailCard(
                modifier = Modifier,
                title = stringResource(id = R.string.transaction_information_title),
                value = transactionDisplayModel.transactionInformation
            )
            TransactionDetailCard(
                modifier = Modifier.padding(top = 12.dp),
                title = stringResource(id = R.string.transaction_reference_title),
                value = transactionDisplayModel.transactionReference
            )
            TransactionDetailCard(
                modifier = Modifier.padding(top = 12.dp),
                title = stringResource(id = R.string.transaction_amount_title),
                value = transactionDisplayModel.amount.amount
            )
            transactionDisplayModel.address?.let {
                TransactionDetailCard(
                    modifier = Modifier.padding(top = 12.dp),
                    title = stringResource(id = R.string.transaction_adress_title),
                    value = it
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionDetailsScreenPreview() {
    TransactionDetailsScreen(
        transactionDisplayModel = TransactionsDisplayModel(
            transactionReference = "transactionReference",
            amount = AmountDisplayModel(
                amount = "100,00 €",
                currency = "currency",
            ),
            creditDebitIndicator = "credit",
            status = "status",
            valueDateTime = "2022-11-05",
            transactionInformation = "transactionInformation",
            address = "address"
        ),
    )
}