package com.mma.orbankmamtest.presentation.accounts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.mma.orbankmamtest.presentation.accounts.AccountsFetchUiState.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mma.orbankmamtest.R
import com.mma.orbankmamtest.presentation.widgets.OrBankLoading
import com.mma.orbankmamtest.presentation.theme.*
import com.mma.orbankmamtest.presentation.transactions.TransactionsScreen
import com.mma.orbankmamtest.presentation.transactions.TransactionsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountsScreen(
    accountsViewModel: AccountsViewModel = viewModel(),
    transactionViewModel: TransactionsViewModel = viewModel()
) {
    val accounts by accountsViewModel.accountsData.collectAsState()
    val isRefreshingAccounts by accountsViewModel.isRefreshingAccounts.collectAsState()
    val isRefreshingTransaction by transactionViewModel.isRefreshingTransactions.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedAccountTransactionUrl by remember { mutableStateOf("") }
    val pullRefreshAccountsState = rememberPullRefreshState(isRefreshingAccounts, {
        accountsViewModel.refreshAccounts()
    })
    val pullRefreshTransactionState = rememberPullRefreshState(isRefreshingTransaction, {
        accountsViewModel.refreshAccounts()
        transactionViewModel.refreshTransactions(selectedAccountTransactionUrl)
    })
    LaunchedEffect(true) {
        accountsViewModel.getAccounts()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshAccountsState),
        contentAlignment = Alignment.Center
    ) {
        Surface {
            PullRefreshIndicator(
                isRefreshingAccounts,
                pullRefreshAccountsState,
                Modifier.align(Alignment.TopCenter),
                backgroundColor = OperationsRed
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_title),
                    textAlign = TextAlign.Center,
                    color = OperationsOrange,
                    style = Typography.xxLargeHeading()
                )
                when (accounts) {
                    is SuccessAccountsUiState -> {
                        AccountsSelector(modifier = Modifier.padding(top = 16.dp),
                            accounts = (accounts as SuccessAccountsUiState).accounts,
                            onAccountSelected = {
                                selectedAccountTransactionUrl = it.transactionsUrl
                                transactionViewModel.getTransactions(it.transactionsUrl)
                                expanded = true
                            })
                        AnimatedVisibility(
                            visible = expanded,
                        ) {
                            TransactionsScreen(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .pullRefresh(pullRefreshTransactionState)
                                    .padding(top = 20.dp),
                            )
                        }
                    }
                    FailureAccountsUiState -> {
                        if (!isRefreshingAccounts) {
                            FailureGetAccounts(accountsViewModel = accountsViewModel)
                        }
                    }
                    LoadingAccountsUiState -> OrBankLoading(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
private fun FailureGetAccounts(accountsViewModel: AccountsViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.error_text),
            textAlign = TextAlign.Center,
            color = OperationsRed,
            style = Typography.xLargeHeading()
        )
        OutlinedButton(
            onClick = { accountsViewModel.refreshAccounts() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = OperationsDark
            ),
            contentPadding = PaddingValues(
                vertical = 12.dp,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.refresh_text),
                style = Typography.largeText(),
                color = OperationsWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountsSelector(
    modifier: Modifier,
    accounts: List<AccountsDisplayModel>,
    onAccountSelected: (account: AccountsDisplayModel) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(modifier = modifier, expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small)
                .border(3.dp, OperationsOrange),
            readOnly = true,
            value = selectedOptionText,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.choose_text),
                    style = Typography.largeText(),
                    color = OperationsOrange,
                )
            },
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = OperationsRedLight, textColor = OperationsOrange
            )
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            accounts.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    selectedOptionText = selectionOption.nickname
                    expanded = false
                    onAccountSelected(selectionOption)
                }) {
                    Text(
                        text = selectionOption.nickname,
                        textAlign = TextAlign.Center,
                        color = OperationsOrange,
                        style = Typography.xLargeHeading()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SpinnerAccountsPreview() {
    AccountsSelector(modifier = Modifier, accounts = listOf(
        AccountsDisplayModel(
            accountId = "accountId",
            status = "status",
            statusUpdateDateTime = "statusUpdateDateTime",
            currency = "currency",
            accountType = "accountType",
            accountSubType = "accountId",
            nickname = "nickname",
            openingDate = "openingDate",
            transactionsUrl = "transactionsUrl",
            accountInfo = listOf(
                AccountInfoDisplayModel(
                    schemeName = "schemeName",
                    identification = "identification",
                    name = "name",
                    secondaryIdentification = "secondaryIdentification",
                )
            )
        )
    ), onAccountSelected = {})
}