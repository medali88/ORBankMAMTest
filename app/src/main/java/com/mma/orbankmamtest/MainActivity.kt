package com.mma.orbankmamtest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.mma.orbankmamtest.presentation.accounts.AccountsScreen
import com.mma.orbankmamtest.presentation.theme.OperationsTheme
import com.mma.orbankmamtest.presentation.transactions.TransactionsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OperationsTheme {
                AccountsScreen(accountsViewModel = hiltViewModel(), transactionViewModel = hiltViewModel())
            }
        }
    }
}
