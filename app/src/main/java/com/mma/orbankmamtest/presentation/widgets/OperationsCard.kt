package com.mma.orbankmamtest.presentation.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mma.orbankmamtest.presentation.theme.OperationsOrangeDark
import com.mma.orbankmamtest.presentation.theme.OperationsWhite
import com.mma.orbankmamtest.presentation.theme.Typography
import com.mma.orbankmamtest.presentation.theme.largeHeading
import com.mma.orbankmamtest.presentation.theme.smallHeading
import com.mma.orbankmamtest.presentation.theme.OperationsTheme
import com.mma.orbankmamtest.presentation.transactions.AmountDisplayModel
import com.mma.orbankmamtest.presentation.transactions.TransactionsDisplayModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OperationCard(
    modifier: Modifier = Modifier,
    transactionsDisplayModel : TransactionsDisplayModel,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 6.dp,
        shape = RoundedCornerShape(size = 5.dp),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = transactionsDisplayModel.valueDateTime,
                style = Typography.largeHeading(),
                color = OperationsOrangeDark
            )
            Text(
                text = transactionsDisplayModel.amount.amount,
                style = Typography.largeHeading(),
                color = OperationsOrangeDark,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionDetailCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = OperationsOrangeDark,
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 8.dp),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                text = title,
                style = Typography.smallHeading(),
                color = OperationsWhite
            )
            Text(
                text = value,
                style = Typography.largeHeading(),
                color = OperationsWhite,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview
@Composable
fun TransactionDetailCardPreview() {
    OperationsTheme {
        TransactionDetailCard(title = "title", value = "value")
    }
}

@Preview
@Composable
fun OperationCardPreview() {
    OperationsTheme {
        OperationCard(transactionsDisplayModel =  TransactionsDisplayModel(
            transactionReference = "transactionReference",
            amount = AmountDisplayModel(
                    amount = "amount",
                    currency = "currency",
                ),
            creditDebitIndicator = "creditDebitIndicator",
            status = "status",
            valueDateTime = "valueDateTime",
            transactionInformation = "transactionInformation",
            address = "address"
        )
        )
    }
}