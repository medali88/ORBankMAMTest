package com.mma.orbankmamtest.presentation.details

import com.mma.orbankmamtest.presentation.transactions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class TransactionDetailsViewModelTest {

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    private lateinit var transactionDetailsViewModel: TransactionDetailsViewModel

    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        transactionDetailsViewModel = TransactionDetailsViewModel(
            dispatcher = mainDispatcher
        )
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTransactionsInformation given the TransactionsDisplayModel then state should get the some TransactionsDisplayModel`() {
        //given
        val transaction = TransactionsDisplayModel(
            transactionId = "transactionId",
            transactionReference = "transactionReference",
            amount = AmountDisplayModel(
                amount = "100,00 €",
                currency = "currency",
            ),
            creditDebitIndicator = "credit",
            status = "status",
            valueDateTime = "2022-11-05",
            transactionInformation = "transactionInformation",
            bankTransactionCode =
            BankTransactionCodeDisplayModel(
                code = "code",
                subCode = "subCode",
            ),
            proprietaryBankTransactionCode =
            ProprietaryBankTransactionCodeDisplayModel(
                code = "code",
                issuer = "issuer",
            )
        )

        //when
        transactionDetailsViewModel.getTransactionsInformation(transaction)

        //then
        assertThat(transactionDetailsViewModel.transactionDetailsData.value)
            .isEqualTo(null)
        scheduler.advanceUntilIdle()
        assertThat(transactionDetailsViewModel.transactionDetailsData.value).isEqualTo(
            transaction
        )
    }
}