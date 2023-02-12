package com.mma.orbankmamtest.data.source.transaction

import com.mma.orbankmamtest.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class TransactionServiceTest {

    @Mock
    lateinit var transactionEndPoint: TransactionEndPoint

    @InjectMocks
    lateinit var transactionService: TransactionService

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
                    bankTransactionCode = JsonBankTransactionCode(code = "code", subCode = ""),
                    proprietaryBankTransactionCode = JsonProprietaryBankTransactionCode(
                        code = "code", issuer = "issuer"
                    ),
                )
            )
        )
    )

    @Test
    fun `getTransactionData - when call the endPoint - then return transactions`() {
        runTest {
            //given
            given(transactionEndPoint.getTransaction(transactionsUrl = transactionUrl)).willReturn(transactions)

            //when
            val result = transactionService.getTransactionData(transactionsUrl = transactionUrl)

            //then
            assertThat(result).isEqualTo(transactions)
        }
    }
}