package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.data.model.*
import com.mma.orbankmamtest.data.source.account.AccountService
import com.mma.orbankmamtest.domain.models.Account
import com.mma.orbankmamtest.domain.models.AccountInfo
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
class AccountDataRepositoryTest {

    @Mock
    lateinit var accountService: AccountService

    @InjectMocks
    lateinit var accountDataRepository: AccountDataRepository

    private val accounts = JsonAccountResponse(
        data = AccountList(
            account = listOf(
                JsonAccountInformation(
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
                        JsonAccountInfo(
                            schemeName = "schemeName",
                            identification = "identification",
                            name = "name",
                            secondaryIdentification = "secondaryIdentification",
                        )
                    )
                )
            )
        )
    )

    @Test
    fun `fetchAccounts when service return formatted response should return a Success state`() {
        runTest {
            // Given
            given(accountService.getAccountsData()).willReturn(accounts)

            // When
            val result = accountDataRepository.fetchAccounts()

            // Then
            assertThat(result).isEqualTo(
                AccountDataState.AccountSuccess(
                    listOf(
                        Account(
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
                                AccountInfo(
                                    schemeName = "schemeName",
                                    identification = "identification",
                                    name = "name",
                                    secondaryIdentification = "secondaryIdentification",
                                )
                            )
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `fetchAccounts when service return wrong response should return a Failure state`() {
        runTest {
            // Given
            given(accountService.getAccountsData()).willReturn(null)

            // When
            val result = accountDataRepository.fetchAccounts()

            // Then
            assertThat(result).isEqualTo(AccountDataState.AccountFailure)
        }
    }
}