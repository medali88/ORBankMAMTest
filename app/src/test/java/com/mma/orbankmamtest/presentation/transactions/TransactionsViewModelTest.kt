package com.mma.orbankmamtest.presentation.transactions

import com.mma.orbankmamtest.domain.GetTransactionsUseCase
import com.mma.orbankmamtest.domain.TransactionsState
import com.mma.orbankmamtest.domain.models.*
import com.mma.orbankmamtest.presentation.transactions.TransactionsFetchUiState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class TransactionsViewModelTest {

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @Mock
    lateinit var getTransactionsUseCase: GetTransactionsUseCase

    private lateinit var transactionsViewModel: TransactionsViewModel

    private val transactionUrl = "/"

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        transactionsViewModel = TransactionsViewModel(
            dispatcher = mainDispatcher,
            getTransactionsUseCase = getTransactionsUseCase,
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTransactions when getTransactions from getTransactionsUseCase is ValidTransactions then return SuccessTransactionsUiState with debit and credit transactionsDisplayModel`() {
        runTest {
            //given
            given(getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)).willReturn(
                TransactionsState.ValidTransactions(
                    credit = listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "100",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "credit",
                            status = "status",
                            valueDateTime = "2022-11-05T10:45:22+00:00",
                            transactionInformation = "transactionInformation",
                            bankTransactionCode =
                            BankTransactionCode(
                                code = "code",
                                subCode = "subCode",
                            ),
                            proprietaryBankTransactionCode =
                            ProprietaryBankTransactionCode(
                                code = "code",
                                issuer = "issuer",
                            ),
                            address = "address"
                        ),
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "50",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "credit",
                            status = "status",
                            valueDateTime = "2022-09-05T10:45:22+00:00",
                            transactionInformation = "transactionInformation",
                            bankTransactionCode =
                            BankTransactionCode(
                                code = "code",
                                subCode = "subCode",
                            ),
                            proprietaryBankTransactionCode =
                            ProprietaryBankTransactionCode(
                                code = "code",
                                issuer = "issuer",
                            ),
                            address = "address"
                        )
                    ),
                    debit = listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "100",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
                            status = "status",
                            valueDateTime = "2022-05-05T10:45:22+00:00",
                            transactionInformation = "transactionInformation",
                            bankTransactionCode =
                            BankTransactionCode(
                                code = "code",
                                subCode = "subCode",
                            ),
                            proprietaryBankTransactionCode =
                            ProprietaryBankTransactionCode(
                                code = "code",
                                issuer = "issuer",
                            ),
                            address = "address"
                        ),
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "50",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
                            status = "status",
                            valueDateTime = "2022-11-05T10:45:22+00:00",
                            transactionInformation = "transactionInformation",
                            bankTransactionCode =
                            BankTransactionCode(
                                code = "code",
                                subCode = "subCode",
                            ),
                            proprietaryBankTransactionCode =
                            ProprietaryBankTransactionCode(
                                code = "code",
                                issuer = "issuer",
                            ),
                            address = "address"
                        )
                    )
                )
            )

            //when
            transactionsViewModel.getTransactions(transactionsUrl = transactionUrl)

            //then
            assertThat(transactionsViewModel.transactionsData.value).isEqualTo(LoadingTransactionsUiState)
            scheduler.advanceUntilIdle()
            assertThat(transactionsViewModel.transactionsData.value).isEqualTo(
                SuccessTransactionsUiState(
                    credit = listOf(
                        TransactionsDisplayModel(
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
                            ),
                            address = "address"
                        ),
                        TransactionsDisplayModel(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = AmountDisplayModel(
                                amount = "50,00 €",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "credit",
                            status = "status",
                            valueDateTime = "2022-09-05",
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
                            ),
                            address = "address"
                        )
                    ),
                    debit = listOf(
                        TransactionsDisplayModel(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = AmountDisplayModel(
                                amount = "100,00 €",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
                            status = "status",
                            valueDateTime = "2022-05-05",
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
                            ),
                            address = "address"
                        ),
                        TransactionsDisplayModel(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = AmountDisplayModel(
                                amount = "50,00 €",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
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
                            ),
                            address = "address"
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `getTransactions when getTransactions from getTransactionsUseCase is InvalidTransactions then return FailureTransactionsUiState`() {
        runTest {
            //given
            given(getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)).willReturn(TransactionsState.InvalidTransactions)

            //when
            transactionsViewModel.getTransactions(transactionsUrl = transactionUrl)

            //then
            assertThat(transactionsViewModel.transactionsData.value).isEqualTo(LoadingTransactionsUiState)
            scheduler.advanceUntilIdle()
            assertThat(transactionsViewModel.transactionsData.value).isEqualTo(
                FailureTransactionsUiState
            )
        }
    }

    @Test
    fun `refreshTransactions when getTransactions from getTransactionsUseCase then isRefreshingTransactions is false`() {
        runTest {
            //given
            given(getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)).willReturn(TransactionsState.InvalidTransactions)

            //when
            transactionsViewModel.refreshTransactions(transactionsUrl = transactionUrl)

            //then
            scheduler.advanceUntilIdle()
            assertThat(transactionsViewModel.isRefreshingTransactions.value).isEqualTo(
                false
            )
        }
    }
}