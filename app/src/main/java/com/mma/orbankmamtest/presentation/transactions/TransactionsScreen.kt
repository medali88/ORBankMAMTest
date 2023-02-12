package com.mma.orbankmamtest.presentation.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mma.orbankmamtest.presentation.transactions.TransactionsFetchUiState.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mma.orbankmamtest.R
import com.mma.orbankmamtest.presentation.theme.*
import com.mma.orbankmamtest.presentation.widgets.OperationCard
import com.mma.orbankmamtest.presentation.widgets.OrBankLoading

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    transactionsViewModel: TransactionsViewModel = viewModel(),
    onTransactionClicked: (TransactionsDisplayModel) -> Unit,
) {
    val transactions by transactionsViewModel.transactionsData.collectAsState()
    val scrollState = rememberLazyListState()
    when (transactions) {
        is SuccessTransactionsUiState -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                state = scrollState
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.credit_title),
                        textAlign = TextAlign.Center,
                        color = OperationsRed,
                        style = Typography.largeHeading()
                    )
                }
                (transactions as? SuccessTransactionsUiState)?.credit?.let {
                    transactionsList(
                        transactionList = it,
                        onTransactionSelected = { details ->
                            onTransactionClicked(details)
                        }
                    )
                }
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        text = stringResource(id = R.string.debit_title),
                        textAlign = TextAlign.Center,
                        color = OperationsRed,
                        style = Typography.largeHeading()
                    )
                }
                (transactions as? SuccessTransactionsUiState)?.debit?.let {
                    transactionsList(
                        transactionList = it,
                        onTransactionSelected = { details ->
                            onTransactionClicked(details)
                        }
                    )
                }
            }
        }
        FailureTransactionsUiState -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.error_text),
                        textAlign = TextAlign.Center,
                        color = OperationsRed,
                        style = Typography.xLargeHeading()
                    )
                    Text(
                        text = stringResource(id = R.string.swipe_title),
                        textAlign = TextAlign.Center,
                        color = OperationsRed,
                        style = Typography.largeHeading()
                    )
                }
            }
        }
        LoadingTransactionsUiState -> OrBankLoading(modifier = Modifier.fillMaxSize())
    }

}

fun LazyListScope.transactionsList(
    transactionList: List<TransactionsDisplayModel>,
    onTransactionSelected: (TransactionsDisplayModel) -> Unit
) {
    items(transactionList) { transaction ->
        OperationCard(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
            transactionsDisplayModel = transaction,
            onClick = { onTransactionSelected(transaction) }
        )
    }
}

