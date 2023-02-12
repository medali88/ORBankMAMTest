package com.mma.orbankmamtest.domain

import com.mma.orbankmamtest.data.TransactionDataRepository
import com.mma.orbankmamtest.data.TransactionDataState
import com.mma.orbankmamtest.domain.TransactionsState.*
import com.mma.orbankmamtest.domain.models.*
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
class GetTransactionsUseCaseTest {

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @Mock
    private lateinit var transactionDataRepository: TransactionDataRepository

    private lateinit var getTransactionsUseCase: GetTransactionsUseCase

    private val transactionUrl = "/"

    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        getTransactionsUseCase = GetTransactionsUseCase(
            dispatcher = mainDispatcher,
            transactionDataRepository = transactionDataRepository,
        )
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTransactions when fetch transactions is TransactionSuccess then return ValidTransactions success with debit and credit transactions`() {
        runTest {
            //given
            given(transactionDataRepository.fetchTransactions(transactionsUrl = transactionUrl)).willReturn(
                TransactionDataState.TransactionSuccess(
                    listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "amount",
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
                                amount = "amount",
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
                        ),
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "amount",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
                            status = "status",
                            valueDateTime = "2022-02-05T10:45:22+00:00",
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
                                amount = "amount",
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
                                amount = "amount",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "credit",
                            status = "Cancelled",
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
                    )
                )
            )

            // When
            val result = getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)
            scheduler.advanceUntilIdle()

            // Then
            assertThat(result).isEqualTo(
                ValidTransactions(
                    credit = listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "amount",
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
                                amount = "amount",
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
                                amount = "amount",
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
                                amount = "amount",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "debit",
                            status = "status",
                            valueDateTime = "2022-02-05T10:45:22+00:00",
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
        }
    }

    @Test
    fun `getTransactions when fetch transactions is TransactionFailure then return InvalidTransactions`() {
        runTest {
            //given
            given(transactionDataRepository.fetchTransactions(transactionsUrl = transactionUrl)).willReturn(
                TransactionDataState.TransactionFailure
            )

            // When
            val result = getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)
            scheduler.advanceUntilIdle()

            // Then
            assertThat(result).isEqualTo(InvalidTransactions)
        }
    }

    @Test
    fun `getTransactions when fetch transactions is TransactionSuccess with invalid valueDateTime then return InvalidTransactions`() {
        runTest {
            //given
            given(transactionDataRepository.fetchTransactions(transactionsUrl = transactionUrl)).willReturn(
                TransactionDataState.TransactionSuccess(
                    listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "amount",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "credit",
                            status = "status",
                            valueDateTime = "2022-11-05T10:45",
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
            // When
            val result =
                getTransactionsUseCase.getTransactions(transactionsUrl = transactionUrl)
            scheduler.advanceUntilIdle()

            // Then
            assertThat(result).isEqualTo(InvalidTransactions)

        }
    }
}