package com.mma.orbankmamtest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.mma.orbankmamtest.presentation.navigation.OrBankMain
import com.mma.orbankmamtest.presentation.theme.OperationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OperationsTheme {
                OrBankMain(transactionDetailsViewModel = hiltViewModel())
            }
        }
    }
}
