package com.mma.orbankmamtest.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mma.orbankmamtest.R
import com.mma.orbankmamtest.presentation.accounts.AccountsScreen
import com.mma.orbankmamtest.presentation.details.TransactionDetailsScreen
import com.mma.orbankmamtest.presentation.details.TransactionDetailsViewModel
import com.mma.orbankmamtest.presentation.theme.Typography
import com.mma.orbankmamtest.presentation.theme.xLargeHeading
import com.mma.orbankmamtest.presentation.theme.OperationsOrangeLight
import com.mma.orbankmamtest.presentation.theme.OperationsWhite

enum class OrBankScreen(@StringRes val title: Int) {
    Main(title = R.string.app_name),
    Detail(title = R.string.details_title),
}

@Composable
fun OrBankAppBar(
    currentScreen: OrBankScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(title = {
        Text(
            text = stringResource(currentScreen.title),
            style = Typography.xLargeHeading(),
            color = OperationsOrangeLight
        )
    }, modifier = modifier, navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = OperationsWhite,
                    contentDescription = null
                )
            }
        }
    })
}

@Composable
fun OrBankMain(
    modifier: Modifier = Modifier,
    transactionDetailsViewModel: TransactionDetailsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = OrBankScreen.valueOf(
        backStackEntry?.destination?.route ?: OrBankScreen.Main.name
    )
    Scaffold(topBar = {
        OrBankAppBar(currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        val transactionDetailsUiState by transactionDetailsViewModel.transactionDetailsData.collectAsState()

        NavHost(
            navController = navController,
            startDestination = OrBankScreen.Main.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = OrBankScreen.Main.name) {
                AccountsScreen(accountsViewModel = hiltViewModel(),
                    transactionViewModel = hiltViewModel(),
                    onTransactionClicked = {
                        transactionDetailsViewModel.getTransactionsInformation(it)
                        navController.navigate(OrBankScreen.Detail.name)
                    })
            }
            composable(route = OrBankScreen.Detail.name) {
                transactionDetailsUiState?.let { transactionDetails ->
                    TransactionDetailsScreen(
                        transactionDisplayModel = transactionDetails,
                    )
                }
            }
        }
    }
}