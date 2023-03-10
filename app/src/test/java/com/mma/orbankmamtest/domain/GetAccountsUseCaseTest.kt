package com.mma.orbankmamtest.domain

import com.mma.orbankmamtest.data.AccountDataRepository
import com.mma.orbankmamtest.data.AccountDataState
import com.mma.orbankmamtest.domain.models.Account
import com.mma.orbankmamtest.domain.models.AccountInfo
import com.mma.orbankmamtest.domain.AccountsState.*
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
class GetAccountsUseCaseTest {

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @Mock
    private lateinit var accountDataRepository: AccountDataRepository

    private lateinit var getAccountsUseCase: GetAccountsUseCase


    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        getAccountsUseCase = GetAccountsUseCase(
            dispatcher = mainDispatcher,
            accountDataRepository = accountDataRepository,
        )
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAccounts should fetch accounts and return ValidAccounts success with accounts`() {
        runTest {
            //given
            given(accountDataRepository.fetchAccounts()).willReturn(
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

            // When
            val result = getAccountsUseCase.getAccounts()
            scheduler.advanceUntilIdle()

            // Then
            assertThat(result).isEqualTo(
                ValidAccounts(
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
    fun `getAccounts should fetch accounts and return InvalidAccounts`() {
        runTest {
            //given
            given(accountDataRepository.fetchAccounts()).willReturn(AccountDataState.AccountFailure)

            // When
            val result = getAccountsUseCase.getAccounts()
            scheduler.advanceUntilIdle()

            // Then
            assertThat(result).isEqualTo(InvalidAccounts)
        }
    }
}