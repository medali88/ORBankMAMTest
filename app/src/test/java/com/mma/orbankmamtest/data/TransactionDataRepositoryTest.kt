package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.data.model.*
import com.mma.orbankmamtest.data.source.transaction.TransactionService
import com.mma.orbankmamtest.domain.models.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class TransactionDataRepositoryTest {

    @Mock
    lateinit var transactionService: TransactionService

    @InjectMocks
    lateinit var transactionDataRepository: TransactionDataRepository

    private val transactionUrl = "/"

    private val transactions = JsonTransactionResponse(
        data = TransactionList(
            transaction = listOf(
                JsonTransactionInformation(
                    transactionId = "transactionId",
                    transactionReference = "transactionReference",
                    amount = JsonAmount(amount = "amount", currency = "currency"),
                    creditDebitIndicator = "creditDebitIndicator",
                    status = "status",
                    valueDateTime = "valueDateTime",
                    transactionInformation = "transactionInformation",
                    bankTransactionCode = JsonBankTransactionCode(code = "code", subCode = "subCode"),
                    proprietaryBankTransactionCode = JsonProprietaryBankTransactionCode(
                        code = "code", issuer = "issuer"
                    ),
                    address = "address"
                )
            )
        )
    )

    @Test
    fun `fetchTransactions when service return formatted response should return a Success state`() {
        runTest {
            // Given
            given(transactionService.getTransactionData(transactionsUrl = transactionUrl)).willReturn(transactions)

            // When
            val result = transactionDataRepository.fetchTransactions(transactionsUrl = transactionUrl)

            // Then
            assertThat(result).isEqualTo(
                TransactionDataState.TransactionSuccess(
                    listOf(
                        Transaction(
                            transactionId = "transactionId",
                            transactionReference = "transactionReference",
                            amount = Amount(
                                amount = "amount",
                                currency = "currency",
                            ),
                            creditDebitIndicator = "creditDebitIndicator",
                            status = "status",
                            valueDateTime = "valueDateTime",
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
    fun `fetchTransactions when service return wrong response should return a Failure state`() {
        runTest {
            // Given
            given(transactionService.getTransactionData(transactionsUrl = transactionUrl)).willReturn(null)

            // When
            val result = transactionDataRepository.fetchTransactions(transactionsUrl = transactionUrl)

            // Then
            assertThat(result).isEqualTo(TransactionDataState.TransactionFailure)
        }
    }
}